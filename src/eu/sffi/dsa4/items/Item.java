/**
 * 
 */
package eu.sffi.dsa4.items;

import java.util.SortedSet;
import java.util.TreeSet;

import eu.sffi.dsa4.util.AbstractNamedObject;
import eu.sffi.dsa4.util.Named;

/**
 * @author Andi Popp
 *
 */
public class Item extends AbstractNamedObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3359387404891416323L;

	public double gewicht;

	public final String name;
	
	//Konstruktoren
	
	public Item(double gewicht, String name) {
		super();
		this.gewicht = gewicht;
		this.name = name;
	}

	public String getItemTyp(){
		return "Gegenstand";
	}
	
	//Overrides
	
	@Override
	public String getName() {
		return name;
	}



	
	
	
}
