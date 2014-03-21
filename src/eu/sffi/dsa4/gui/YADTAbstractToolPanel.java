/**
 * 
 */
package eu.sffi.dsa4.gui;

import javax.swing.JPanel;

/**
 * @author Andi Popp
 *
 */
public abstract class YADTAbstractToolPanel extends JPanel implements ChecksCurrentChanges{

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

	/**
	 * Verweist zurück auf die {@link YADTMainContentPane} auf der der YADTElixierArtEditor liegt
	 */
	protected YADTMainContentPane parent;
	
	protected abstract JPanel createTopPanel();
	
	protected abstract JPanel createMainPanel();
	
	@Override
	public void setCurrentChanges(boolean changeFlag) {
		parent.setCurrentChanges(changeFlag);
	}
}
