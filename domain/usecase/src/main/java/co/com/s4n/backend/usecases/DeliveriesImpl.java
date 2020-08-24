package co.com.s4n.backend.usecases;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import co.com.s4n.backend.exceptions.MaxBlocksAllowedException;
import co.com.s4n.backend.exceptions.MaxConfiguredLunchesException;
import co.com.s4n.backend.model.Drone;
import co.com.s4n.backend.model.FinalDeliveryAddress;
import co.com.s4n.backend.model.Lunch;
import co.com.s4n.backend.model.interfaces.Deliveries;
import co.com.s4n.backend.model.interfaces.TextFileProcesses;

public class DeliveriesImpl extends Thread implements Deliveries{

	private static final String MAX_LUNCHES_EXCEPTION = "Lunches quantity per dron not allowed, maximum is %s and actual is %s";
	private static final String MAX_BLOCKS_EXCEPTION = "Lunch cannot be delivered, the drone cannot move beyond the allowed limit %s";
	private static final char AHEAD = 'A';
	private static final char RIGHT = 'D';
	private static final char LEFT = 'I';
	private static final String SOUTH = "SOUTH";
	private static final String NORTH = "NORTH";
	private static final String EAST = "EAST";
	private static final String WEST = "WEST";
	private String movementsFileName;
	private int maxLunches;
	private int blocksAllowed;


	private TextFileProcesses textFileProcesses;

	public DeliveriesImpl() {
	}

	public DeliveriesImpl(TextFileProcesses textFileProcesses, String movementsFileName, 
			int maxLunches, int blocksAllowed) {
		this.textFileProcesses = textFileProcesses;
		this.movementsFileName = movementsFileName;
		this.maxLunches = maxLunches;
		this.blocksAllowed = blocksAllowed;
	}

	@Override
	public List<Lunch> setDroneSettings(List<String> addresses) {

		return addresses.stream()
				.map(address -> new Lunch().movements(address).build())
				.collect(Collectors.toList());
	}

	@Override
	public Drone setDelivery(String movementsFileName) throws IOException {

		return new Drone()
				.lunches(setDroneSettings(textFileProcesses.readInputFile(movementsFileName)))
				.build();
	}

	@Override
	public List <FinalDeliveryAddress> sendDeliveries() throws IOException, 
	MaxConfiguredLunchesException {

		Drone drone = setDelivery(movementsFileName);	
		if(drone.getLunches().size() > maxLunches) {
			throw new MaxConfiguredLunchesException(String.format(MAX_LUNCHES_EXCEPTION, 
					maxLunches, drone.getLunches().size()));
		}
		List <FinalDeliveryAddress> finalDeliveryAddress = drone.getLunches().stream()
				.map(this::deliverLunch)
				.collect(Collectors.toList());
		textFileProcesses.generateDeliveryReport(finalDeliveryAddress, movementsFileName);
		return finalDeliveryAddress;		
	}

	@Override
	public void run() {

		try {
			sendDeliveries();
		} catch (IOException | MaxConfiguredLunchesException e) {
			e.printStackTrace();
		}
		super.run();
	}

	private FinalDeliveryAddress deliverLunch(Lunch lunch){

		FinalDeliveryAddress finalDeliveryAddress = new FinalDeliveryAddress();
		finalDeliveryAddress.setCardinalPoint(NORTH);
		lunch.getMovements().chars().forEach(character ->{
			if(character == LEFT) {
				turn(finalDeliveryAddress, LEFT);
			}
			if(character == RIGHT) {
				turn(finalDeliveryAddress, RIGHT);
			}
			if(character == AHEAD) {
				try {
					move(finalDeliveryAddress);
				} catch (MaxBlocksAllowedException e) {
					e.printStackTrace();
				}
			}
		});
		return finalDeliveryAddress;
	}

	private void move(FinalDeliveryAddress finalDeliveryAddress) throws MaxBlocksAllowedException {

		switch (finalDeliveryAddress.getCardinalPoint()) {
		case NORTH:
			finalDeliveryAddress.setyCoordinate(validateNextMove(finalDeliveryAddress.getyCoordinate()+1));
			break;

		case WEST:
			finalDeliveryAddress.setxCoordinate(validateNextMove(finalDeliveryAddress.getxCoordinate()-1));
			break;

		case SOUTH:
			finalDeliveryAddress.setyCoordinate(validateNextMove(finalDeliveryAddress.getyCoordinate()-1));
			break;

		case EAST:
			finalDeliveryAddress.setxCoordinate(validateNextMove(finalDeliveryAddress.getxCoordinate()+1));
			break;

		default:
			break;
		}
	}

	private void turn(FinalDeliveryAddress finalDeliveryAddress, char twistType) {

		switch (finalDeliveryAddress.getCardinalPoint()) {
		case NORTH:
			finalDeliveryAddress.setCardinalPoint(LEFT == twistType ? WEST : EAST);
			break;
		case WEST:
			finalDeliveryAddress.setCardinalPoint(LEFT == twistType ? SOUTH : NORTH);
			break;
		case SOUTH:
			finalDeliveryAddress.setCardinalPoint(LEFT == twistType ? EAST : WEST);
			break;
		case EAST:
			finalDeliveryAddress.setCardinalPoint(LEFT == twistType ? NORTH : SOUTH);
			break;

		default:
			break;
		}
	}
	
	private int validateNextMove(int nextCoordinate) throws MaxBlocksAllowedException {
		
		if(nextCoordinate > blocksAllowed || nextCoordinate < -blocksAllowed) {
			throw new MaxBlocksAllowedException(String.format(MAX_BLOCKS_EXCEPTION, blocksAllowed));
		}
		return nextCoordinate;
	}
}
