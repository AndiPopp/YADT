/**
 * 
 */
package eu.sffi.dsa4.held.talente;

/**
 * @author Andi Popp
 *
 */
public class TalentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6714272008103691580L;

	/**
	 * 
	 */
	public TalentException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TalentException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TalentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public TalentException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TalentException(Throwable cause) {
		super(cause);
	}

}
