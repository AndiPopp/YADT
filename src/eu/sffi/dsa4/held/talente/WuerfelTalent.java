/**
 * 
 */
package eu.sffi.dsa4.held.talente;

import eu.sffi.dsa4.held.Held;

/**
 * @author Andi Popp
 *
 */
public class WuerfelTalent extends Talent {

	/**
	 * Die Eigenschaften auf die beim Talent gewürfelt wird als Eigenschaftsindizes
	 */
	public final byte[] eigenschaft = new byte[3];
	
	/**
	 * Der Name des Talents
	 */
	private final String name;
		
	/**
	 * 
	 * @param name Der Name des Talents
	 * @param eigenschaften Die Eigenschaften, auf die das Talent würfelt
	 * @throws IllegalArgumentException Wenn das Feld der Eigenschaften nicht drei Elemente groß ist
	 */
	public WuerfelTalent(String name, byte[] eigenschaften) throws IllegalArgumentException{
		if (eigenschaften.length != this.eigenschaft.length) throw new IllegalArgumentException("Talente müssen immer auf genau drei Eigenschaften würfeln");
		this.name = name;
		for (int i = 0; i < this.eigenschaft.length; i++){
			this.eigenschaft[i] = eigenschaften[i];
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param eigenschaft1
	 * @param eigenschaft2
	 * @param eigenschaft3
	 */
	public WuerfelTalent(String name, byte eigenschaft1, byte eigenschaft2, byte eigenschaft3) {
		this.name = name;
		this.eigenschaft[0] = eigenschaft1;
		this.eigenschaft[1] = eigenschaft2;
		this.eigenschaft[2] = eigenschaft3;
	}

	@Override
	public TalentWert getTalentWert(int tap, Held held) {
		return new WuerfelTalentWert(this, tap, held);
	}

	@Override
	public String getName() {
		return this.name;
	}



}
