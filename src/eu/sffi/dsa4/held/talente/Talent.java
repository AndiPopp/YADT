/**
 * 
 */
package eu.sffi.dsa4.held.talente;

import eu.sffi.dsa4.held.Held;

/**
 * @author Andi Popp
 *
 */
public abstract class Talent {

	public static final WuerfelTalent ALCHEMIE = new WuerfelTalent("Alchemie", Held.MU, Held.KL, Held.FF);
	public static final WuerfelTalent KOCHEN = new WuerfelTalent("Kochen", Held.KL, Held.IN, Held.FF);
	
	public abstract String getName();

	public abstract TalentWert getTalentWert(int tap, Held held);
}


