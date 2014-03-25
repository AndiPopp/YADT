/**
 * 
 */
package eu.sffi.dsa4.items;

import eu.sffi.dsa4.util.AbstractNamedObject;

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

	private String name;
	
	private String notiz;
	
	//Konstruktoren
	
	public Item(double gewicht, String name) {
		super();
		this.gewicht = gewicht;
		this.name = name;
	}

	public String getItemTyp(){
		return "Gegenstand";
	}
	
	public void setNotiz(String notiz) {
		this.notiz = notiz;
	}
	
	public String getNotiz() {
		return this.notiz;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//Overrides
	
	@Override
	public String getName() {
		return name;
	}



	
	
	
}
