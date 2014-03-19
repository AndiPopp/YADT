/**
 * 
 */
package eu.sffi.dsa4.util;

import java.io.Serializable;

/**
 * @author Andi Popp
 *
 */
public interface Named<T> extends Comparable<T>, Serializable{
	
	public abstract String getName();
	

}
