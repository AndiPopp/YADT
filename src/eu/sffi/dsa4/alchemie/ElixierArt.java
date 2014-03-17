package eu.sffi.dsa4.alchemie;

public class ElixierArt {

	public static final byte GRUPPE_KEINE = 0;
	public static final byte GRUPPE_SPAGYRIK = 1;
	public static final byte GRUPPE_VENENIK = 2;
	public static final byte GRUPPE_NARKOTIKA = 3;
	public static final byte GRUPPE_MAGIKA = 4;
	public static final byte GRUPPE_INANIMATICA = 5;
	public static final byte GRUPPE_MUTANDICA = 6;
	public static final byte GRUPPE_VIRTURICA = 7;
	
	
	/**
	 * Der Name der Art des Elixiers
	 */
	String name;
	
	/**
	 * Die Gruppe der das Elixier zugeordnet wird
	 */
	byte gruppe;
	
	/**
	 * Erschwernis bei der Analyse des Tranks
	 */
	byte analyseModifikator;
	
	/**
	 * Liste der typischen Möglichkeiten bei Fehlschlägen
	 */
	int[] misslingensVorschlaege;
}
