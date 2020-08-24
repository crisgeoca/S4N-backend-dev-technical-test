package co.com.s4n.backend;

import java.util.ArrayList;
import java.util.List;

import co.com.s4n.backend.usecases.DeliveriesImpl;
import co.com.s4n.backend.usecases.TextFileProcessesImpl;

public class RunApplication {

	public static void main(String[] args) {

		int numberOfDrones = 20;
		int blocksAllowed = 10;
		int maxLunches = 3;
		//This list can be filled for example reading a directory
		//selected on the UI
		List<String> inputAddressesList = new ArrayList<>();
		inputAddressesList.add("in01.txt");

		//Here I check if the number of desired drones
		//can manage the number of input files, so in the case
		//the number of input files is higher than number of
		//drones, the process won't start
		if(inputAddressesList.size() <= numberOfDrones) {
			inputAddressesList.parallelStream().forEach(inputFile -> 
			new DeliveriesImpl(new TextFileProcessesImpl(), inputFile, maxLunches, blocksAllowed).start());
		}
	}

}
