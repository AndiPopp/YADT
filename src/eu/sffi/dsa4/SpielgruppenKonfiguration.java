/**
 * 
 */
package eu.sffi.dsa4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import eu.sffi.dsa4.alchemie.AlchemieKonfiguration;
import eu.sffi.dsa4.held.Held;
import eu.sffi.dsa4.held.talente.Talent;
import eu.sffi.dsa4.items.ItemPool;
import eu.sffi.dsa4.kalender.AventurischesDatum;
import eu.sffi.dsa4.util.Saveable;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;



/**
 * @author Andi Popp
 *
 */
public class SpielgruppenKonfiguration implements Saveable{

	
	private static final long serialVersionUID = 5587829916787431886L;
	
	/**
	 * Die Elixier-Typen und Rezepte
	 */
	public AlchemieKonfiguration alchemieKonfiguration;
	
	public SimplePersistentNamedCollection<Held> heldenListe;
	
	public SimplePersistentNamedCollection<Talent> talentListe;
	
	public ItemPool itemPool;
	
	public AventurischesDatum aktuellesDatum;
	
	public SpielgruppenKonfiguration(
			SimplePersistentNamedCollection<Held> heldenListe,
			SimplePersistentNamedCollection<Talent> talentListe,
			AlchemieKonfiguration alchemieKonfiguration) {
		this.heldenListe = heldenListe;
		this.talentListe = talentListe;
		this.alchemieKonfiguration = alchemieKonfiguration;
		integrityCheck();
	}
	
	
	/**
	 * "Leere" Standardliste
	 * @return eine "leere" Standardliste
	 */
	public static SpielgruppenKonfiguration getStandadard(){
		return new SpielgruppenKonfiguration(
				Held.emptyHeldenListe(), //Heldenliste
				Talent.getStandardListe(), //Talentliste
				AlchemieKonfiguration.getStandardKonfiguration() //Alchemie-Konfiguration
				); 
	}
	
	/**
	 * Überschreibt nicht vorhandene Konfigurationen mit den Standardwerten
	 */
	public void integrityCheck(){
		if (this.heldenListe == null) this.heldenListe = Held.emptyHeldenListe();
		if (this.talentListe == null) this.talentListe = Talent.getStandardListe();
		if (this.alchemieKonfiguration == null) this.alchemieKonfiguration = AlchemieKonfiguration.getStandardKonfiguration();
		else this.alchemieKonfiguration.integrityCheck();
		if (this.itemPool == null) this.itemPool = new ItemPool();
		if (this.aktuellesDatum == null) this.aktuellesDatum = new AventurischesDatum(1018, 1, 1, 1);
	}

	
	 @Override
	 public void save(String fileName) throws IOException {
		 FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		 objectOutputStream.writeObject(this);
		 objectOutputStream.close();
	 }
	 
	 public void save(File file) throws IOException {
		 FileOutputStream fileOutputStream = new FileOutputStream(file);
		 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		 objectOutputStream.writeObject(this);
		 objectOutputStream.close();
	 }
	 
	 public static SpielgruppenKonfiguration load(String fileName) throws ClassNotFoundException, IOException, ClassCastException {
		 FileInputStream fileInputStream = new FileInputStream(fileName);
		 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		 SpielgruppenKonfiguration spielgruppenKonfiguration = (SpielgruppenKonfiguration) objectInputStream.readObject();
		 objectInputStream.close();
		 return spielgruppenKonfiguration;
	 }
	 
	 public static SpielgruppenKonfiguration load(File file) throws ClassNotFoundException, IOException, ClassCastException {
		 FileInputStream fileInputStream = new FileInputStream(file);
		 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		 SpielgruppenKonfiguration spielgruppenKonfiguration = (SpielgruppenKonfiguration) objectInputStream.readObject();
		 objectInputStream.close();
		 return spielgruppenKonfiguration;
	 }
}
