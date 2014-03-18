package eu.sffi.dsa4.held.talente;

import eu.sffi.dsa4.held.Held;



public abstract class TalentWert implements Comparable<TalentWert>{

	/**
	 * Das Talent auf das sich der Wert bezieht
	 */
	public abstract Talent getTalent();
	
	/**
	 * Der eigentliche Talentwert
	 */
	public abstract int getTAP();
	
	/**
	 * Der Held der diesen Talentwert besitzt
	 */
	public abstract Held getHeld();
	
	@Override
	public int compareTo(TalentWert o) {
		return this.getTalent().getName().compareToIgnoreCase(o.getTalent().getName());
	}
}
