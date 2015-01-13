/**
 * 
 */
package eu.sffi.dsa4.battle;

/**
 * Diese Klasse repräsentiert eine Waffe, die von einem Kämpfer geführt wird
 * @author besitzer
 *
 */
public class KampfWaffe {
	
	/**
	 * Der Name der Waffe
	 */
	String name;
	
	/**
	 * Die Distanzklassen der Waffe als Boolean-Array
	 */
	boolean[] distanzklasse;
	
	/**
	 * Der Initiative-Modifikator der Waffe
	 */
	byte iniModifikator;
	
	/**
	 * Anzahl an Schadenswürfeln der Waffe
	 */
	byte schadensWuerfel;
	
	/**
	 * Konstanter Schaden der Waffe
	 */
	byte schadensKonstante;
	
	/**
	 * Attackewert des Kämpfers mit der Waffe
	 */
	byte attacke;
	
	/**
	 * Paradewert des Kämpfers mit der Waffe
	 */
	byte parade;
	
}
