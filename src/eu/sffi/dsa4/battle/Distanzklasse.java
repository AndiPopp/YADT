/**
 * 
 */
package eu.sffi.dsa4.battle;

/**
 * @author besitzer
 *
 */
public class Distanzklasse {
	
	/**
	 * Repräsentation der Distanzklasse H
	 */
	public static final byte H = 0;
	public static final byte N = 1;
	public static final byte S = 2;
	public static final byte P = 3;
	
	/**
	 * Die Distanzklasse als byte
	 */
	public byte distanz;
	
	/**
	 * Vollständig parametrisierter Konstruktor
	 * @param distanz
	 */
	public Distanzklasse(byte distanz) throws IllegalArgumentException{
		if (distanz < 0 || distanz > 3) throw new IllegalArgumentException("Distanzklassen können nur mit den Werten 0 bis 3 konstruiert werden");
		this.distanz = distanz;
	}

	/**
	 * Gibt die Distanzklasse in einem String aus, welcher der regeltechnischen 
	 * Bezeichnung entspricht (H, N, S, P).
	 * @return "H", "N", "S", "P" oder "" im Falle ungültiger numerischer Distanz
	 */
	public String toString(){
		return toString(this.distanz);
	}
	
	/**
	 * Gibt die eingegebene numerische Distanzklasse in einem String aus, welcher der regeltechnischen 
	 * Bezeichnung entspricht (H, N, S, P).
	 * @param distanz numerische Distanz
	 * @return "H", "N", "S", "P" oder "" im Falle ungültiger numerischer Distanz
	 */
	public static String toString(byte distanz){
		switch (distanz){
			case 0: return "H";
			case 1: return "N";
			case 2: return "S";
			case 3: return "P";
			default: return "";
		}
			
	}
	
	/**
	 * Gibt die Distanzklasse in einem String aus, welcher der vollständigen regeltechnischen 
	 * Bezeichnung entspricht (H, N, S, P).
	 * @param distanz numerische Distanz
	 * @return "Handgemenge", "Nahkampf", "Stangenwaffe", "Pike" oder "" im Falle ungültiger numerischer Distanz
	 */
	public String toFullString(){
		return toFullString(this.distanz);
	}
	
	/**
	 * Gibt die eingegebene numerische Distanzklasse in einem String aus, welcher der vollständigen regeltechnischen 
	 * Bezeichnung entspricht (H, N, S, P).
	 * @param distanz numerische Distanz
	 * @return "Handgemenge", "Nahkampf", "Stangenwaffe", "Pike" oder "" im Falle ungültiger numerischer Distanz
	 */
	public static String toFullString(byte distanz){
		switch (distanz){
			case 0: return "Handgemenge";
			case 1: return "Nahkampf";
			case 2: return "Stangenwaffe";
			case 3: return "Pike";
			default: return "";
		}
			
	}
}
