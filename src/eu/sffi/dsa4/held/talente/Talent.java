/**
 * 
 */
package eu.sffi.dsa4.held.talente;

import eu.sffi.dsa4.held.Held;
import eu.sffi.dsa4.util.Named;

/**
 * @author Andi Popp
 *
 */
public abstract class Talent implements Named<Talent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2176605079246353482L;
	public static final WuerfelTalent ALCHEMIE = new WuerfelTalent("Alchemie", Held.MU, Held.KL, Held.FF);
	public static final WuerfelTalent KOCHEN = new WuerfelTalent("Kochen", Held.KL, Held.IN, Held.FF);
	
	public abstract String getName();

	public abstract TalentWert getTalentWert(int tap, Held held);
	
	public int compareTo(Talent talent){
		return this.getName().compareTo(talent.getName());
	}
}


