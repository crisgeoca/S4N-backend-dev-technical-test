package co.com.s4n.backend.exceptions;

public class MaxConfiguredLunchesException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3361014070021559603L;

	public MaxConfiguredLunchesException(String message) {
		super(message);
	}

	public MaxConfiguredLunchesException(String message, Throwable cause) {
		super(message, cause);
	}

}
