/**
 * 
 */
package eu.sffi.dsa4.items;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * @author Andi Popp
 *
 */
public class ItemPool implements Serializable, HatInventar {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 741488323798518237L;
	
	SortedSet<Item> inventar;
	
	public ItemPool(){
		this.inventar = new TreeSet<Item>();
	}
	
	@Override
	public SortedSet<Item> getInventar() {
		return this.inventar;
	}
	
	@Override
	public String toString(){
		return "--Itempool--";
	}

}
