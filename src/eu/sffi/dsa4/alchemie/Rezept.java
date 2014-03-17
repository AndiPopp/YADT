/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import eu.sffi.dsa4.kalender.AventurischesDatum;
import eu.sffi.dsa4.wuerfel.Wuerfel;

/**
 * @author Andi Popp
 *
 */
public class Rezept {

	public static final byte LABOR_ARCHAISCH = 0;
	public static final byte LABOR_HEXENKUECHE = 1;
	public static final byte LABOR_ALCHEMIELABOR = 2;
	
	public static final byte LABOR_QUALITAET_SCHLECHT = 0;
	public static final byte LABOR_QUALITAET_NORMAL = 1;
	public static final byte LABOR_QUALITAET_HOCHWERTIG = 2;
	public static final byte LABOR_QUALITAET_AUSSERGEWOEHNLICH = 3;
	
	/**
	 * Der Name des Rezepts
	 */
	final String name;
	
	/**
	 * Trank welcher das Ergebnis der Rezeptur ist
	 */
	final ElixierArt elixierArt;
	
	/**
	 * Liste der Zutaten
	 */
	final RezeptZutat[] zutaten;
	
	/**
	 * Der Beschaffungspreis der Zutaten in Silbertalern
	 */
	final double beschaffung;
	
	/**
	 * Minimalanforderung an das Labor
	 */
	final byte labor;
	
	/**
	 * Erschwernis auf die Alchemie-Probe für dieses Rezept
	 */
	final byte brauModifikator;
	
	/**
	 * Die Verbreitung der Rezeptur
	 */
	final byte verbreitung;

	
	
	/**
	 * @param name
	 * @param elixierArt
	 * @param zutaten
	 * @param beschaffung
	 * @param labor
	 * @param brauModifikator
	 * @param verbreitung
	 */
	public Rezept(String name, ElixierArt elixierArt, RezeptZutat[] zutaten,
			double beschaffung, byte labor, byte brauModifikator,
			byte verbreitung) {
		this.name = name;
		this.elixierArt = elixierArt;
		this.zutaten = zutaten;
		this.beschaffung = beschaffung;
		this.labor = labor;
		this.brauModifikator = brauModifikator;
		this.verbreitung = verbreitung;
	}



	public Elixier brauen(Alchemist alchemist, 
			byte labor, 
			byte laborQualitaet, 
			byte modifikatorMeisterhandwerk,
			byte sonstigerProbeModifikator,
			byte zurueckgehalteneTAP, 
			byte qualitaetsBonusASP, 
			byte sonstigerQualitaetsModifikator,
			boolean benutztKochen){
		
		byte tap = 0;//TAP
		if (benutztKochen) tap = alchemist.TAW_Kochen;
		else tap = alchemist.TAW_Alchemie;
		byte tapStern = tap;//TAP*
		
		//PROBEMODIFIKATOREN
		//Zurückgehaltene TAP
		tapStern -= zurueckgehalteneTAP;
		//Meisterhandwerk
		tapStern += modifikatorMeisterhandwerk;
		//Modifikatoren für die Laborstufe
		if (labor-this.labor == 2) tapStern += 3; //zwei stufen besser
		else if (labor-this.labor == -1) tapStern -= 7; //eine stufe schlechter
		else if (labor-this.labor < -1) return null; //zwei Stufen schlechter //TODO Exception für Labor erstellen?
		//Modifikatoren für Laborqualität
		if (laborQualitaet == LABOR_QUALITAET_SCHLECHT) tapStern -= 3;
		else if (laborQualitaet == LABOR_QUALITAET_HOCHWERTIG) tapStern += 3;
		else if (laborQualitaet == LABOR_QUALITAET_AUSSERGEWOEHNLICH) tapStern += 7;
		
		//Probe auf MU bzw. IN
		byte wurf = 0;
		wurf = Wuerfel.wirfW20();
		if (benutztKochen){
			if (wurf > alchemist.in) tapStern -= wurf - alchemist.in;
		}
		else{
			if (wurf > alchemist.mu) tapStern -= wurf - alchemist.mu;
		}
		//Probe auf KL
		wurf = Wuerfel.wirfW20();
		if (wurf > alchemist.kl) tapStern -= wurf - alchemist.kl;
		//Probe auf KL
		wurf = Wuerfel.wirfW20();
		if (wurf > alchemist.ff) tapStern -= wurf - alchemist.ff;
		
		//TAP* auf TAW-Zurückgehaltene Punkte begrenzen
		if (tapStern > tap - zurueckgehalteneTAP) tapStern = (byte) (tap - zurueckgehalteneTAP);
		
		//Ergebnis
		//TODO eindeutige Namen vergeben 
		//TODO Haltbarkeit
		if (tapStern < 0){ //Probe misslungen
			return new Elixier(this.elixierArt.name, this.elixierArt, Elixier.QUALITAET_M, new AventurischesDatum(0)); 
		}
		else{
			int qualitaetszahl = 
				Wuerfel.wirfW6() 
				+ Wuerfel.wirfW6() 
				+ tapStern 
				+ (2+zurueckgehalteneTAP) 
				+ qualitaetsBonusASP
				+ sonstigerQualitaetsModifikator;
			byte qualitaet = 0;
			if (qualitaetszahl < 7) qualitaet = Elixier.QUALITAET_A;
			else if (qualitaetszahl < 13) qualitaet = Elixier.QUALITAET_B;
			else if (qualitaetszahl < 19) qualitaet = Elixier.QUALITAET_C;
			else if (qualitaetszahl < 25) qualitaet = Elixier.QUALITAET_D;
			else if (qualitaetszahl < 31) qualitaet = Elixier.QUALITAET_E;
			else qualitaet = Elixier.QUALITAET_F;
			
			//TODO eindeutige Namen vergeben 
			//TODO Haltbarkeit
			return new Elixier(this.elixierArt.name, this.elixierArt, qualitaet, new AventurischesDatum(0)); 			
		}
	}
}
