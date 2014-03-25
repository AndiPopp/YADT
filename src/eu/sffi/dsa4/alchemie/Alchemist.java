/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import java.util.SortedSet;
import java.util.TreeSet;

import eu.sffi.dsa4.held.talente.WuerfelTalentWert;
import eu.sffi.dsa4.util.AbstractNamedObject;

/**
 * @author Andi Popp
 *
 */
public class Alchemist extends AbstractNamedObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -950451273634352660L;

	/**
	 * Der Talentwert des Alchemisten. Beinhaltet die Referenz auf den Helden mittels getHeld()
	 */
	public final WuerfelTalentWert talentWert;

	public final SortedSet<Rezept> rezeptBuch;
	
	public Alchemist(WuerfelTalentWert talentWert) {
		super();
		this.talentWert = talentWert;
		rezeptBuch = new TreeSet<Rezept>();
	}

	@Override
	public String getName() {
		return this.talentWert.getHeld().getName()+" ("+this.talentWert+")";
	}
	
	public boolean equalsAsAlchemist(Alchemist alchemist){
		if (this.talentWert.equals(alchemist.talentWert)) return true;
		else return false;
	}
	
	
	
}
