package co.com.s4n.backend.model.interfaces;

import java.io.IOException;
import java.util.List;

import co.com.s4n.backend.exceptions.MaxConfiguredLunchesException;
import co.com.s4n.backend.model.Drone;
import co.com.s4n.backend.model.FinalDeliveryAddress;
import co.com.s4n.backend.model.Lunch;

public interface Deliveries {

	/**
	 * This method sets the settings for the lunch
	 * @param addresses
	 * @return The list of lunches with the indications for each of them
	 */
	List<Lunch> setDroneSettings(List<String> addresses);

	/**
	 * This method sets the drone with the lunches
	 * @param movementsFileName
	 * @return  Drone with all lunches loaded
	 * @throws IOException
	 */
	Drone setDelivery(String movementsFileName) throws IOException;

	/**
	 * This method launches the deliveries process
	 * @return The list with the final coordinates
	 * @throws IOException
	 * @throws MaxConfiguredLunchesException
	 */
	List <FinalDeliveryAddress> sendDeliveries() throws IOException, MaxConfiguredLunchesException;

}
