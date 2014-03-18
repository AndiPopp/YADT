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
	int analyseModifikator;

	/**
	 * @param name
	 * @param gruppe
	 * @param analyseModifikator
	 */
	public ElixierArt(String name, byte gruppe, int analyseModifikator) {
		this.name = name;
		this.gruppe = gruppe;
		this.analyseModifikator = analyseModifikator;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ElixierArt)) return false;
		return this.name.equals(((ElixierArt)obj).name);
	}
	
	
}
