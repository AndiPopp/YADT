package eu.sffi.dsa4.held.talente;

import java.io.Serializable;

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
	
	/**
	 * Der Held der diesen Talentwert besitzt
	 */
	public abstract Held getHeld();
	
	@Override
	public int compareTo(TalentWert o) {
		return this.getTalent().getName().compareToIgnoreCase(o.getTalent().getName());
	}
	
	@Override
	public String toString(){
		return getTalent().getName();
	}
}
