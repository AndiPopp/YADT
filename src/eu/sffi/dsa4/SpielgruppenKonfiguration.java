/**
 * 
 */
package eu.sffi.dsa4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import eu.sffi.dsa4.alchemie.AlchemieKonfiguration;
import eu.sffi.dsa4.util.Saveable;



/**
 * @author Andi Popp
 *
 */
public class SpielgruppenKonfiguration implements Saveable{

	
	private static final long serialVersionUID = 5587829916787431886L;
	
	/**
	 * Die Elixiere und Rezepte
	 */
	public AlchemieKonfiguration alchemieKonfiguration;
	
	/**
	 * @param alchemieKonfiguration
	 */
	public SpielgruppenKonfiguration(AlchemieKonfiguration alchemieKonfiguration) {
		this.alchemieKonfiguration = alchemieKonfiguration;
	}
	
	 @Override
	 public void save(String fileName) throws IOException {
		 FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		 objectOutputStream.writeObject(this);
		 objectOutputStream.close();
	 }
	 
	 public static SpielgruppenKonfiguration load(String fileName) throws ClassNotFoundException, IOException, ClassCastException {
		 FileInputStream fileInputStream = new FileInputStream(fileName);
		 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		 SpielgruppenKonfiguration spielgruppenKonfiguration = (SpielgruppenKonfiguration) objectInputStream.readObject();
		 objectInputStream.close();
		 return spielgruppenKonfiguration;
	 }
}
