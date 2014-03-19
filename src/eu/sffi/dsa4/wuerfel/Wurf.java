/**
 * 
 */
package eu.sffi.dsa4.wuerfel;

import java.io.Serializable;

/**
 * @author Andi Popp
 *
 */
public class Wurf implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3901400696867805975L;

	public final int anzahlW3;
	
	public final int anzahlW6;
	
	public final int anzahlW10;
	
	public final int anzahlW20;
	
	public final int konstante;

	/**
	 * @param anzahlW6
	 * @param anzahlW20
	 * @param konstante
	 */
	public Wurf(int anzahlW3, int anzahlW6, int anzahlW10, int anzahlW20, int konstante) {
		this.anzahlW3 = anzahlW3;
		this.anzahlW6 = anzahlW6;
		this.anzahlW10 = anzahlW10;
		this.anzahlW20 = anzahlW20;
		this.konstante = konstante;
	}
	
	public int werfen(Wuerfel wuerfel){
		int ergebnis = konstante;
		for (int i = 0; i < anzahlW3; i++){
			ergebnis += wuerfel.wirfW3();
		}
		for (int i = 0; i < anzahlW6; i++){
			ergebnis += wuerfel.wirfW6();
		}
		for (int i = 0; i < anzahlW10; i++){
			ergebnis += wuerfel.wirfW10();
		}
		for (int i = 0; i < anzahlW20; i++){
			ergebnis += wuerfel.wirfW20();
		}
		return ergebnis;
	}
	
}
