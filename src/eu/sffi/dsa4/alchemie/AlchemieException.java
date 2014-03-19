/**
 * 
 */
package eu.sffi.dsa4.alchemie;

/**
 * @author Andi Popp
 *
 */

public class AlchemieException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3902616630635067551L;

	/**
	 * 
	 */
	public AlchemieException() {
	}

	/**
	 * @param message
	 */
	public AlchemieException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AlchemieException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AlchemieException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AlchemieException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
