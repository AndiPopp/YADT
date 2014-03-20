/**
 * 
 */
package eu.sffi.dsa4.alchemie;

import eu.sffi.dsa4.util.AbstractNameObject;
import eu.sffi.dsa4.util.Named;

/**
 * @author Andi Popp
 *
 */
public class ElixierGruppe extends AbstractNameObject implements Named<ElixierGruppe>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4566679123109574555L;

	/**
	 * Der Integerwert der Elixiergruppe
	 */
	private final byte wert;
	
	public static final byte VIRTURICA = 7;
	public static final byte MUTANDICA = 6;
	public static final byte INANIMATICA = 5;
	public static final byte MAGIKA = 4;
	public static final byte NARKOTIKA = 3;
	public static final byte VENENIK = 2;
	public static final byte SPAGYRIK = 1;
	public static final byte KEINE = 0;
		
	/**
	 * @param wert Der in den Konstanten der Gruppe ElixierArt definierte Wert
	 */
	private ElixierGruppe(byte wert) {
		this.wert = wert;
	}
	
	public static ElixierGruppe[] getAlleGruppen(){
		ElixierGruppe[] elixierGruppen = new ElixierGruppe[8];
		for (byte i = 0; i < 8; i++){
			elixierGruppen[i] = new ElixierGruppe(i);
		}
		return elixierGruppen;
	}

	@Override
	public int compareTo(ElixierGruppe arg0) {
		return this.getName().compareTo(arg0.getName());
	}

	@Override
	public String getName() {
		switch (wert){
			case ElixierGruppe.KEINE: return "keine Gruppe";
			case ElixierGruppe.SPAGYRIK: return "Spagyrik (Heilmittel)";
			case ElixierGruppe.VENENIK: return "Venenik (Gifte)";
			case ElixierGruppe.NARKOTIKA: return "Narkotika (Rauschmittel)";
			case ElixierGruppe.MAGIKA: return "Magika (Zaubermittel)";
			case ElixierGruppe.INANIMATICA: return "Inanimatica (Gegenstands-Elixiere)";
			case ElixierGruppe.MUTANDICA: return "Mutandika (Wandlungselixiere)";
			case ElixierGruppe.VIRTURICA: return "Virturica (Elixiere der Tugenden)";
			default: return null;
		}
	}

	
	/**
	 * 
	 * @return Der in den Konstanten der Gruppe ElixierArt definierte Wert
	 */
	public byte getWert(){
		return this.wert;
	}

}
