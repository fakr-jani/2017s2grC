package ro.msg.edu.business.common.exception;

<<<<<<< HEAD
/**
 * 
 * @author maresb
 *
 */
public class BusinessException extends JBugsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);

=======
public class BusinessException extends Exception {

	private static final long serialVersionUID = -5628141671921410481L;

	private String message;

	public BusinessException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
	}

}
