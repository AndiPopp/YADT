/**
 * 
 */
package eu.sffi.dsa4.util;

/**
 * @author Andi Popp
 *
 */
public abstract class AbstractNameObject {

	public abstract String getName();
	
	public String toString(){
		return this.getName();
	}
}
