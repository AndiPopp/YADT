/**
 * 
 */
package eu.sffi.dsa4.held.talente;

import eu.sffi.dsa4.held.Held;
import eu.sffi.dsa4.util.VerboseOut;
import eu.sffi.dsa4.wuerfel.Wuerfel;

/**
 * @author Andi Popp
 *
 */
public class WuerfelTalentWert extends TalentWert {

	private final WuerfelTalent talent;
	@Override
	public Talent getTalent() {
		return this.talent;
	}

	private final int tap;
	@Override
	public int getTAP() {
		return this.tap;
	}

	private final Held held;
	@Override
	public Held getHeld() {
		return this.held;
	}
	
	/**
	 * @param talent
	 * @param wert
	 * @param held
	 */
	public WuerfelTalentWert(WuerfelTalent talent, int wert, Held held) {
		this.talent = talent;
		this.tap = wert;
		this.held = held;
	}
		
	/**
	 * Führt einen unmodifizierten Talentwurf mit dem statischen W20 aus
	 * @return die TAP* der Probe
	 */
	public int werfen(){
		return werfen(0);
	}
	
	/**
	 * Führt einen modifizierten Talentwurf mit dem statischen W20 aus
	 * @param modifikator Der Modifikator der Probe in DSA4-Sprache (d.h. negative Werte sind Erleichterungen)
	 * @return die TAP* der Probe
	 */
	public int werfen(int modifikator){
		return werfen(new Wuerfel(), modifikator);
	}
	
	/**
	 * Führt einen unmodifizierten Talentwurf mit dem angegebenen W20 aus
	 * @param wuerfel der Würfel mit dem geworfen wird
	 * @return die TAP* der Probe
	 */
	public int werfen(Wuerfel wuerfel){
		return werfen(wuerfel, 0);
	}
	
	/**
	 * Führt einen modifizierten Talentwurf mit dem angegebenen W20 aus
	 * @param wuerfel der Würfel mit dem geworfen wird
	 * @param modifikator Der Modifikator der Probe in DSA4-Sprache (d.h. negative Werte sind Erleichterungen)
	 * @return die TAP* der Probe
	 */
	public int werfen(Wuerfel wuerfel, int modifikator){
		//TODO Patzer und Glückswürfe
		VerboseOut.CONSOLE.print(this.held.name+" wirft eine Probe auf "+this.talent.getName()+" ("
				+Held.EIGENSCHAFT(this.talent.eigenschaft[0])+"/"
				+Held.EIGENSCHAFT(this.talent.eigenschaft[1])+"/"
				+Held.EIGENSCHAFT(this.talent.eigenschaft[2])+") "
				+"mit Eigenschaftswerten "
				+this.held.getEigenschaft(this.talent.eigenschaft[0])+"/"
				+this.held.getEigenschaft(this.talent.eigenschaft[1])+"/"
				+this.held.getEigenschaft(this.talent.eigenschaft[1])+" "
				+ "und Talentwert "+this.tap+": ");
		
		int tapStern = this.tap;
		
		//Wende Modifikator an
		tapStern -= modifikator;
		
		//Wirf alle Probem
		for (int i = 0; i < this.talent.eigenschaft.length; i++){
			//Wirf den Wuerfel
			int wurf = wuerfel.wirfW20();
			VerboseOut.CONSOLE.ammend(wurf+" ");
			//Hole den Eigenschaftswert 
			int eigenschaftswert = this.held.getEigenschaft(this.talent.eigenschaft[i]);
			//Verrechne eventuelle Überschüsse
			if (wurf > eigenschaftswert) tapStern -= wurf - eigenschaftswert;
		}
		
		//Maximal tap
		if (tapStern > this.tap) {
			VerboseOut.CONSOLE.ammendln("=> TAP*="+this.tap);
			return this.tap;
		}
		VerboseOut.CONSOLE.ammendln("=> TAP*="+tapStern);
		return tapStern;

	}

	
	
	//TODO Werfen mit Spezialisierung


	

	

	
}
