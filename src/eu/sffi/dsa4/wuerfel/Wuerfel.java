/**
 * 
 */
package eu.sffi.dsa4.wuerfel;

import java.util.Random;

/**
 * @author Andi Popp
 *
 */
public abstract class Wuerfel {
	
	Random zufallsgenerator;
	
	/**
	 * Wirft den Würfel einmal und gibt den Wert zurück
	 * @return
	 */
	public abstract byte werfen();
	
	/**
	 * Simpler W6 Wurf basierend auf Math.random()
	 * @return eine ganzzahlige Zufallszahl zwischen 1 und 6
	 */
	public static byte wirfW6(){
		return (byte) Math.ceil((Math.random()*6));
	}
	
	/**
	 * Simpler W6 Wurf basierend auf Math.random()
	 * @return eine ganzzahlige Zufallszahl zwischen 1 und 20
	 */
	public static byte wirfW20(){
		return (byte) Math.ceil((Math.random()*20));
	}
}
