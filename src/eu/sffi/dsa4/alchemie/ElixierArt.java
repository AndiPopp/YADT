package eu.sffi.dsa4.alchemie;

import eu.sffi.dsa4.util.AbstractNameConstructableObject;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;

public class ElixierArt extends AbstractNameConstructableObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5729951796270699950L;
	
	/**
	 * Der Name der Art des Elixiers
	 */
	private String name;
	
	/**
	 * Die Gruppe der das Elixier zugeordnet wird
	 */
	public byte gruppe;
	
	/**
	 * Erschwernis bei der Analyse des Tranks
	 */
	public int analyseSchwierigkeit;

	/**
	 * @param name
	 * @param gruppe
	 * @param analyseSchwierigkeit
	 */
	public ElixierArt(String name, byte gruppe, int analyseSchwierigkeit) {
		super(name);
		this.gruppe = gruppe;
		this.analyseSchwierigkeit = analyseSchwierigkeit;
	}
	
	
	public boolean equals(Object obj) {
		if (!(obj instanceof ElixierArt)) return false;
		return this.name.equals(((ElixierArt)obj).name);
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public static SimplePersistentNamedCollection<ElixierArt> getStandardListe(){
		SimplePersistentNamedCollection<ElixierArt> liste = new SimplePersistentNamedCollection<ElixierArt>();
		//TODO Alle ElixierArten eintragen
		
		//Gegenstandselixiere
		liste.putObject(new ElixierArt("Ewiges Öl", ElixierGruppe.INANIMATICA, 2));
		
		//Heilmittel
		liste.putObject(new ElixierArt("Antidot", ElixierGruppe.SPAGYRIK, 4));
		liste.putObject(new ElixierArt("Furchtlos-Tropfen", ElixierGruppe.SPAGYRIK, 1));
		liste.putObject(new ElixierArt("Heiltrank", ElixierGruppe.SPAGYRIK, 1));
		liste.putObject(new ElixierArt("Pastillen gegen Erschöpfung", ElixierGruppe.SPAGYRIK, 4));
		liste.putObject(new ElixierArt("Prophylaktika", ElixierGruppe.SPAGYRIK, 7));
		liste.putObject(new ElixierArt("Pulver des klaren Geistes", ElixierGruppe.SPAGYRIK, 2));
		liste.putObject(new ElixierArt("Restorarium", ElixierGruppe.SPAGYRIK, 3));
		liste.putObject(new ElixierArt("Schlaftrunk", ElixierGruppe.SPAGYRIK, 1));
		return liste;
	}
	
	
	
	@Override
	public String toString(){
		return this.name;
	}


	@Override
	public String initName(String name) {
		if (this.name == null)	this.name = name;
		return this.name;
	}





}
