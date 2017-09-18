package ro.msg.edu.business.common.exception;

/**
 * Exception thrown when the format of an input does not meet the requirements
 * 
 * @author maresb
 *
 */
public class InvalidFormatException extends JBugsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFormatException(String message) {
		super(message);

	}

	public InvalidFormatException(String message, Throwable cause) {
		super(message, cause);

	}

}
