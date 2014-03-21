/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import java.io.Serializable;

import eu.sffi.dsa4.util.AbstractNamedObject;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;

/**
 * @author Andi Popp
 *
 */
public class AlchemieKonfiguration extends AbstractNamedObject implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2361336496251442441L;

	/**
	 * Die Liste der Elixier-Arten
	 */
	public SimplePersistentNamedCollection<ElixierArt> elixierArten;
	
	/**
	 * Die Liste der Rezepte
	 */
	public SimplePersistentNamedCollection<Rezept>	rezepte;

	/**
	 * Vollständiger Parameter-Konstruktor
	 * @param elixierArten
	 * @param rezepte
	 */
	public AlchemieKonfiguration(
			SimplePersistentNamedCollection<ElixierArt> elixierArten,
			SimplePersistentNamedCollection<Rezept> rezepte) {
		this.elixierArten = elixierArten;
		this.rezepte = rezepte;
	}
	
	public static AlchemieKonfiguration getStandardKonfiguration(){
		SimplePersistentNamedCollection<ElixierArt> listeEA = ElixierArt.getStandardListe();
		return new AlchemieKonfiguration(listeEA, Rezept.getStandardListe(listeEA));
	}


	@Override
	public String getName() {
		return "Alchemiekonfiguration";
	}
	
	
}
