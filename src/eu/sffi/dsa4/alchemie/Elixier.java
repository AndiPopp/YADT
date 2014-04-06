/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import eu.sffi.dsa4.items.InventarException;
import eu.sffi.dsa4.items.Item;
import eu.sffi.dsa4.items.Verbrauchbar;
import eu.sffi.dsa4.kalender.AventurischesDatum;

/**
 * Eine Portion eines bestimmten Elixiers
 * @author Andi Popp
 */
public class Elixier extends Item implements Verbrauchbar{

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
	 * Die Art des Elixiers
	 */
	public final ElixierArt art;
	
	/**
	 * Die Qualität des Elixiers
	 */
	public final byte qualitaet;

	/**
	 * Datum an dem das Elixier verdirbt
	 */
	public final AventurischesDatum haltbarkeitsDatum;
	
	/**
	 * Die Anzahl der Anwendungen, hier 1 oder 0
	 */
	private int anwendungen;
	
	/**
	 * @param name
	 * @param art
	 * @param qualitaet
	 * @param haltbarkeitsDatum
	 */
	public Elixier(String name, ElixierArt art, byte qualitaet,
			AventurischesDatum haltbarkeitsDatum) {
		super(100, name);
		this.art = art;
		this.qualitaet = qualitaet;
		this.haltbarkeitsDatum = haltbarkeitsDatum;
		this.anwendungen = 1;
	}
	
	public char getBuchstabeQualitaet(){
		return buchstabeQualitaet(qualitaet);
	}
	
	//Overrides
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Elixier)) return false;
		return this.getName().equals(((Elixier)obj).getName());
	}
	
	@Override
	public String getItemTyp(){
		return "Elixier";
	}

	@Override
	public boolean verbrauchen() throws InventarException{
		if (this.anwendungen < 1) throw new InventarException("Das Item "+this.getName()+" ist bereits vollständig verbraucht.");
		this.anwendungen--;
		return true;
	}

	@Override
	public int getAwendungen() {
		return this.anwendungen;
	}
}
