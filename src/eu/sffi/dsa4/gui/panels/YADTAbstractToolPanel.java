/**
 * 
 */
package eu.sffi.dsa4.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import eu.sffi.dsa4.gui.ChecksCurrentChanges;
import eu.sffi.dsa4.gui.YADTMainContentPane;

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
	 * Das obere Panel. 
	 */
	protected JPanel topPanel;
	
	/**
	 * Das untere Panel.
	 */
	protected JPanel mainPanel;

	
	/**
	 * Verweist zurück auf die {@link YADTMainContentPane} auf der der YADTElixierArtEditor liegt
	 */
	protected YADTMainContentPane parent;
	
	/**
	 * Erstellt das Top-Panel
	 * @return
	 */
	protected abstract JPanel createTopPanel();
	
	/**
	 * Erstellt das Main-Panel
	 * @return
	 */
	protected abstract JPanel createMainPanel();
	
	public YADTAbstractToolPanel(YADTMainContentPane parent){
		this.parent = parent;
	}
	
	@Override
	public void setCurrentChanges(boolean changeFlag) {
		parent.setCurrentChanges(changeFlag);
	}
	
	/**
	 * Überschreibt die Funktion toString() von Object um die Anzeige im 
	 * Navigationsbaum zu verbessern.
	 */
	@Override
	public abstract String toString();
}
