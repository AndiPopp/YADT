/**
 * 
 */
package eu.sffi.dsa4.util;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author Andi Popp
 * The Object can be saved into a file
 */
public interface Saveable extends Serializable{

	/**
	 * Saves the Object into a file
	 * @param fileName The file the object is to be saved in
	 * @throws IOException
	 */
	public void save(String fileName) throws IOException;
}
