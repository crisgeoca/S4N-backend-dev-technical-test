package co.com.s4n.backend.usecases;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import co.com.s4n.backend.exceptions.MaxConfiguredLunchesException;
import co.com.s4n.backend.model.FinalDeliveryAddress;
import co.com.s4n.backend.model.interfaces.TextFileProcesses;

@RunWith(MockitoJUnitRunner.class)
public class DeliveriesImplTest {

	private static final String IN01_TXT = "in01.txt";
	private static final String MAX_LUNCHES_EXCEPTION = "Lunches quantity per dron not allowed, maximum is 2 and actual is 3";
	private static final String SOUTH = "SOUTH";
	private static final String NORTH = "NORTH";
	private static final String EAST = "EAST";
	private static final String WEST = "WEST";
	
	@InjectMocks
	private DeliveriesImpl deliveriesImpl;

	@Mock
	private TextFileProcesses textFileProcesses;
	
	@Rule
    public ErrorCollector errorCollector = new ErrorCollector();
	
	@Before
	 public void setUp() {
		deliveriesImpl = new DeliveriesImpl(textFileProcesses, IN01_TXT, 3, 10);
	}

	@Test
	public void sendDeliveriesExpectedResultTest() {

		List <FinalDeliveryAddress> finalCoordinatesList = null;

		try {
			Mockito.when(textFileProcesses.readInputFile(IN01_TXT)).thenReturn(buildInputStringList());
			Mockito.doNothing().when(textFileProcesses).generateDeliveryReport(ArgumentMatchers.anyList(), ArgumentMatchers.anyString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			finalCoordinatesList = deliveriesImpl.sendDeliveries();
		} catch (IOException | MaxConfiguredLunchesException e) {
			e.printStackTrace();			
		}
		
		errorCollector.checkThat(finalCoordinatesList, notNullValue());
		
		errorCollector.checkThat(finalCoordinatesList.get(0).getxCoordinate(), equalTo(-2));
		errorCollector.checkThat(finalCoordinatesList.get(0).getyCoordinate(), equalTo(4));
		errorCollector.checkThat(finalCoordinatesList.get(0).getCardinalPoint(), equalTo(NORTH));
		
		errorCollector.checkThat(finalCoordinatesList.get(1).getxCoordinate(), equalTo(-3));
		errorCollector.checkThat(finalCoordinatesList.get(1).getyCoordinate(), equalTo(3));
		errorCollector.checkThat(finalCoordinatesList.get(1).getCardinalPoint(), equalTo(SOUTH));
		
		errorCollector.checkThat(finalCoordinatesList.get(2).getxCoordinate(), equalTo(-4));
		errorCollector.checkThat(finalCoordinatesList.get(2).getyCoordinate(), equalTo(2));
		errorCollector.checkThat(finalCoordinatesList.get(2).getCardinalPoint(), equalTo(EAST));
		
		try {
			Mockito.verify(textFileProcesses).readInputFile(IN01_TXT);
			Mockito.verify(textFileProcesses).generateDeliveryReport(ArgumentMatchers.anyList(), ArgumentMatchers.anyString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sendDeliveriesManualResultTest() {

		List <FinalDeliveryAddress> finalCoordinatesList = null;

		try {
			Mockito.when(textFileProcesses.readInputFile(IN01_TXT)).thenReturn(buildInputStringList());
			Mockito.doNothing().when(textFileProcesses).generateDeliveryReport(ArgumentMatchers.anyList(), ArgumentMatchers.anyString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			finalCoordinatesList = deliveriesImpl.sendDeliveries();
		} catch (IOException | MaxConfiguredLunchesException e) {
			e.printStackTrace();			
		}
		
		errorCollector.checkThat(finalCoordinatesList, notNullValue());
		
		errorCollector.checkThat(finalCoordinatesList.get(0).getxCoordinate(), equalTo(-2));
		errorCollector.checkThat(finalCoordinatesList.get(0).getyCoordinate(), equalTo(4));
		errorCollector.checkThat(finalCoordinatesList.get(0).getCardinalPoint(), equalTo(WEST));
		
		errorCollector.checkThat(finalCoordinatesList.get(1).getxCoordinate(), equalTo(-1));
		errorCollector.checkThat(finalCoordinatesList.get(1).getyCoordinate(), equalTo(-1));
		errorCollector.checkThat(finalCoordinatesList.get(1).getCardinalPoint(), equalTo(WEST));
		
		errorCollector.checkThat(finalCoordinatesList.get(2).getxCoordinate(), equalTo(-1));
		errorCollector.checkThat(finalCoordinatesList.get(2).getyCoordinate(), equalTo(3));
		errorCollector.checkThat(finalCoordinatesList.get(2).getCardinalPoint(), equalTo(EAST));
		
		try {
			Mockito.verify(textFileProcesses).readInputFile(IN01_TXT);
			Mockito.verify(textFileProcesses).generateDeliveryReport(ArgumentMatchers.anyList(), ArgumentMatchers.anyString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sendDeliveriesMaxConfiguredLunchesExceptionTest() {

		try {
			Mockito.when(textFileProcesses.readInputFile(IN01_TXT)).thenReturn(buildInputStringList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			deliveriesImpl.sendDeliveries();
		} catch (IOException | MaxConfiguredLunchesException e) {
			e.printStackTrace();
			assertEquals(MAX_LUNCHES_EXCEPTION, e.getMessage());
		}
		try {
			Mockito.verify(textFileProcesses).readInputFile(IN01_TXT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> buildInputStringList(){

		List<String> inputStringList = new ArrayList<>();
		inputStringList.add(new String("AAAAIAA"));
		inputStringList.add(new String("DDDAIAD"));
		inputStringList.add(new String("AAIADAD"));

		return inputStringList;
	}

}
