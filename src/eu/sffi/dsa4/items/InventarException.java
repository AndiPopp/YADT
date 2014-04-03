/**
 * 
 */
package eu.sffi.dsa4.items;

/**
 * Eine Exception die im Zusammenhang mit InventarOperationen geworfen wird
 * @author Andi Popp
 *
 */
public class InventarException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2509534179349649213L;

	/**
	 * 
	 */
	public InventarException() {
		
	}

	/**
	 * @param message
	 */
	public InventarException(String message) {
		super(message);
	}

}
