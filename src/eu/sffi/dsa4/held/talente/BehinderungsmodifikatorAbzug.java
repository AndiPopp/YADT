/**
 * 
 */
package eu.sffi.dsa4.held.talente;

/**
 * @author Andi Popp
 *
 */
public class BehinderungsmodifikatorAbzug extends Behinderungsmodifikator {

	public int bonus;
	
	
	
	/**
	 * @param summand
	 */
	public BehinderungsmodifikatorAbzug(int summand) {
		this.bonus = summand;
	}



	/* (non-Javadoc)
	 * @see eu.sffi.dsa4.held.talente.Behinderungsmodifikator#getEffektiveBehinderung(int)
	 */
	@Override
	public int getEffektiveBehinderung(int Behinderung) {
		if (Behinderung-this.bonus > 0) return Behinderung-this.bonus;
		else return 0;
	}

}
