/**
 * 
 */
package eu.sffi.dsa4.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;



/**
 * @author Andi Popp
 * Wrapper for a hash table with saving and loading functions
 */
public class SimplePersistentNamedCollection<T extends Named<T>> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4199539232213531406L;
	private TreeMap<String, T> content;
		
	/**
	 * 
	 */
	public SimplePersistentNamedCollection() {
		content = new TreeMap<String, T>();
	}

	/**
	 * @param fileName
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public SimplePersistentNamedCollection(String fileName) throws ClassNotFoundException, IOException {
		this();
		load(fileName);
	}
	
	 public void save(String fileName) throws IOException {
		 FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		 objectOutputStream.writeObject(this.content);
		 objectOutputStream.close();
	 }
	 
	 /**
	  * 
	  * @param fileName
	  * @throws ClassCastException if the file has not serialized a Hashtable or the values of the
	  * 			Hashtable are not of type T
	  * @throws ClassNotFoundException if an unknown class has been serialized
	  * @throws IOException
	  */
	 public void load(String fileName) throws ClassNotFoundException, IOException, ClassCastException {
		 FileInputStream fileInputStream = new FileInputStream(fileName);
		 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		 TreeMap<String, T> content = (TreeMap<String, T>) objectInputStream.readObject();
		 objectInputStream.close();
		 
		 //TODO Check if elements are of correct type
		 
		 //Everything worked, write the read content to the objects content
		 this.content = content;
	 }
	 
	 public void putObject(T object) {
		 content.put(object.getName(), object);
	 }
	 
	 public T getObject(String name) {
		 return content.get(name);
	 }
	 
	 public Iterator<String> getAllNames() {
		 return content.keySet().iterator();
	 }
	 
	 public T remove(String name){
		 return content.remove(name);
	 }
	 
	 public int size(){
		 return content.size();
	 }
	 
	 public boolean isEmpty(){
		 return content.isEmpty();
	 }
	 
	 public Collection<T> values(){
		 return content.values();
	 }
}
