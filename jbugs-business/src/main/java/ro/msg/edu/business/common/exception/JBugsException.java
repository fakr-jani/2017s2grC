package ro.msg.edu.business.common.exception;

/**
 * 
 * @author maresb
 *
 */
public class JBugsException extends Exception {
	private static final long serialVersionUID = -5628141671921410481L;

	public JBugsException(String message) {
		super(message);
	}

	public JBugsException(String message, Throwable cause) {
		super(message, cause);

	}

}
