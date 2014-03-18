/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import org.apache.commons.codec.binary.Base64;

import eu.sffi.dsa4.held.talente.WuerfelTalent;
import eu.sffi.dsa4.held.talente.WuerfelTalentWert;
import eu.sffi.dsa4.kalender.AventurischesDatum;
import eu.sffi.dsa4.util.VerboseOut;
import eu.sffi.dsa4.wuerfel.Wuerfel;

/**
 * @author Andi Popp
 *
 */
public class Rezept implements Comparable<Rezept>{

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
	
//	TODO Zutatenrechner
//	/**
//	 * Liste der Zutaten
//	 */
//	final RezeptZutat[] zutaten;
	
	/**
	 * Der Beschaffungspreis der Zutaten in Silbertalern
	 */
	final double beschaffungsPreis;
	
	/**
	 * Die Beschaffungswahrscheinlichkeit auf einem W20
	 */
	final int beschaffungsWahrscheinlichkeit;
	
	/**
	 * Minimalanforderung an das Labor
	 */
	final byte labor;
	
	/**
	 * Erschwernis auf die Alchemie-Probe für dieses Rezept
	 */
	final int brauModifikator;
	
	/**
	 * Die Verbreitung der Rezeptur
	 */
	final int verbreitung;

	
	
	/**
	 * @param name
	 * @param elixierArt
	 * @param zutaten
	 * @param beschaffung
	 * @param labor
	 * @param brauModifikator
	 * @param verbreitung
	 */
	public Rezept(String name, 
			ElixierArt elixierArt, 
//			RezeptZutat[] zutaten,
			double beschaffungsPreis,
			int beschaffungsWahrscheinlichkeit,
			byte labor, 
			int brauModifikator,
			int verbreitung) {
		this.name = name;
		this.elixierArt = elixierArt;
//		this.zutaten = zutaten;
		this.beschaffungsPreis = beschaffungsPreis;
		this.beschaffungsWahrscheinlichkeit = beschaffungsWahrscheinlichkeit;
		this.labor = labor;
		this.brauModifikator = brauModifikator;
		this.verbreitung = verbreitung;
	}



	public Elixier brauen(WuerfelTalentWert talentWert, 
			byte labor, 
			byte laborQualitaet, 
			int modifikatorMeisterhandwerk,
			int sonstigerProbeModifikator,
			int zurueckgehalteneTAP, 
			int qualitaetsBonusASP, 
			int sonstigerQualitaetsModifikator,
			AventurischesDatum BrauDatum,
			Wuerfel wuerfel){
		VerboseOut.CONSOLE.println(talentWert.getHeld().name+" will das Rezept "+this.name+" brauen.");
		
		//Erstelle einen temporären Talentwert um die zurückgehaltenen TAP einzurechnen
		WuerfelTalentWert effektiverTalentWert = new WuerfelTalentWert((WuerfelTalent)talentWert.getTalent(), talentWert.getTAP()-zurueckgehalteneTAP, talentWert.getHeld());
		VerboseOut.CONSOLE.println(talentWert.getHeld().name+"s Talentwert in "+talentWert.getTalent().getName()+" beträgt "+talentWert.getTAP()+".");		
		VerboseOut.CONSOLE.println("Er/Sie behält "+zurueckgehalteneTAP+" TAP ein. Der effektive Talentwert beträgt damit "+effektiverTalentWert.getTAP()+".");
		
		//PROBEMODIFIKATOREN
		int modifikator = 0;
		//Meisterhandwerk
		modifikator -= modifikatorMeisterhandwerk;
		VerboseOut.CONSOLE.println("Durch Meisterhandwerk wird die Probe um "+modifikatorMeisterhandwerk+" Punkt erleichtert."+" (Summe der Modifikationen bisher "+modifikator+")");
		//Modifikatoren für die Laborstufe
		if (labor-this.labor == 2) { //zwei stufen besser
			modifikator -= 3; 
			VerboseOut.CONSOLE.println("Die Laborausstattung ist zwei Stufen besser als benötigt, die Probe damit um 3 Punkt erleichtert."+" (Summe der Modifikationen bisher "+modifikator+")");
		}
		else if (labor-this.labor == -1) { //eine stufe schlechter
			modifikator += 7; 
			VerboseOut.CONSOLE.println("Die Laborausstattung ist eine Stufe schlechter als benötigt, die Probe damit um 7 Punkt erschwert."+" (Summe der Modifikationen bisher "+modifikator+")");
		}
		else if (labor-this.labor < -1) { //zwei Stufen schlechter
			VerboseOut.CONSOLE.println("Die Laborausstattung ist zwei Stufen schlechter als benötigt, die Probe wird abgebrochen. Es wurde kein Trank (null) gebraut."+" (Summe der Modifikationen bisher "+modifikator+")");
			VerboseOut.CONSOLE.println();
			return null; 
		}
		else{ //genau richtig
			VerboseOut.CONSOLE.println("Die Laborausstattung ist genau ausreichend, die Probe wird dadurch nicht modifiziert."+" (Summe der Modifikationen bisher "+modifikator+")");
		}
		
		//Modifikatoren für Laborqualität
		if (laborQualitaet == LABOR_QUALITAET_SCHLECHT) {
			modifikator += 3;
			VerboseOut.CONSOLE.println("Die Laborqualität ist schlecht, die Probe wird um 3 Punkt erschwert."+" (Summe der Modifikationen bisher "+modifikator+")");
		}
		else if (laborQualitaet == LABOR_QUALITAET_HOCHWERTIG){
			modifikator -= 3;
			VerboseOut.CONSOLE.println("Die Laborqualität ist hochwertig, die Probe wird um 3 Punkt erleichtert."+" (Summe der Modifikationen bisher "+modifikator+")");
		}
		else if (laborQualitaet == LABOR_QUALITAET_AUSSERGEWOEHNLICH){
			modifikator -= 7;
			VerboseOut.CONSOLE.println("Die Laborqualität ist außergewöhnlich hochwertig, die Probe wird um 7 Punkt erleichtert."+" (Summe der Modifikationen bisher "+modifikator+")");
		}
		else{
			VerboseOut.CONSOLE.println("Die Laborqualität ist normal. Die Probe wird dadurch nicht modifiziert"+" (Summe der Modifikationen bisher "+modifikator+")");
		}
		
		//Brau-Modifikator des Rezept
		modifikator += this.brauModifikator;
		VerboseOut.CONSOLE.println("Das Rezept hat einen Aufschlag für die Brauschwierigkeit von "+this.brauModifikator+" (Summe der Modifikationen bisher "+modifikator+")");
		
		
		//Sonstiger Modifikator
		modifikator += sonstigerProbeModifikator;
		VerboseOut.CONSOLE.println("Sonstige Erschwerungen betragen "+sonstigerProbeModifikator+" Punkte."+" (Summe der Modifikationen bisher "+modifikator+")");
		
		
		//Gesamten Modifikator ausgeben
		VerboseOut.CONSOLE.println("Insgesamt ist die "+talentWert.getTalent().getName()+"-Probe um "+modifikator+" Punkte erschwert.");

		
		//Probe auf den effektiven Talentwert werfen
		int tapStern = effektiverTalentWert.werfen(wuerfel, modifikator);
		
		//Ergebnis
		//Erstelle einen zufälligen base64-String um eindeutige Namen zu vergeben
		byte[] zufallsbytes = new byte[3];
		wuerfel.nextBytes(zufallsbytes);
		String name = this.elixierArt.name+" (ID: "+Base64.encodeBase64String(zufallsbytes)+")";
		
		
		
		//TODO Haltbarkeit
		if (tapStern < 0){ //Probe misslungen
			VerboseOut.CONSOLE.println("Das Exlixier ist misslungen.");
			VerboseOut.CONSOLE.println();
			return new Elixier(name, this.elixierArt, Elixier.QUALITAET_M, new AventurischesDatum(0)); 
		}
		else{
			VerboseOut.CONSOLE.println("Das Elixier ist gelungen. Die Qualitätszahl wird berechnet.");
			int qualitaetszahl = wuerfel.wirfW6()+wuerfel.wirfW6();
			VerboseOut.CONSOLE.println("   "+qualitaetszahl+" (2W6)");
			VerboseOut.CONSOLE.println("  +"+tapStern+" (TAP*)");
			if (qualitaetsBonusASP>0) VerboseOut.CONSOLE.println("  +"+qualitaetsBonusASP+" (Bonus durch den Einsatz von +"+(Math.pow(2, qualitaetsBonusASP-1))+")");
			VerboseOut.CONSOLE.println("  +"+sonstigerQualitaetsModifikator+" (Sonstige Qualitätsmodifikatoren)");
			qualitaetszahl += tapStern 
				+ (2*zurueckgehalteneTAP) 
				+ qualitaetsBonusASP
				+ sonstigerQualitaetsModifikator;
			VerboseOut.CONSOLE.print("  ="+qualitaetszahl+". ");
			byte qualitaet = 0;
			if (qualitaetszahl < 7) qualitaet = Elixier.QUALITAET_A;
			else if (qualitaetszahl < 13) qualitaet = Elixier.QUALITAET_B;
			else if (qualitaetszahl < 19) qualitaet = Elixier.QUALITAET_C;
			else if (qualitaetszahl < 25) qualitaet = Elixier.QUALITAET_D;
			else if (qualitaetszahl < 31) qualitaet = Elixier.QUALITAET_E;
			else qualitaet = Elixier.QUALITAET_F;
			VerboseOut.CONSOLE.ammendln("Dies entspricht einer Qualität von "+Elixier.buchstabeQualitaet(qualitaet)+".");
			
			VerboseOut.CONSOLE.println();
			return new Elixier(name, this.elixierArt, qualitaet, new AventurischesDatum(0)); 			
		}
	}

	@Override
	public int compareTo(Rezept o) {
		return this.name.compareToIgnoreCase(o.name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Rezept)) return false;
		return this.name.equals(((Rezept)obj).name);
	}
}
