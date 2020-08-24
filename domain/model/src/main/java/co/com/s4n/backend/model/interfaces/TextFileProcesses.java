package co.com.s4n.backend.model.interfaces;

import java.io.IOException;
import java.util.List;

import co.com.s4n.backend.model.FinalDeliveryAddress;

public interface TextFileProcesses {

	/**
	 * This method loads the input file with the indications to deliver the lunches
	 * @param movementsFileName
	 * @return The list of the indications
	 * @throws IOException
	 */
	List<String> readInputFile(String movementsFileName) throws IOException;

	/**
	 * This method generates the final report with the X and Y coordinates after the
	 * lunch is delivered
	 * @param finalAddresses
	 * @param inputFileName
	 * @throws IOException
	 */
	void generateDeliveryReport(List<FinalDeliveryAddress> finalAddresses, 
			String inputFileName) throws IOException;

}
