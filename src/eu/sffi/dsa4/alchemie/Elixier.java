/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import eu.sffi.dsa4.kalender.AventurischesDatum;
import eu.sffi.dsa4.util.Named;

/**
 * @author Andi Popp
 * Eine Portion eines bestimmten Elixiers
 */
public class Elixier implements Named<Elixier>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6670573574052482712L;
	public static final byte QUALITAET_M = 0;
	public static final byte QUALITAET_A = 1;
	public static final byte QUALITAET_B = 2;
	public static final byte QUALITAET_C = 3;
	public static final byte QUALITAET_D = 4;
	public static final byte QUALITAET_E = 5;
	public static final byte QUALITAET_F = 6;
	
	public static char buchstabeQualitaet(byte qualitaet){
		switch (qualitaet){
			case QUALITAET_M: return 'M';
			case QUALITAET_A: return 'A';
			case QUALITAET_B: return 'B';
			case QUALITAET_C: return 'C';
			case QUALITAET_D: return 'D';
			case QUALITAET_E: return 'E';
			case QUALITAET_F: return 'F';
			default: return ' ';
		}
	}
	
	/**
	 * Der Name dieser Portion des Elixiers, wie er am Spieltisch zur Unterscheidung benutzt wird
	 */
	public final String name;
	
	/**
	 * Die Art des Elixiers
	 */
	public final ElixierArt art;
	
	/**
	 * Die Qualität des Elixiers
	 */
	public final byte qualitaet;

	/**
	 * Datum an dem das Elixiers verdirbt
	 */
	public final AventurischesDatum haltbarkeitsDatum;

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
	
	public char getBuchstabeQualitaet(){
		return buchstabeQualitaet(qualitaet);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Elixier)) return false;
		return this.name.equals(((Elixier)obj).name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int compareTo(Elixier o) {
		return this.getName().compareTo(o.getName());
	}
	
}
