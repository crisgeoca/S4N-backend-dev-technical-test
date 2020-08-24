package co.com.s4n.backend.exceptions;

public class MaxBlocksAllowedException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4150648770014444185L;

	public MaxBlocksAllowedException(String message) {
		super(message);
	}

	public MaxBlocksAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

}
