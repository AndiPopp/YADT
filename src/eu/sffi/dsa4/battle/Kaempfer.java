package eu.sffi.dsa4.battle;

import eu.sffi.dsa4.util.AbstractNamedObject;


/**
 * Diese Klasse repräsentiert einen einzelnen Kämpfer im Schlachten-Modul
 * @author Andi Popp
 *
 */
public class Kaempfer extends AbstractNamedObject{
	
	/**
	 * Der Name des Kämpfers
	 */
	String name;
	
	/**
	 * Die Wundschwelle des Kämpfers
	 */
	byte wundschwelle;
	
	/**
	 * Der Rüstungsschutz des Kämpfers
	 */
	byte ruestungsschutz;
	
	/**
	 * Die Magieresistenz des Kämpfers
	 */
	byte magieresistenz;
	
	/**
	 * Die Lebensernergie des Kämpfers
	 */
	byte lebensenergie;
	
	/**
	 * Die Ausdauer des Kämpfers
	 */
	byte ausdauer;
	
	/**
	 * Die Initiative des Kämpfers
	 */
	byte initiative;
	
	/**
	 * Anzahl der Wunden des Kämpfers
	 */
	byte wunden;
	
	/**
	 * Der Ausweichen-Wert des Kämpfers
	 */
	byte ausweichen;
	
	/**
	 * Die Waffen des Kämpfers
	 */
	KampfWaffe[] waffen;
	
	/**
	 * Vollständig parametrisierter Konstruktor
	 * @param name
	 * @param wundschwelle
	 * @param ruestungsschutz
	 * @param magieresistenz
	 * @param lebensenergie
	 * @param ausdauer
	 * @param initiative
	 * @param wunden
	 * @param ausweichen
	 * @param waffen
	 */
	public Kaempfer(String name, byte wundschwelle, byte ruestungsschutz,
			byte magieresistenz, byte lebensenergie, byte ausdauer,
			byte initiative, byte wunden, byte ausweichen, KampfWaffe[] waffen) {
		super();
		this.name = name;
		this.wundschwelle = wundschwelle;
		this.ruestungsschutz = ruestungsschutz;
		this.magieresistenz = magieresistenz;
		this.lebensenergie = lebensenergie;
		this.ausdauer = ausdauer;
		this.initiative = initiative;
		this.wunden = wunden;
		this.ausweichen = ausweichen;
		this.waffen = waffen;
	}




	@Override
	public String getName() {
		return this.name;
	}
	
	
}
