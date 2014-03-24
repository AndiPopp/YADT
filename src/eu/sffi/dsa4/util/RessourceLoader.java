/**
 * 
 */
package eu.sffi.dsa4.util;

import java.io.InputStream;

/**
 * @author Andi Popp
 *
 */
public final class RessourceLoader {

	public static InputStream load(String fileName){
		InputStream input = RessourceLoader.class.getResourceAsStream(fileName);
		if (input == null) input = RessourceLoader.class.getResourceAsStream("/"+fileName);
		return input;
	}
	
}
