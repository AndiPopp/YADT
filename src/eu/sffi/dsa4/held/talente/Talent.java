/**
 * 
 */
package eu.sffi.dsa4.held.talente;

import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import eu.sffi.dsa4.held.Held;
import eu.sffi.dsa4.util.Named;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;

/**
 * @author Andi Popp
 *
 */
public abstract class Talent implements Named{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2176605079246353482L;
	
	/**
	 * Der Name des Talents
	 */
	protected String name;
	
	//TODO Alle Talente
	public static final SimplePersistentNamedCollection<Talent> getStandardListe(){
		SimplePersistentNamedCollection<Talent> liste = new SimplePersistentNamedCollection<Talent>();
		
		//Handwerk
		addHandwerkstalente(liste);
		
		//return
		return liste;
	}
	
	private static final SimplePersistentNamedCollection<Talent> addHandwerkstalente(SimplePersistentNamedCollection<Talent> liste){
		Talent talentToAdd;
		
		talentToAdd = new WuerfelTalent("Abrichten", Held.MU, Held.IN, Held.CH);
			liste.putObject(talentToAdd);	
		talentToAdd = new WuerfelTalent("Ackerbau", Held.IN, Held.FF, Held.KO);
			liste.putObject(talentToAdd);	
		talentToAdd = new WuerfelTalent("Alchemie", Held.MU, Held.KL, Held.FF);
			liste.putObject(talentToAdd);	
		talentToAdd = new WuerfelTalent("Kochen", Held.KL, Held.IN, Held.FF);
			liste.putObject(talentToAdd);	
	
		return liste;
	}
	
	public String getName(){
		return this.name;
	}

	public abstract TalentWert getTalentWert(int tap, Held held);
	
	public int compareTo(Named talent){
		return this.getName().compareTo(talent.getName());
	}
	
	//Overrides
	public String toString(){
		return this.getName();
	}
}


