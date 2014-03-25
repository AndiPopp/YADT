/**
 * 
 */
package eu.sffi.dsa4.util;

/**
 * @author Andi Popp
 *
 */
public abstract class AbstractNamedObject implements Named{

	@Override
	public abstract String getName();
	
	@Override
	public int compareTo(Named o) {
		return this.getName().compareTo(o.getName());
	}
	
	public String toString(){
		return this.getName();
	}
}
