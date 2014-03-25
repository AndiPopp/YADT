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
	 * Die Liste der Rezepte
	 */
	public SimplePersistentNamedCollection<Alchemist> alchemisten;
	
	/**
	 * Vollständiger Parameter-Konstruktor
	 * @param elixierArten
	 * @param rezepte
	 * @param alchemisten
	 */
	public AlchemieKonfiguration(
			SimplePersistentNamedCollection<ElixierArt> elixierArten,
			SimplePersistentNamedCollection<Rezept> rezepte,
			SimplePersistentNamedCollection<Alchemist> alchemisten) {
		this.elixierArten = elixierArten;
		this.rezepte = rezepte;
		this.alchemisten =  alchemisten;
	}
	
	public static AlchemieKonfiguration getStandardKonfiguration(){
		SimplePersistentNamedCollection<ElixierArt> listeEA = ElixierArt.getStandardListe();
		return new AlchemieKonfiguration(listeEA, Rezept.getStandardListe(listeEA), new SimplePersistentNamedCollection<Alchemist>());
	}
	
	/**
	 * Überschrebt leere Teilkonfigurationen mit Standardwerten
	 */
	public void integrityCheck() {
		if (this.elixierArten == null) this.elixierArten = ElixierArt.getStandardListe();
		if (this.rezepte == null) this.rezepte = Rezept.getStandardListe(this.elixierArten);
		if (this.alchemisten == null) this.alchemisten = new SimplePersistentNamedCollection<Alchemist>() ;
	}


	@Override
	public String getName() {
		return "Alchemiekonfiguration";
	}

	
	
	
}
