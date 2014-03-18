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
	 * Wirft den Würfel einmal und gibt den Wert zurück
	 * @return
	 */
	public byte wirfW6(){
		return (byte) Math.ceil(this.nextDouble()*6);
	}
	
	/**
	 * Wirft den Würfel einmal und gibt den Wert zurück
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
