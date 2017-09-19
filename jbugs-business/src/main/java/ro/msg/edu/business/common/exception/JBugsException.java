package ro.msg.edu.business.common.exception;

/**
 * 
 * @author maresb
 *
 */
public class JBugsException extends Exception {
	private static final long serialVersionUID = -5628141671921410481L;

	private final String message;

	public JBugsException(String message) {
		super();
		this.message = message;
	}

	public JBugsException(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
