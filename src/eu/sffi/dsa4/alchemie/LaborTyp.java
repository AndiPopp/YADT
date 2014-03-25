/**
 * 
 */
package eu.sffi.dsa4.alchemie;

/**
 * @author Andi Popp
 *
 */
public class LaborTyp {

	public static final byte ARCHAISCH = 0;
	public static final byte HEXENKUECHE = 1;
	public static final byte ALCHEMIELABOR = 2;

	public final byte wert;

	public LaborTyp(byte wert) {
		super();
		this.wert = wert;
	}
	
	public static LaborTyp[] getStandardWerte(){
		LaborTyp[] werte = new LaborTyp[3];
		for (byte i = 0; i < werte.length; i++){
			werte[i] = new LaborTyp(i);
		}
		return werte;
	}
	
	public String toString(){
		switch (wert){
			case ARCHAISCH: return "Archaisches Labor";
			case HEXENKUECHE: return "Hexenküche";
			case ALCHEMIELABOR: return "Alchemielabor";
			default: return ""+wert;
		}
	}
	
}
