package co.com.s4n.backend.model;

public class Lunch {

	private FinalDeliveryAddress finalDeliveryAddress;
	private String movements;

	public Lunch finalDeliveryAddress(FinalDeliveryAddress finalDeliveryAddress) {
		this.finalDeliveryAddress = finalDeliveryAddress;
		return this;
	}

	public Lunch movements(String movements) {
		this.movements = movements;
		return this;
	}

	public Lunch build(){
		Lunch lunch = new Lunch();
		lunch.finalDeliveryAddress = this.finalDeliveryAddress;
		lunch.movements = this.movements;
		return lunch;
	}

	public FinalDeliveryAddress getFinalDeliveryAddress() {
		return finalDeliveryAddress;
	}
	public void setFinalDeliveryAddress(FinalDeliveryAddress finalDeliveryAddress) {
		this.finalDeliveryAddress = finalDeliveryAddress;
	}
	public String getMovements() {
		return movements;
	}
	public void setMovements(String movements) {
		this.movements = movements;
	}
}
