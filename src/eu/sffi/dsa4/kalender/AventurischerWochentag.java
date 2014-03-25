package eu.sffi.dsa4.kalender;

import java.io.Serializable;

public class AventurischerWochentag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4846264401392257079L;
	
	public static final byte WINDSTAG = 1;
	public static final byte ERDTAG = 2;
	public static final byte MARKTTAG = 3;
	public static final byte PRAIOSTAG = 4;
	public static final byte ROHALSTAG = 5;
	public static final byte FEUERTAG = 6;
	public static final byte WASSERTAG = 7;
	
	/**
	 * Der numerische Wert des Wochentags
	 */
	public final byte wert;
	
	public AventurischerWochentag(byte wert) {
		super();
		this.wert = wert;
	}
	
	@Override
	public String toString(){
		switch (wert){
			case WINDSTAG: return "Windstag";
			case ERDTAG: return "Erdtag";
			case MARKTTAG: return "Markttag";
			case PRAIOSTAG: return "Praiostag";
			case ROHALSTAG: return "Rohalstag";
			case FEUERTAG: return "Feuertag";
			case WASSERTAG: return "Wassertag";
			default: return ""+wert;
		}
	}
	
	
}
