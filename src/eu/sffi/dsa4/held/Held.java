/**
 * 
 */
package eu.sffi.dsa4.held;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import eu.sffi.dsa4.held.talente.Talent;
import eu.sffi.dsa4.held.talente.TalentException;
import eu.sffi.dsa4.held.talente.TalentWert;
import eu.sffi.dsa4.util.Named;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;

/**
 * @author Andi Popp
 *
 */
public class Held implements Named<Held>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7924090737926296115L;

	/**
	 * Der Index für den Mut-Wert
	 */
	public static final byte MU = 0;
	
	/**
	 * Der Index für den Klugheits-Wert
	 */
	public static final byte KL = 1;
	
	/**
	 * Der Index für den Intuitions-Wert
	 */
	public static final byte IN = 2;
	
	/**
	 * Der Index für den Charisma-Wert
	 */
	public static final byte CH = 3;
	
	/**
	 * Der Index für den Fingerfertigkeits-Wert
	 */
	public static final byte FF = 4;
	
	/**
	 * Der Index für den Gewandheits-Wert
	 */
	public static final byte GE = 5;
	
	/**
	 * Der Index für den Konstitutions-Wert
	 */
	public static final byte KO = 6;
	
	/**
	 * Der Index für den Körperkraft-Wert
	 */
	public static final byte KK = 7;
	
	/**
	 * Gibt den zu einer Eigenschafts-Konstante korrespondierenden String aus
	 * @param eigenschaft Die Eigenschafts-Konstante
	 * @return 
	 */
	public static final String EIGENSCHAFT(byte eigenschaft){
		switch (eigenschaft){
			case MU: return "MU";
			case KL: return "KL";
			case IN: return "IN";
			case CH: return "CH";
			case FF: return "FF";
			case GE: return "GE";
			case KO: return "KO";
			case KK: return "KK";
			default: return "";
		}
	}
	
	/**
	 * Gibt eine leere Heldenliste zurück
	 * @return eine leere Heldenliste
	 */
	public static final SimplePersistentNamedCollection<Held> emptyHeldenListe(){
		return new SimplePersistentNamedCollection<Held>();
	}
	
	/**
	 * Feld der Eigenschaftswerte
	 */
	public final int[] eigenschaft = new int[8];
	
	/**
	 * Temporäre Modifikationen z.B. durch Tränke
	 */
	public final int[] eigenschaftsModifkator = new int[8];
	
	/**
	 * Talentwerte des Helden
	 */
	public final SortedSet<TalentWert> talentWerte = new TreeSet<TalentWert>();
	
	/**
	 * Der Name des Helden
	 */
	public String name;
		
	/**
	 * Erstellt einen neuen "leeren" Helden
	 */
	public Held() {
	
	}
	
	/**
	 * Erstellt einen neuen "leeren" Helden mit dem angegebenen Namen
	 * @param name
	 */
	public Held(String name) {
		this.name = name;
	}

	/**
	 * Erstellt einen neuen Helden mit den angegebenen Eigenschaftswerten
	 * @param name der Name des Helden
	 * @param eigenschatsWerte Das Feld der Eigenschaftswerte. Muss exakt 8 Elemente groß sein.
	 * @throws IllegalArgumentException wenn das Eingabefeld nicht exakt 8 Elemente hat
	 */
	public Held(String name, int[] eigenschatsWerte) throws IllegalArgumentException{
		this.name = name;
		
		//Länge der Eingabe prüfen
		if (eigenschatsWerte.length != 8) throw new IllegalArgumentException("Eigenschaftsfelder für Helden müssen exakt 8 Elemente haben");
		
		//Eingaben übertragen
		for (int i = 0; i < eigenschatsWerte.length; i++){
			this.eigenschaft[i] = eigenschatsWerte[i];
		}
	}
	
	/**
	 * Erstellt einen neuen Helden mit den angegebenen Eigenschaftswerten
	 * @param name Der Name des Helden
	 * @param mu Der Mut-Wert des Helden
	 * @param kl Der Klugheits-Wert des Helden
	 * @param in Der Intuitions-Wert des Helden
	 * @param ch Der Charisma-Wert des Helden
	 * @param ff Der Fingerfertigkeits-Wert des Helden
	 * @param ge Der Gewandheits-Wert des Helden
	 * @param ko Der Konstitutions-Wert des Helden
	 * @param kk Der Körperkraft-Wert des Helden
	 */
	public Held(String name, int mu, int kl, int in, int ch, int ff, int ge, int ko, int kk){
		this.name = name;
		this.eigenschaft[0] = mu;
		this.eigenschaft[1] = kl;
		this.eigenschaft[2] = in;
		this.eigenschaft[3] = ch;
		this.eigenschaft[4] = ff;
		this.eigenschaft[5] = ge;
		this.eigenschaft[6] = ko;
		this.eigenschaft[7] = kk;
	}
	
	/**
	 * Setzt die temporären Modifikationen zurück
	 */
	public void resetModifikatoren(){
		for (int i = 0; i < this.eigenschaftsModifkator.length; i++){
			this.eigenschaftsModifkator[i] = 0;
		}
	}
	
	/**
	 * Der aktuelle Eigenschaftswert inclusive Modifikatoren
	 * @param eigenschaft Der Index des korrespondierenden Eigenschaftswert
	 * @return Der aktuelle Eigenschaftswert inclusive Modifikatoren
	 */
	public int getEigenschaft(byte eigenschaft){
		return this.eigenschaft[eigenschaft] + this.eigenschaftsModifkator[eigenschaft];
	}
	
	/**
	 * 
	 * @param Talent
	 * @return
	 */
	public TalentWert getTalentWert(Talent talent){
		//TODO Könnte wahrscheinlich effizienter sein
		for (Iterator<TalentWert> it=this.talentWerte.iterator();it.hasNext();){
			TalentWert talentWert = it.next();
			if (talentWert.getTalent().equals(talent)) return talentWert;
		}
		return null;
	}
	
	public void addTalentWert(Talent talent, int tap) throws TalentException{
		if (this.getTalentWert(talent) != null) throw new TalentException(this.name+" hat bereits einen Wert für das Talent "+talent.getName()+"("+talent+")");
		this.talentWerte.add(talent.getTalentWert(tap, this));
	}

	@Override
	public int compareTo(Held o) {
		return this.getName().compareTo(o.getName());
	}

	@Override
	public String getName() {
		return this.name;
	}

}
