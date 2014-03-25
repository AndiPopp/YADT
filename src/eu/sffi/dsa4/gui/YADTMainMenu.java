/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import eu.sffi.dsa4.SpielgruppenKonfiguration;
import eu.sffi.dsa4.gui.elements.Icons;
import eu.sffi.dsa4.gui.elements.Spacing;

/**
 * @author Andi Popp
 *
 */
public class YADTMainMenu extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1699715488733748402L;
	
	//Datei-Optionen
	private static final String ACTION_NEW = "YADTMainMenu:new";
	private static final String ACTION_OPEN = "YADTMainMenu:open";
	private static final String ACTION_SAVE = "YADTMainMenu:save";
	private static final String ACTION_SAVE_AS = "YADTMainMenu:save as";
	
	//Hilfe-Optionen
	private static final String ACTION_ABOUT = "YADTMainMenu:about";
	
	private JMenu dateiMenu;
	private JMenu hilfeMenu;
	private JMenu themeMenu;

	private final YADTMainFrame parent;

	private JFileChooser fileChooser;
	
	public YADTMainMenu(YADTMainFrame parent){
	//Globale Variablen initialisieren		
	this.parent = parent;
	this.fileChooser = new JFileChooser(){
		private static final long serialVersionUID = -9177539004199080833L;

		@Override
	    public void approveSelection(){
	        File f = getSelectedFile();
	        if(f.exists() && getDialogType() == SAVE_DIALOG){
	            int result = JOptionPane.showConfirmDialog(this,"Diese Datei besteht bereits, überschreiben?","Existing file",JOptionPane.OK_CANCEL_OPTION);
	            switch(result){
	                case JOptionPane.OK_OPTION:
	                    super.approveSelection();
	                    return;
	                case JOptionPane.CANCEL_OPTION:
	                    return;
	            }
	        }
	        super.approveSelection();
	    }        
	};
	
	JMenuItem menuItem;
	//Datei Menu
	this.dateiMenu = new JMenu("Datei");
	this.dateiMenu.setMnemonic('D');
	this.add(dateiMenu);
		//Neu
		menuItem = new JMenuItem("Neu", 'N');
		menuItem.setActionCommand(ACTION_NEW);
		menuItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		menuItem.addActionListener(this);
		menuItem.setIcon(Icons.NEW_ICON);
		this.dateiMenu.add(menuItem);
		//Öffnen
		menuItem = new JMenuItem("Öffnen", 'f');
		menuItem.setActionCommand(ACTION_OPEN);
		menuItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
		menuItem.addActionListener(this);
		menuItem.setIcon(Icons.OPEN_ICON);
		this.dateiMenu.add(menuItem);
		//Speichern
		menuItem = new JMenuItem("Speichern", 'S');
		menuItem.setActionCommand(ACTION_SAVE);
		menuItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
		menuItem.addActionListener(this);
		menuItem.setIcon(Icons.SAVE_ICON);
		this.dateiMenu.add(menuItem);
		//Speichern unter
		menuItem = new JMenuItem("Speichern unter", 'u');
		menuItem.setActionCommand(ACTION_SAVE_AS);
		menuItem.addActionListener(this);
		menuItem.setIcon(Icons.SAVE_AS_ICON);
		this.dateiMenu.add(menuItem);				
	//Theme Menu
	this.themeMenu = new JMenu("Themes");
	this.themeMenu.setMnemonic('T');
	this.add(themeMenu);
		for (LookAndFeelInfo lookAndFeel : UIManager.getInstalledLookAndFeels()){
			menuItem = new JMenuItem(lookAndFeel.getName());
			menuItem.addActionListener(this);
			menuItem.setActionCommand(lookAndFeel.getClassName());
			this.themeMenu.add(menuItem);
		}
	//Hilfe Menu
	this.hilfeMenu = new JMenu("Hilfe");
	this.hilfeMenu.setMnemonic('H');
	this.add(hilfeMenu);
	Spacing.addHorizontalSpacer(this, 1);	
		//About
		menuItem = new JMenuItem("Über", 'b');
		menuItem.addActionListener(this);
		menuItem.setActionCommand(ACTION_ABOUT);
		this.hilfeMenu.add(menuItem);	
			
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
//		System.out.println(actionEvent.getActionCommand());
		//Datei-Optionen
		if (actionEvent.getActionCommand().equals(ACTION_NEW)){
			createNewSpielgruppe();
		}
		else if (actionEvent.getActionCommand().equals(ACTION_SAVE)){
			saveSpielgruppe();
		}
		else if (actionEvent.getActionCommand().equals(ACTION_SAVE_AS)){
			saveAsSpielgruppe();
		}
		else if (actionEvent.getActionCommand().equals(ACTION_OPEN)){
			openSpielgruppe();
		}
		//Theme-Optionen
		else if (actionEvent.getActionCommand().contains(".plaf.")){
			try {
				UIManager.setLookAndFeel(actionEvent.getActionCommand());
				SwingUtilities.updateComponentTreeUI(this.parent);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent, "Umschalten des Themes fehlgeschlagen.\n\nFehlermeldung: "+ex.getMessage(), "Fehler beim Öffnen", JOptionPane.ERROR_MESSAGE);
			} 
		}
		//Hilfe-Optionen
		else if(actionEvent.getActionCommand().equals(ACTION_ABOUT)){
			JOptionPane.showMessageDialog(parent, new YADTAboutPanel(), "Über YADT", JOptionPane.PLAIN_MESSAGE);
		}
		//Auffang-Option
		else{
			JOptionPane.showMessageDialog(parent, "Kommando noch nicht implementiert: "+actionEvent.getActionCommand());
		}
	}
	
	/**
	 * Erstellt eine neue Spielgruppe
	 * @return true wenn das erstellen erfolgreich war, false sonst
	 */
	private boolean createNewSpielgruppe(){
		//Aktuelle Änderungen abfragen
				if (this.parent.unsavedChangesFlag){
					int confirm = JOptionPane.showConfirmDialog(parent, "Es gibt ungespeicherte Änderungen. Diese werden beim Laden verworfen. Fortfahren?", "Aktuelle Änderungen verwerfen?", JOptionPane.OK_CANCEL_OPTION);
					if (confirm != JOptionPane.OK_OPTION) return false;
				}
		
		this.parent.saveFile = null;
		parent.start(SpielgruppenKonfiguration.getStandadard());
		return true;
	}
	
	/**
	 * Speichert eine Spielgruppe
	 * @return true wenn das speichern erfolgreich war, false sonst
	 */
	private boolean saveSpielgruppe(){
		if (parent.saveFile != null) return saveSpielgruppe(parent.saveFile);
		else return saveAsSpielgruppe();
	}
	
	private boolean saveAsSpielgruppe(){
		File saveFile;
		
		int returnState = fileChooser.showSaveDialog(this.parent);
		
		if (returnState == JFileChooser.APPROVE_OPTION)	saveFile = fileChooser.getSelectedFile();
		else return false;
		
		boolean saveSuccesful = saveSpielgruppe(saveFile);
		
		if (saveSuccesful){
			this.parent.saveFile = saveFile;
			this.parent.updateFrameTitle();
			return true;
		}
		else return false;
		
	}
	
	private boolean saveSpielgruppe(File saveFile){
		try {
			parent.mainContentPane.spielgruppenKonfiguration.save(saveFile);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(parent, e.getMessage(), "Fehler beim Speichern", JOptionPane.ERROR_MESSAGE);
		}
		
		this.parent.setCurrentChanges(false);
		return true;
	}
	
	/**
	 * Öffnet eine Spielgruppe
	 * @return true wenn das Öffnen erfolgreich war, false sonst
	 */
	private boolean openSpielgruppe(){
		
		//Aktuelle Änderungen abfragen
		if (this.parent.unsavedChangesFlag){
			int confirm = JOptionPane.showConfirmDialog(parent, "Es gibt ungespeicherte Änderungen. Diese werden beim Laden verworfen. Fortfahren?", "Aktuelle Änderungen verwerfen?", JOptionPane.OK_CANCEL_OPTION);
			if (confirm != JOptionPane.OK_OPTION) return false;
		}
		
		//File Chooser anzeigen
		int returnState = fileChooser.showOpenDialog(parent);
		
		if (returnState == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			try {
				SpielgruppenKonfiguration spielgruppenKonfiguration = SpielgruppenKonfiguration.load(file);
				spielgruppenKonfiguration.integrityCheck();
				this.parent.start(spielgruppenKonfiguration);
				this.parent.saveFile = file;
				this.parent.updateFrameTitle();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(parent, "Das zu öffnende File ist kein gültiges Spielgruppen-File.\n\nFehlermeldung: "+e.getMessage(), "Fehler beim Öffnen", JOptionPane.ERROR_MESSAGE);
			} catch (ClassCastException e) {
				JOptionPane.showMessageDialog(parent, "Das zu öffnende File ist kein gültiges Spielgruppen-File.\n\nFehlermeldung: "+e.getMessage(), "Fehler beim Öffnen", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(parent, "File konnte nicht gelesen werden.\n\nFehlermeldung: "+e.getMessage(), "Fehler beim Öffnen", JOptionPane.ERROR_MESSAGE);
			}
		}
		return true;
	}

}
