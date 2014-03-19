/**
 * 
 */
package eu.sffi.dsa4.wuerfel;

import java.util.Random;

/**
 * @author Andi Popp
 *
 */
public class Wuerfel extends Random{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1145508868864271187L;

	/**
	 * Wirft einen W3 und gibt den Wert zurück
	 * @return
	 */
	public byte wirfW3(){
		return (byte) Math.ceil(this.nextDouble()*3);
	}
	
	/**
	 * Wirft einen W6 einmal und gibt den Wert zurück
	 * @return
	 */
	public byte wirfW6(){
		return (byte) Math.ceil(this.nextDouble()*6);
	}
	
	/**
	 * Wirft einen W10 einmal und gibt den Wert zurück
	 * @return
	 */
	public byte wirfW10(){
		return (byte) Math.ceil(this.nextDouble()*10);
	}
	
	/**
	 * Wirft einen W20 einmal und gibt den Wert zurück
	 * @return
	 */
	public byte wirfW20(){
		return (byte) Math.ceil(this.nextDouble()*20);
	}
		
	/**
	 * Simpler W6 Wurf basierend auf Math.random()
	 * @return eine ganzzahlige Zufallszahl zwischen 1 und 6
	 */
	public static byte wirfStaticW6(){
		return (byte) Math.ceil((Math.random()*6));
	}
	
	/**
	 * Simpler W6 Wurf basierend auf Math.random()
	 * @return eine ganzzahlige Zufallszahl zwischen 1 und 20
	 */
	public static byte wirfStaticW20(){
		return (byte) Math.ceil((Math.random()*20));
	}
}
