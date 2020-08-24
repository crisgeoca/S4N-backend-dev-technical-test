package co.com.s4n.backend.usecases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.com.s4n.backend.model.FinalDeliveryAddress;
import co.com.s4n.backend.model.interfaces.TextFileProcesses;

public class TextFileProcessesImpl implements TextFileProcesses{

	@Override
	public List<String> readInputFile(String movementsFileName) throws IOException {

		List<String> resultReadingList = new ArrayList<>();
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource(movementsFileName).getFile());
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;  
		while((line = bufferedReader.readLine())!=null) {
			resultReadingList.add(line);
		}
		fileReader.close();
		bufferedReader.close();		
		return resultReadingList;
	}

	@Override
	public void generateDeliveryReport(List<FinalDeliveryAddress> finalAddresses, String inputFileName) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("out".
				concat(inputFileName.substring(2, inputFileName.indexOf('.')).
						concat(".txt"))));
		finalAddresses.forEach(finalDeliveryAddress -> {
			try {
				writer.write("("+
						finalDeliveryAddress.getxCoordinate()+
						","+finalDeliveryAddress.getyCoordinate()+
						") to "+
						finalDeliveryAddress.getCardinalPoint()+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		writer.close();
	}
}
