/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import eu.sffi.dsa4.kalender.AventurischesDatum;

/**
 * @author Andi Popp
 * Eine Portion eines bestimmten Elixiers
 */
public class Elixier {

	public static final byte QUALITAET_M = 0;
	public static final byte QUALITAET_A = 1;
	public static final byte QUALITAET_B = 2;
	public static final byte QUALITAET_C = 3;
	public static final byte QUALITAET_D = 4;
	public static final byte QUALITAET_E = 5;
	public static final byte QUALITAET_F = 6;
	
	/**
	 * Der Name dieser Portion des Elixiers, wie er am Spieltisch zur Unterscheidung benutzt wird
	 */
	String name;
	
	/**
	 * Die Art des Elixiers
	 */
	ElixierArt art;
	
	/**
	 * Die Qualität des Elixiers
	 */
	byte qualitaet;

	/**
	 * Datum an dem das Elixiers verdirbt
	 */
	AventurischesDatum haltbarkeitsDatum;

	/**
	 * @param name
	 * @param art
	 * @param qualitaet
	 * @param haltbarkeitsDatum
	 */
	public Elixier(String name, ElixierArt art, byte qualitaet,
			AventurischesDatum haltbarkeitsDatum) {
		this.name = name;
		this.art = art;
		this.qualitaet = qualitaet;
		this.haltbarkeitsDatum = haltbarkeitsDatum;
	}
	
	
	
}
