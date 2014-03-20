/**
 * 
 */
package eu.sffi.dsa4.gui;

import javax.swing.JPanel;

/**
 * @author Andi Popp
 *
 */
public abstract class YADTAbstractToolPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7168800574417698546L;

	/**
	 * Das obere Panel. Beinhaltet die Möglichkeiten zur Auswahl, Löschen und neu erstellen
	 * von Elixierarten
	 */
	protected JPanel topPanel;
	
	/**
	 * Das untere Panel. Beinhaltet die Informationen des aktuell ausgewählten Elixiertyps
	 */
	protected JPanel mainPanel;

	protected abstract JPanel createTopPanel();
	
	protected abstract JPanel createMainPanel();
	
}
