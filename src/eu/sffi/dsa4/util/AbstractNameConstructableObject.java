/**
 * 
 */
package eu.sffi.dsa4.util;

import java.lang.reflect.Constructor;

/**
 * @author Andi Popp
 *
 */
public abstract class AbstractNameConstructableObject extends AbstractNamedObject {

	/**
	 * Setzt den Namen des Objekts of name, falls das Objekt noch keinen Namen hat
	 * @param name Der Name, den das Objekt erhalten soll.
	 * @return Der Name, den das Objekt nun hat.
	 */
	public abstract String initName(String name);
	
	/**
	 * Konstruiert ein "leeres" Objekt dass nur einen Namen hat
	 * @param name Der Name des Objekts
	 */
	public AbstractNameConstructableObject (String name){
		initName(name);
	}
	
	public static <T extends AbstractNameConstructableObject> T constructObject(Class<T> type, String name){
		try{
			Constructor<T> cons = type.getConstructor(String.class);
			return cons.newInstance(name);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Ich muss hier aufgeben mit den Generics. Diese Funktion ruft nur 
	 * den üblichen Konstruktor im dynamischen Kontext auf.
	 * @param name
	 * @return
	 */
	public abstract Object getBabyObject(String name);

}
