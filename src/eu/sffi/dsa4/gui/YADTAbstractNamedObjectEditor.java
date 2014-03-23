/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import eu.sffi.dsa4.util.AbstractNameConstructableObject;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;


/**
 * @author Andi Popp
 *
 */
public abstract class YADTAbstractNamedObjectEditor<T extends AbstractNameConstructableObject> extends YADTAbstractToolPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8693734682872402364L;

//	/**
//	 * An instance of the object to create new Instances from
//	 */
//	private final T fatherObject;
	
	/**
	 * Die Datenbank von Namens-Objekten, die durch diesen Editor gemanaged wird
	 */
	protected SimplePersistentNamedCollection<T> objectSet;
	
	/**
	 * Das aktuell ausgewählte Objekt
	 */
	protected T selectedObject;

	/**
	 * Die ComboBox mit der einzelne Objekte ausgewählt werden können.
	 */
	protected JComboBox<T> objektComboBox;
	
	/**
	 * Der Titel des Editors. Standardmäßig der Wert von toString, d.h. der Navigationseintrag
	 */
	protected String title;
	
	public YADTAbstractNamedObjectEditor(SimplePersistentNamedCollection<T> objectSet,  YADTMainContentPane parent){
		title = this.toString();
		this.objectSet = objectSet;
		this.parent = parent;
		
		//Set Layout
		this.setLayout(new BorderLayout());
			
		//Top Panel
		this.topPanel = createTopPanel();
		this.add(topPanel, BorderLayout.NORTH);
		
		//Main Panel
		this.mainPanel = createMainPanel();
		this.add(this.mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Erzeugt den oberen Teil des Fensters mit Titel, Auswahl-Combo-Box, sowie Löschen- und Neu-Button
	 */
	protected JPanel createTopPanel(){
		JPanel topPanel = new JPanel(new GridLayout(0, 1));
		
		//Titlelabel
		JLabel titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		titleLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
		topPanel.add(titleLabel);
	
		//Editpanel
		JPanel editPanel = new JPanel(new FlowLayout());
			//Dropdown-Menü
			Vector<T> rawVector = new Vector<T>(this.objectSet.values());
			objektComboBox = new JComboBox<T>(rawVector);
			objektComboBox.setActionCommand("NamedObjectEditor:ComboBoxChanged");
			objektComboBox.addActionListener(this);
			updateSelectedObject(objektComboBox);
			editPanel.add(objektComboBox);
			//Delete Button
			JButton deleteButton = new JButton("Löschen");
			deleteButton.setActionCommand("NamedObjectEditor:Delete");
			deleteButton.addActionListener(this);
			editPanel.add(deleteButton);
			//New Button
			JButton newButton = new JButton("Neu");
			newButton.setActionCommand("NamedObjectEditor:New");
			newButton.addActionListener(this);
			editPanel.add(newButton);
		topPanel.add(editPanel);
			
		return topPanel;
	}
	
	/**
	 * Erzeugt den Rahmen für den Hauptteil des Fensters. Wenn ein Objekt ausgewählt ist, ruft
	 * die createMainPanel() die Funktion getMainContentPanel() der konkreten Klasse auf, um
	 * den Rahmen mit dem zu füllen.
	 */
	protected JPanel createMainPanel() {
		//Außen zentrieren
		JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		//Border
		JPanel mainBorderPanel = new JPanel(new BorderLayout());
		JPanel mainConentPanel = new JPanel(); //Leeres Panel als default
		if (this.selectedObject != null){
			TitledBorder mainBorder = BorderFactory.createTitledBorder(this.selectedObject.getName());
			mainBorder.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,15));
			mainBorderPanel.setBorder(mainBorder);
		
			//InnererAbstand
			mainConentPanel = getMainContentPanel(); //Holt das Main Content Panel von der konkreten Klasse
			mainConentPanel.setBorder(new EmptyBorder(15, 50, 15, 50));
		}
		
		
		
		
		//Zusammenpacken
		mainBorderPanel.add(mainConentPanel, BorderLayout.CENTER);
		mainPanel.add(mainBorderPanel);
		return mainPanel;
	}
	
	/**
	 * Jede konkrete Klasse muss diese Funktion implementieren um die Eingabemaske
	 * für ein in der Combo-Box gewähltes Objekt zu erstellen.
	 * @return
	 */
	public abstract JPanel getMainContentPanel();
	
	/**
	 * Reagiert auf das auswählen von Objekten in der Combobox und ändert das Feld selectedObject
	 * @param comboBox
	 * @return
	 */
	protected T updateSelectedObject(JComboBox<T> comboBox){
		this.selectedObject = (T) comboBox.getSelectedItem();
		return this.selectedObject;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NamedObjectEditor:ComboBoxChanged")){
			updateSelectedObject((JComboBox<T>)e.getSource());
			if (this.mainPanel != null) this.remove(this.mainPanel);
			this.mainPanel = createMainPanel();
			this.add(this.mainPanel, BorderLayout.CENTER);
			revalidate();
		}
		else if (e.getActionCommand().equals("NamedObjectEditor:New")){
			String neuerName = JOptionPane.showInputDialog("Bitte einen Namen für das neue Objekt eingeben:");
			if (neuerName.equals("")) JOptionPane.showMessageDialog(this, "Name darf nicht leer sein", "Fehler", JOptionPane.ERROR_MESSAGE);
			if (neuerName != null) newObject(neuerName);		
		}
		else if (e.getActionCommand().equals("NamedObjectEditor:Delete")){
			deleteObject(selectedObject);
		}
		else if(!this.specificActionPerformed(e)){
			System.out.println("Noch nicht implementiert: "+e.getActionCommand());
		}
	}
	
	/**
	 * Nimmt das spezifizierte Objekt aus der Konfiguration und updatet
	 * die Auswahl-Combo-Box.
	 * 
	 * @param objekt
	 * @return false wenn das Objekt nicht in der Konfigurations-Datenbank war
	 */
	public boolean deleteObject(T objekt){
		if (this.objectSet.containsName(objekt.getName())){
			this.objectSet.remove(objekt.getName());
			
//			Update das Top-Panel mit der ComboBox
			this.remove(topPanel);
			this.topPanel = createTopPanel();
			this.add(topPanel, BorderLayout.NORTH);
			if (objektComboBox.getItemCount() > 0) objektComboBox.setSelectedIndex(0);
			else{
				this.selectedObject = null;
				if (this.mainPanel != null) this.remove(this.mainPanel);
				this.mainPanel = createMainPanel();
				this.add(this.mainPanel, BorderLayout.CENTER);
			}
		
			updateSelectedObject(objektComboBox);
			
			this.setCurrentChanges(true);
			revalidate();
			return true;
		}
		return false;
	}
	
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
	
	/**
	 * Standardmäßig versucht die abstrakte Klasse alle ActionEvents die sie
	 * kennt selbst zu behandeln (Neu, Löschen). Events die sie nicht kennt
	 * übergibt sie an diese Funktion der konkreten Klasse.
	 * 
	 * @param e
	 * @return
	 */
	public abstract boolean specificActionPerformed(ActionEvent e);
	
	/**
	 * Überschreibt die Funktion toString() von Object um die Anzeige im 
	 * Navigationsbaum zu verbessern.
	 */
	@Override
	public abstract String toString();

}
