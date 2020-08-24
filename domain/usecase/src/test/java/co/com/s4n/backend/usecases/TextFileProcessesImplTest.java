package co.com.s4n.backend.usecases;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import co.com.s4n.backend.model.FinalDeliveryAddress;

@RunWith(MockitoJUnitRunner.class)
public class TextFileProcessesImplTest{

	private static final String IN01_TXT = "in01.txt";
	private static final String NORTH = "NORTH";

	@InjectMocks
	private TextFileProcessesImpl textFileProcessesImpl;
	
	@Test
	public void readInputFileTest() {

		List<String> resultReadingList = null;
		try {
			resultReadingList = textFileProcessesImpl.readInputFile(IN01_TXT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertNotNull(resultReadingList);
	}
	
	@Test
	public void generateDeliveryReportTest() {
		
		try {
			textFileProcessesImpl.generateDeliveryReport(buildFinalList(), IN01_TXT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<FinalDeliveryAddress> buildFinalList(){
		
		List<FinalDeliveryAddress> finalList = new ArrayList<>();
		FinalDeliveryAddress finalDeliveryAddress = new FinalDeliveryAddress();
		finalDeliveryAddress.setxCoordinate(1);
		finalDeliveryAddress.setyCoordinate(1);
		finalDeliveryAddress.setCardinalPoint(NORTH);
		
		finalList.add(finalDeliveryAddress);
		
		return finalList;
	}
}
