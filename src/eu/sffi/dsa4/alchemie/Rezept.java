/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import org.apache.commons.codec.binary.Base64;

import eu.sffi.dsa4.held.talente.WuerfelTalent;
import eu.sffi.dsa4.held.talente.WuerfelTalentWert;
import eu.sffi.dsa4.kalender.AventurischesDatum;
import eu.sffi.dsa4.util.Named;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;
import eu.sffi.dsa4.util.VerboseOut;
import eu.sffi.dsa4.wuerfel.W3Wurf;
import eu.sffi.dsa4.wuerfel.W6Wurf;
import eu.sffi.dsa4.wuerfel.Wuerfel;

/**
 * @author Andi Popp
 *
 */
public class Rezept implements Named<Rezept>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8991166731732262322L;
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
	public final String name;
	
	/**
	 * Trank welcher das Ergebnis der Rezeptur ist
	 */
	public final ElixierArt elixierArt;
	
//	TODO Zutatenrechner
//	/**
//	 * Liste der Zutaten
//	 */
//	final RezeptZutat[] zutaten;
	
	/**
	 * Der Beschaffungspreis der Zutaten in Silbertalern
	 */
	public final double beschaffungsPreis;
	
	/**
	 * Die Beschaffungswahrscheinlichkeit auf einem W20
	 */
	public final int beschaffungsWahrscheinlichkeit;
	
	/**
	 * Minimalanforderung an das Labor
	 */
	public final byte labor;
	
	/**
	 * Erschwernis auf die Alchemie-Probe für dieses Rezept
	 */
	public final int brauModifikator;
	
	/**
	 * Die Verbreitung der Rezeptur
	 */
	public final int verbreitung;

	/**
	 * Die Haltbarkeit des durch dieses Rezept erstellte Elixiers
	 */
	public final Haltbarkeit haltbarkeit;
	
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
			int verbreitung,
			Haltbarkeit haltbarkeit) {
		this.name = name;
		this.elixierArt = elixierArt;
//		this.zutaten = zutaten;
		this.beschaffungsPreis = beschaffungsPreis;
		this.beschaffungsWahrscheinlichkeit = beschaffungsWahrscheinlichkeit;
		this.labor = labor;
		this.brauModifikator = brauModifikator;
		this.verbreitung = verbreitung;
		this.haltbarkeit = haltbarkeit;
	}

	public static SimplePersistentNamedCollection<Rezept> STANDARDLISTE = getStandardListe();
	
	public static SimplePersistentNamedCollection<Rezept> getStandardListe(){
		SimplePersistentNamedCollection<Rezept> liste = new SimplePersistentNamedCollection<Rezept>();
		
		//TODO Alle Rezepte eintragen
		SimplePersistentNamedCollection<ElixierArt> listeElixierArten = ElixierArt.STANDARDLISTE;
		
		//Heilmittel
		liste.putObject(new Rezept(
				"Antidot (Standardrezept)", 						//name 
				listeElixierArten.getObject("Antidot"), 			//elixierArt 
				250, 												//beschaffungsPreis
				9, 													//beschaffungsWahrscheinlichkeit 
				LABOR_HEXENKUECHE, 									//labor, 
				5,													//brauModifikator 
				5,													//verbreitung 
				new Haltbarkeit(new W6Wurf(2,30), Haltbarkeit.MONATE)));	//haltbarkeit
		liste.putObject(new Rezept(
				"Furchtlos-Tropfen (Standardrezept)",				//name 
				listeElixierArten.getObject("Furchtlos-Tropfen"),	//elixierArt 
				100, 												//beschaffungsPreis
				12, 												//beschaffungsWahrscheinlichkeit 
				LABOR_ARCHAISCH, 									//labor, 
				3,													//brauModifikator 
				4,													//verbreitung 
				new Haltbarkeit(new W3Wurf(1,3), Haltbarkeit.MONATE)));	//haltbarkeit
		liste.putObject(new Rezept(
				"Heiltrank (Standardrezept)", 						//name 
				listeElixierArten.getObject("Heiltrank"), 			//elixierArt 
				50, 												//beschaffungsPreis
				14, 												//beschaffungsWahrscheinlichkeit 
				LABOR_ARCHAISCH, 									//labor, 
				2,													//brauModifikator 
				7,													//verbreitung 
				new Haltbarkeit(new W6Wurf(1,20), Haltbarkeit.MONATE)));	//haltbarkeit
		liste.putObject(new Rezept(
				"Pastillen gegen Erschöpfung (Standardrezept)", 	//name 
				listeElixierArten.getObject("Pastillen gegen Erschöpfung"), //elixierArt 
				50, 												//beschaffungsPreis
				11, 												//beschaffungsWahrscheinlichkeit 
				LABOR_HEXENKUECHE, 									//labor, 
				4,													//brauModifikator 
				6,													//verbreitung 
				new Haltbarkeit(new W3Wurf(1,7), Haltbarkeit.MONATE)));	//haltbarkeit
		liste.putObject(new Rezept(
				"Prophylaktika (Standardrezept)", 					//name 
				listeElixierArten.getObject("Prophylaktika"), 		//elixierArt 
				180, 												//beschaffungsPreis
				4, 													//beschaffungsWahrscheinlichkeit 
				LABOR_ALCHEMIELABOR,								//labor, 
				7,													//brauModifikator 
				4,													//verbreitung 
				new Haltbarkeit(new W3Wurf(1,4), Haltbarkeit.MONATE)));	//haltbarkeit
		liste.putObject(new Rezept(
				"Pulver des klaren Geistes (Standardrezept)", 						//name 
				listeElixierArten.getObject("Pulver des klaren Geistes"), 			//elixierArt 
				80, 												//beschaffungsPreis
				14, 												//beschaffungsWahrscheinlichkeit 
				LABOR_ARCHAISCH, 									//labor, 
				3,													//brauModifikator 
				4,													//verbreitung 
				new Haltbarkeit(new W6Wurf(1,3), Haltbarkeit.MONATE)));	//haltbarkeit
		liste.putObject(new Rezept(
				"Restorarium (Standardrezept)", 					//name 
				listeElixierArten.getObject("Restorarium"), 		//elixierArt 
				600, 												//beschaffungsPreis
				3, 													//beschaffungsWahrscheinlichkeit 
				LABOR_ALCHEMIELABOR,								//labor, 
				6,													//brauModifikator 
				3,													//verbreitung 
				new Haltbarkeit(new W6Wurf(3,3), Haltbarkeit.WOCHEN)));	//haltbarkeit
		liste.putObject(new Rezept(
				"Schlaftrunk (Standardrezept)", 					//name 
				listeElixierArten.getObject("Schlaftrunk"), 		//elixierArt 
				100, 												//beschaffungsPreis
				10, 												//beschaffungsWahrscheinlichkeit 
				LABOR_ARCHAISCH, 									//labor, 
				3,													//brauModifikator 
				5,													//verbreitung 
				new Haltbarkeit(new W3Wurf(1,10), Haltbarkeit.MONATE)));	//haltbarkeit
		
		return liste;
	}

	public Elixier brauen(WuerfelTalentWert talentWert, 
			byte labor, 
			byte laborQualitaet, 
			int modifikatorMeisterhandwerk,
			int sonstigerProbeModifikator,
			int zurueckgehalteneTAP, 
			int qualitaetsBonusASP, 
			int sonstigerQualitaetsModifikator,
			AventurischesDatum brauDatum,
			Wuerfel wuerfel) throws AlchemieException{
		VerboseOut.CONSOLE.println(talentWert.getHeld().name+" will das Rezept "+this.name+" brauen.");
		
		//Erstelle einen temporären Talentwert um die zurückgehaltenen TAP einzurechnen
		WuerfelTalentWert effektiverTalentWert = new WuerfelTalentWert((WuerfelTalent)talentWert.getTalent(), talentWert.getTAP()-zurueckgehalteneTAP, talentWert.getHeld());
		VerboseOut.CONSOLE.println(talentWert.getHeld().name+"s Talentwert in "+talentWert.getTalent().getName()+" beträgt "+talentWert.getTAP()+".");		
		VerboseOut.CONSOLE.println("Er/Sie hält "+zurueckgehalteneTAP+" TAP zurück. Der effektive Talentwert beträgt damit "+effektiverTalentWert.getTAP()+".");
		if (zurueckgehalteneTAP < 2 || zurueckgehalteneTAP > talentWert.getTAP()-this.brauModifikator){
			VerboseOut.CONSOLE.println("Die zurückgehaltenen TAP sind nicht gültig (mindestens 2, maximal TAP-Brauschwierigkeit). Probe wird abgebrochen.");
			VerboseOut.CONSOLE.println();
			throw new AlchemieException("Die zurückgehaltenen TAP sind nicht gültig (mindestens 2, maximal TAP-Brauschwierigkeit).");
		}
		
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
			throw new AlchemieException("Die Laborausstattung ist zwei Stufen schlechter als benötigt und damit nicht ausreichend um das Elixier zu brauen.");
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
	
		if (tapStern < 0){ //Probe misslungen
			VerboseOut.CONSOLE.println("Das Exlixier ist misslungen.");
			VerboseOut.CONSOLE.println();
			return new Elixier(name, this.elixierArt, Elixier.QUALITAET_M, this.haltbarkeit.getHaltbarkeitsDatum(brauDatum, wuerfel)); 
		}
		else{
			VerboseOut.CONSOLE.println("Das Elixier ist gelungen. Die Qualitätszahl wird berechnet.");
			int qualitaetszahl = wuerfel.wirfW6()+wuerfel.wirfW6();
			VerboseOut.CONSOLE.println("   "+qualitaetszahl+" (2W6)");
			VerboseOut.CONSOLE.println("  +"+tapStern+" (TAP*)");
			VerboseOut.CONSOLE.println("  +"+(2*zurueckgehalteneTAP)+" (2 x zurückgehaltene TAP)");
			if (qualitaetsBonusASP>0) VerboseOut.CONSOLE.println("  +"+qualitaetsBonusASP+" (Bonus durch den Einsatz von "+((int)Math.pow(2, qualitaetsBonusASP-1))+" ASP)");
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
			return new Elixier(name, this.elixierArt, qualitaet, this.haltbarkeit.getHaltbarkeitsDatum(brauDatum, wuerfel)); 			
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
	
	@Override
	public String getName(){
		return this.name;
	}
}
