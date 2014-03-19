/**
 * 
 */
package eu.sffi.dsa4.util;

/**
 * @author Andi Popp
 * An interface to translate an object into a string and construct it from a string
 */
public interface SimpleStringSerializable<T> {

	public String toSerzializableString();
	
	public boolean hasControlCharacters(String s);
}
