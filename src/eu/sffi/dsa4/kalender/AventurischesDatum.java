/**
 * 
 */
package eu.sffi.dsa4.kalender;

import java.io.Serializable;

/**
 * @author Andi Popp
 * Eine Klasse für Tage nach dem aventurischen Kalender. Auf Stunden genau gerechnet (genauer wäre in Aventurien wohl unüblich). 
 * Der Fixpunkt des Timestamps ist der 1. Praios 0 BF, 1. Stunde.
 * 
 * Die Klasse arbeitet bisher nur mit Daten nach BF ordentlich.
 */
public class AventurischesDatum implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6658344931923758969L;
	
	/**
	 * Der time stamp in Stunden seit dem 1. Praios 0 BF 0:00
	 */
	long timestamp;
	
	/**
	 * @param timestamp
	 */
	public AventurischesDatum(long timestamp) {
		if (timestamp == 0); 
		this.timestamp = timestamp;
	}
	
	public AventurischesDatum(int jahr, AventurischerMonat monat, int tag){
		this(jahr, monat.wert, tag, 1);
	}
	
	public AventurischesDatum(int jahr, AventurischerMonat monat, int tag, int stunde){
		this(jahr, monat.wert, tag, stunde);
	}
	
	/**
	 * Erstellt ein neues aventurisches Datum
	 * @param jahr Das Jahr bezüglich Bosparans Fall
	 * @param monat Der numerische Monat (1 = Praios, 30 = Rahja)
	 * @param tag Der Tag
	 * @param stunde Die numerische Stunde (1 = Praiosstunde (Geisterstunde)
	 */
	public AventurischesDatum(int jahr, int monat, int tag, int stunde){
		//Eingaben überprüfen
		if (monat < 1 || monat > 13) throw new IllegalArgumentException("Fehler beim Konstruieren eines aventurischen Datums. Monat muss zwischen 1 und 13 liegen.");
		if (tag < 1 || tag > 30) throw new IllegalArgumentException("Fehler beim Konstruieren eines aventurischen Datums. Tag muss zwischen 1 und 30 liegen.");
		if (monat == 13 && tag > 5) throw new IllegalArgumentException("Fehler beim Konstruieren eines aventurischen Datums. Es gibt nur 5 namenlose Tage.");
		if (stunde < 1 || stunde > 24) throw new IllegalArgumentException("Fehler beim Konstruieren eines aventurischen Datums. Stunde muss zwischen 1 und 24 liegen.");
		
		//timestamp initialisieren
		this.timestamp = 0;
		
		//timestamp berechenen
		if (jahr >= 0){
			this.timestamp += jahr * 365 * 24;
			this.timestamp += (monat-1) * 30 * 24;
			this.timestamp += (tag-1) * 24;
			this.timestamp += (stunde-1);
		}
		else{
			this.timestamp += (jahr+1) * 365 * 24;
			if (monat < 13) {
				this.timestamp -= ((12-monat) * 30 +5) * 24;
				this.timestamp -= (30-tag) * 24;
			}
			else{
				this.timestamp -= (5-tag) * 24;
				
			}
			this.timestamp -= 25-stunde;
			
		}
	}

	/**
	 * Gibt den time stamp zurück
	 * @return der time stamp
	 */
	public long getTimestamp(){
		return this.timestamp;
	}
	
	/**
	 * Das Jahr des Datums bzgl. Bosparans Fall
	 * @return Das Jahr des Datums bzgl. Bosparans Fall
	 */
	public int getNumerischesJahr(){
		if (this.timestamp<0) return (int) this.timestamp/(365*24)-1;
		return (int) this.timestamp/(365*24);
	}
	
	/**
	 * Der Monat des Datums als Zahl
	 * @return Monat des Datums als Zahl
	 */
	public byte getNumerischenMonat(){
		//vor BF
		if (this.timestamp<0) {
			int tage = (int) this.timestamp % (365*24)/24;
			//namenlose tage
			if (tage > -5) return 13;
			//vor namenlosen tagen
			return (byte) (12+((tage+5)/30));
		}
		//nach BF
		return (byte) ((this.timestamp % (365*24))/(30*24) + 1);
	}
	
	/**
	 * Der Monat als Wort
	 * @return Der Monat als Wort
	 */
	public AventurischerMonat getMonat(){
		return new AventurischerMonat(getNumerischenMonat());
	}
	
	/**
	 * Der numerische Tag
	 * @return Der numerische Tag
	 */
	public byte getNumerischenTag(){
		//vor BF
		//TODO
		
		//nach BF
		return (byte) (this.timestamp % (365*24) % (30*24) / 24 + 1);
	}
	
	/**
	 * Die numerische Stunde
	 * @return Die numerische Stunde
	 */
	public byte getNumerischeStunde(){
		//vor BF
		//TODO
		
		//nach BF
		return (byte) (this.timestamp % (365*24) % (30*24) % 24 + 1);
	}

	public AventurischerWochentag getWochentag(){
		long tage = timestamp/24;
		byte wochentag = (byte) ((tage+4)%7);
		return new AventurischerWochentag(wochentag);
	}
	
	@Override
	public String toString(){
		return getNumerischenTag()+". "+getMonat()+" "+getNumerischesJahr()+" BF, "+getNumerischeStunde()+". Stunde";
	}
	
	/**
	 * Gibt einen String aus, der ausschließlich das kalendarische 
	 * Datum ohne Tageszeit angibt
	 * @return einen String aus, der ausschließlich das kalendarische 
	 * Datum ohne Tageszeit angibt
	 */
	public String toShortString(){
		return getNumerischenTag()+". "+getMonat()+" "+getNumerischesJahr()+" BF";
	}

	public void add(long stunden){
		this.timestamp += stunden;
	}
	
	public void addTage(int tage){
		this.timestamp += tage*24;
	}
	
	public void addWochen(int wochen){
		this.timestamp += wochen*7*24;
	}
	
	/**
	 * Fügt dem Datum die Zahl an Monate (30 Tagen) zu. Die namenlosen Tage werden dabei nicht als Monat gewertet.
	 * @param monate
	 */
	public void addMonate(int monate){
		this.timestamp += monate*30*24;
	}
	
	public void addJahre(int jahre){
		this.timestamp += jahre*365*24;
	}
	
	@Override
	public AventurischesDatum clone(){
		return new AventurischesDatum(this.timestamp);
	}
	
}
