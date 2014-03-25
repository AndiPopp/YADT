/**
 * 
 */
package eu.sffi.dsa4.alchemie;

/**
 * @author Andi Popp
 *
 */
public class Laborqualitaet {

	public static final byte SCHLECHT = 0;
	public static final byte NORMAL = 1;
	public static final byte HOCHWERTIG = 2;
	public static final byte AUSSERGEWOEHNLICH = 3;

	public final byte wert;

	public Laborqualitaet(byte wert) {
		super();
		this.wert = wert;
	}
	
	public static Laborqualitaet[] getStandardWerte(){
		Laborqualitaet[] werte = new Laborqualitaet[4];
		for (byte i = 0; i < werte.length; i++){
			werte[i] = new Laborqualitaet(i);
		}
		return werte;
	}
	
	public String toString(){
		switch (wert){
			case SCHLECHT: return "schlecht";
			case NORMAL: return "normal";
			case HOCHWERTIG: return "hochwertig";
			case AUSSERGEWOEHNLICH: return "außergewöhnlich";
			default: return ""+wert;
		}
	}
	

	
}
