/**
 * 
 */
package eu.sffi.dsa4.alchemie;

/**
 * @author Andi Popp
 *
 */
public class RezeptZutat {

	/**
	 * Die Zutat
	 */
	Zutat zutat;
	
	/**
	 * Menge der Zutat, wie sie im Rezept vorkommt
	 */
	double menge;
	
	/**
	 * Einheit der Zutat, wie sie im Rezept vorkommt
	 */
	String einheit;

	/**
	 * @param zutat Die Zutat
	 * @param menge Menge der Zutat, wie sie im Rezept vorkommt
	 * @param einheit Einheit der Zutat, wie sie im Rezept vorkommt
	 */
	public RezeptZutat(Zutat zutat, double menge, String einheit) {
		this.zutat = zutat;
		this.menge = menge;
		this.einheit = einheit;
	}
	
	
}
