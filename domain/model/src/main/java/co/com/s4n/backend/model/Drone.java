package co.com.s4n.backend.model;

import java.util.List;

public class Drone {
	
	private List<Lunch> lunches;

	public Drone lunches (List<Lunch> lunches) {
		this.lunches = lunches;
		return this;
	}
	
	public Drone build(){
		Drone drone = new Drone();
		drone.lunches = this.lunches;
		return drone;
	}

	public List<Lunch> getLunches() {
		return lunches;
	}

	public void setLunches(List<Lunch> lunches) {
		this.lunches = lunches;
	}
}
