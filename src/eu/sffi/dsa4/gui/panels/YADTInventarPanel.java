/**
 * 
 */
package eu.sffi.dsa4.gui.panels;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import eu.sffi.dsa4.SpielgruppenKonfiguration;
import eu.sffi.dsa4.gui.YADTMainContentPane;
import eu.sffi.dsa4.items.HatInventar;

/**
 * @author Andi Popp
 *
 */
public class YADTInventarPanel extends YADTAbstractToolPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1305903505246896558L;

	/**
	 * Beinhaltet die Helden und den Itempool
	 */
	private Vector<HatInventar> inventare;
	
	//Swing-Objekte
	
	private JComboBox<HatInventar> inventarComboBox;
	
	//Konstruktoren und verwandte Methoden;
	
	public YADTInventarPanel(SpielgruppenKonfiguration spielgruppenKonfiguration, YADTMainContentPane parent){
		super(parent);
		
		this.inventare = new Vector<HatInventar>();
		this.inventare.add(spielgruppenKonfiguration.itemPool);
		
		
		this.setLayout(new BorderLayout());
	}
	
	/* (non-Javadoc)
	 * @see eu.sffi.dsa4.gui.YADTAbstractToolPanel#createTopPanel()
	 */
	@Override
	protected JPanel createTopPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see eu.sffi.dsa4.gui.YADTAbstractToolPanel#createMainPanel()
	 */
	@Override
	protected JPanel createMainPanel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected JPanel createBottomPanel(){
		//TODO
		return null;
	}

}
