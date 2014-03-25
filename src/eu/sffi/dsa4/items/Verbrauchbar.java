/**
 * 
 */
package eu.sffi.dsa4.items;

/**
 * @author Andi Popp
 *
 */
public interface Verbrauchbar {

	/**
	 * Wird aufgerufen wenn das Item verbraucht werden soll. Da es
	 * auch Items mit mehreren Anwedungen kann, gibt die Funktion zurück
	 * ob das Item vollständig verbraucht ist.
	 * @return true wenn das Item vollständig verbraucht ist, false sonst
	 */
	public boolean verbrauchen();
	
	/**
	 * Gibt die Anzahl noch verbleibender Anwendungen für dieses verbrauchbare
	 * Objekt aus.
	 * @return die Anzahl der verbleibenden Anwendungen
	 */
	public int getAwendungen();
}
