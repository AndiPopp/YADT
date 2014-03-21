/**
 * 
 */
package eu.sffi.dsa4.util;

import java.io.Serializable;

/**
 * @author Andi Popp
 *
 */
public interface Named extends Comparable<Named>, Serializable{
	
	public abstract String getName();
	
}
