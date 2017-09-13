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

	private static final String MESSAGE_KEY = "exception.technical";


	public InvalidFormatException(String message) {
		super(MESSAGE_KEY);

	}


	public InvalidFormatException(String message, Throwable cause) {
		super(MESSAGE_KEY, cause);

	}

}
