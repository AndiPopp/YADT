/**
 * 
 */
package eu.sffi.dsa4.held.talente;

/**
 * @author Andi Popp
 *
 */
public class BehinderungsmodifikatorMultiplikativ extends
		Behinderungsmodifikator {

	public int koeffizient;
	
	/**
	 * @param koeffizient
	 */
	public BehinderungsmodifikatorMultiplikativ(int koeffizient) {
		this.koeffizient = koeffizient;
	}

	/* (non-Javadoc)
	 * @see eu.sffi.dsa4.held.talente.Behinderungsmodifikator#getEffektiveBehinderung(int)
	 */
	@Override
	public int getEffektiveBehinderung(int Behinderung) {
		return Behinderung - this.koeffizient;
	}

}
