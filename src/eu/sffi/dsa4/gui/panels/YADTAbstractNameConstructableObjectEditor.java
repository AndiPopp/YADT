/**
 * 
 */
package eu.sffi.dsa4.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import eu.sffi.dsa4.gui.YADTMainContentPane;
import eu.sffi.dsa4.util.AbstractNameConstructableObject;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;


/**
 * @author Andi Popp
 *
 */
public abstract class YADTAbstractNameConstructableObjectEditor<T extends AbstractNameConstructableObject> extends YADTAbstractNamedObjectEditor<T>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8693734682872402364L;
	
	public YADTAbstractNameConstructableObjectEditor(
			SimplePersistentNamedCollection<T> objectSet,
			YADTMainContentPane parent) {
		super(objectSet, parent);
	}
	
	
	public boolean specificActionPerformed(ActionEvent e) {
	    if (e.getActionCommand().equals("NamedObjectEditor:New")){
			String neuerName = JOptionPane.showInputDialog("Bitte einen Namen für das neue Objekt eingeben:");			
			if (neuerName != null) {
				if (neuerName.equals("")) JOptionPane.showMessageDialog(this, "Name darf nicht leer sein", "Fehler", JOptionPane.ERROR_MESSAGE);
				else newObject(neuerName);		
			}
			specficLevel2ActionPerformed(e); //Gib die new action zusätzlich an die konkrete Klasse für weitere Aktionen
			return true;
	    }
		return this.specficLevel2ActionPerformed(e);
	}
	
	public abstract boolean specficLevel2ActionPerformed(ActionEvent e);
	
	
	/**
	 * Legt fügt ein neues Objekt mit dem Namen name in den Datenspeicher ein.
	 * Danach wird die ComboBox der Items aktualisiert und die Selektion auf
	 * das neue Objekt gesetzt. Ist der Name bereits in der Liste passiert nichts.
	 * Der Nutzer wird darüber mit einer Warnmeldung informiert.
	 * 
	 * @param name Der Name des neuen Objekts
	 * @return true wenn die Operation erfolgreich war, false sonst (z.B. 
	 * 		weil der Name bereits vorhanden war)
	 */
	public boolean newObject(String name){
		T newObject = getNewObject(name);
		if (!this.objectSet.containsName(name)) {
			this.objectSet.putObject(newObject);
			
			//Update das Top-Panel mit der ComboBox
			this.remove(topPanel);
			this.topPanel = createTopPanel();
			this.add(topPanel, BorderLayout.NORTH);
			
			this.selectedObject = newObject;
			this.objektComboBox.setSelectedItem(selectedObject);
			
			
			this.setCurrentChanges(true);
			revalidate();
			return true;
		}
		else{
			JOptionPane.showMessageDialog(this, 
					"Ein Objekt mit diesem Namen existiert bereits.", 
					"Fehler beim Anlegen", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public abstract T getNewObject(String name);
	
	
	


}
