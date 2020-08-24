package co.com.s4n.backend.model;

public class FinalDeliveryAddress {

	private int xCoordinate;
	private int yCoordinate;
	private String cardinalPoint;

	public int getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public String getCardinalPoint() {
		return cardinalPoint;
	}
	public void setCardinalPoint(String cardinalPoint) {
		this.cardinalPoint = cardinalPoint;
	}
	@Override
	public String toString() {
		return "DeliveryAddress [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + ", cardinalPoint="
				+ cardinalPoint + "]";
	}
}
