/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;



/**
 * @author Andi Popp
 *
 */
public class RezeptListe extends TreeSet<Rezept> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310686181638638855L;

	/**
	 * Leerer Konstruktor
	 */
	public RezeptListe(){
		super();
	}
	
	public static RezeptListe getStandardListe(){
		RezeptListe rezeptListe = new RezeptListe();
		//Heiltrank
		rezeptListe.add(new Rezept(
				"Heiltrank (Standardrezept)", 									//name 
				new ElixierArt("Heiltrank", ElixierArt.GRUPPE_SPAGYRIK, 1), 	//elixierArt
				50, 															//beschaffungsPreis
				14, 															//beschaffungsWahrscheinlichkeit
				Rezept.LABOR_ARCHAISCH,											//labor
				2,																//brauModifikator, 
				7));															//verbreitung
		//Heiltrank
		rezeptListe.add(new Rezept(
				"Heiltrank (Standardrezept)", 									//name 
				new ElixierArt("Heiltrank", ElixierArt.GRUPPE_SPAGYRIK, 1), 	//elixierArt
				50, 															//beschaffungsPreis
				14, 															//beschaffungsWahrscheinlichkeit
				Rezept.LABOR_ARCHAISCH,											//labor
				2,																//brauModifikator, 
				7));	
		
		return rezeptListe;
	}
	
	/**
	 * 
	 * @param name Der Name des Rezepts
	 * @return Das Rezept mit dem Namen, null sollte der Name nicht vorhanden sein
	 */
	public Rezept getRezept(String name){
		//Kann effizienter werden
		for(Iterator<Rezept> it = this.iterator();it.hasNext();){
			Rezept rezept = it.next();
			if (rezept.name.equals(name)) return rezept;
 		}
		return null;
	}
	
	/**
	 * Ist der Rezept der in der Sammlung vorhanden?
	 * @param name Der Name des Rezepts
	 * @return true falls das Rezept vorhanden ist, false sonst
	 */
	public boolean containsRezept(String name){
		return (getRezept(name) != null);
	}
	
}
