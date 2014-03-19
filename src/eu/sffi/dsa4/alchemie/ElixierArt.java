package eu.sffi.dsa4.alchemie;

import eu.sffi.dsa4.util.Named;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;

public class ElixierArt implements Named<ElixierArt>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5729951796270699950L;
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
	int analyseSchwierigkeit;

	/**
	 * @param name
	 * @param gruppe
	 * @param analyseSchwierigkeit
	 */
	public ElixierArt(String name, byte gruppe, int analyseSchwierigkeit) {
		this.name = name;
		this.gruppe = gruppe;
		this.analyseSchwierigkeit = analyseSchwierigkeit;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ElixierArt)) return false;
		return this.name.equals(((ElixierArt)obj).name);
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public static SimplePersistentNamedCollection<ElixierArt> STANDARDLISTE = getStandardListe();
	
	public static SimplePersistentNamedCollection<ElixierArt> getStandardListe(){
		SimplePersistentNamedCollection<ElixierArt> liste = new SimplePersistentNamedCollection<ElixierArt>();
		//TODO Alle ElixierArten eintragen
		
		//Heilmittel
		liste.putObject(new ElixierArt("Andtidot", ElixierArt.GRUPPE_SPAGYRIK, 4));
		liste.putObject(new ElixierArt("Furchtlos-Tropfen", ElixierArt.GRUPPE_SPAGYRIK, 1));
		liste.putObject(new ElixierArt("Heiltrank", ElixierArt.GRUPPE_SPAGYRIK, 1));
		liste.putObject(new ElixierArt("Pastillen gegen Erschöpfung", ElixierArt.GRUPPE_SPAGYRIK, 4));
		liste.putObject(new ElixierArt("Prophylaktika", ElixierArt.GRUPPE_SPAGYRIK, 7));
		liste.putObject(new ElixierArt("Pulver des klaren Geistes", ElixierArt.GRUPPE_SPAGYRIK, 2));
		liste.putObject(new ElixierArt("Restorarium", ElixierArt.GRUPPE_SPAGYRIK, 3));
		liste.putObject(new ElixierArt("Schlaftrunk", ElixierArt.GRUPPE_SPAGYRIK, 1));
		return liste;
	}
	
	@Override
	public int compareTo(ElixierArt o) {
		return this.getName().compareTo(o.getName());
	}
}
