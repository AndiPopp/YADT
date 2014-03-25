package eu.sffi.dsa4.held.talente;

import java.io.Serializable;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import eu.sffi.dsa4.held.Held;



public abstract class TalentWert implements Comparable<TalentWert>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2739015247661724844L;

	/**
	 * Das Talent auf das sich der Wert bezieht
	 */
	public abstract Talent getTalent();
	
	/**
	 * Der eigentliche Talentwert
	 */
	public abstract int getTAP();
	
	/**
	 * Funktion zum festlegen des Talentwerts
	 */
	public abstract void setTAP(int tap);
	
	public SortedSet<String> spezialisierungen = new TreeSet<String>();
	
	/**
	 * Der Held der diesen Talentwert besitzt
	 */
	public abstract Held getHeld();
	
	/**
	 * Fügt eine Spezialisierung hinzu
	 * @param spezialisierung Der Name der Spezialisierung, dient zur Identifikation und muss exakt richtig geschrieben sein.
	 * @return true wenn die Spezialisierung hinzugefügt wurde, false sonst
	 */
	public boolean addSpezialisierung(String spezialisierung){
		if (this.spezialisierungen.contains(spezialisierung)) return false;
		else{
			//teste ob der notwendige TAW besteht
			if (
					this.getTAP() < 7 ||
					(this.getTAP() < 14 && this.spezialisierungen.size() > 0) ||
					(this.getTAP() < 21 && this.spezialisierungen.size() > 1) ||
					(this.getTAP() < 28 && this.spezialisierungen.size() > 2) ||
					this.spezialisierungen.size() > 3
				) return false;
			
			//Wenn die Vorraussetzungen stimmen füge Spezialisierung hinzu
			this.spezialisierungen.add(spezialisierung);
			return true;
		}
	}
	
	/**
	 * Entfernt eine vorhandene Spezialisierung
	 * @param spezialisierung Der Name der Spezialisierung, dient zur Identifikation und muss exakt richtig geschrieben sein.
	 * @return true wenn die Spezialisierung entfernt wurde, false falls sie nicht vorhanden war
	 */
	public boolean removeSpezialisierung(String spezialisierung){
		if (this.spezialisierungen.contains(spezialisierung)){
			this.spezialisierungen.remove(spezialisierung);
			return true;
		}
		else return false;
	}
	
	@Override
	public int compareTo(TalentWert o) {
		return this.getTalent().getName().compareToIgnoreCase(o.getTalent().getName());
	}
	
	@Override
	public String toString(){
		String outputString = getTalent().getName();
		if (this.spezialisierungen.size()>0){
			outputString = outputString + " (";
			for (Iterator<String> it = this.spezialisierungen.iterator(); it.hasNext();){
				outputString = outputString + it.next()+",";
			}
			outputString = outputString.substring(0, outputString.length()-1);
			outputString = outputString+")";
		}
		return outputString;
	}
}
