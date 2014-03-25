/**
 * 
 */
package eu.sffi.dsa4.kalender;

import java.io.Serializable;

/**
 * @author Andi Popp
 *
 */
public class AventurischerMonat implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4299141063044336669L;
	
	/**
	 * Der numerische Wert des Monats
	 */
	public final byte wert;

	public AventurischerMonat(byte wert) {
		super();
		this.wert = wert;
	}
	
	public static final AventurischerMonat[] getMonate(){
		AventurischerMonat[] monate = new AventurischerMonat[13];
		for (byte i = 0; i < monate.length; i++){
			monate[i] = new AventurischerMonat((byte)(i+1));
		}
		return monate;
	}
	
	public String toString(){
		return AventurischerMonat.translateMonat(wert);
	}



	/**
	 * Übersetzt einen numerischen Monat in einen Göttermonat
	 * @param monat
	 * @return Ein String der die dreibuchstabige Abkürzung des Gottes darstellt
	 */
	public static String translateMonat(byte monat){
		switch (monat){
			case  1: return "PRA";
			case  2: return "RON";
			case  3: return "EFF";
			case  4: return "TRA";
			case  5: return "BOR";
			case  6: return "HES";
			case  7: return "FIR";
			case  8: return "TSA";
			case  9: return "PHE";
			case 10: return "PER";
			case 11: return "ING";
			case 12: return "RAH";
			case 13: return "NAM";
			default: throw new IllegalArgumentException("Fehler beim Übersetzen eines Monats. Muss zwischen 1 und 13 liegen");
		}
	}
}
