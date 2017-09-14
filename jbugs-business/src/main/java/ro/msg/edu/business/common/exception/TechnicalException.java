package ro.msg.edu.business.common.exception;

public class TechnicalException extends JBugsException {

	private static final String MESSAGE_KEY = "exception.technical";

	public TechnicalException(String message) {
		super(message);

	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);

	}

}
