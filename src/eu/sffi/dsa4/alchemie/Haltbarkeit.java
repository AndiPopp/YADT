/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import java.io.Serializable;

import eu.sffi.dsa4.kalender.AventurischesDatum;
import eu.sffi.dsa4.util.VerboseOut;
import eu.sffi.dsa4.wuerfel.Wuerfel;
import eu.sffi.dsa4.wuerfel.Wurf;

/**
 * @author Andi Popp
 *
 */
public class Haltbarkeit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2062517117461241147L;
	public static final byte STUNDEN = 0;
	public static final byte TAGE = 1;
	public static final byte WOCHEN = 2;
	public static final byte MONATE = 3;
	public static final byte JAHRE = 4;
		
	byte zeiteinheit;

	Wurf wurf;
	
	
	
	/**
	 * @param zeiteinheit
	 * @param wurf
	 */
	public Haltbarkeit(Wurf wurf,byte zeiteinheit) {
		this.zeiteinheit = zeiteinheit;
		this.wurf = wurf;
	}



	public AventurischesDatum getHaltbarkeitsDatum(AventurischesDatum Erstelldatum, Wuerfel wuerfel){
		switch (this.zeiteinheit){
		case STUNDEN: VerboseOut.CONSOLE.ammend("Stunden"); break;
		case TAGE: VerboseOut.CONSOLE.ammend("Tage"); break;
		case WOCHEN: VerboseOut.CONSOLE.ammend("Wochen"); break;
		case MONATE: VerboseOut.CONSOLE.ammend("Monate"); break;
		case JAHRE: VerboseOut.CONSOLE.ammend("Jahre"); break;
	}
		
		int haltbarkeit = this.wurf.werfen(wuerfel);
		VerboseOut.CONSOLE.ammendln(" => "+haltbarkeit);
		AventurischesDatum haltbarkeitsDatum = Erstelldatum.clone();
		switch (this.zeiteinheit){
			case STUNDEN: haltbarkeitsDatum.add(haltbarkeit); break;
			case TAGE: haltbarkeitsDatum.addTage(haltbarkeit); break;
			case WOCHEN: haltbarkeitsDatum.addWochen(haltbarkeit); break;
			case MONATE: haltbarkeitsDatum.addMonate(haltbarkeit); break;
			case JAHRE: haltbarkeitsDatum.addJahre(haltbarkeit); break;
		}
		return haltbarkeitsDatum;
	}
	
	
}
