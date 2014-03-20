/**
 * 
 */
package eu.sffi.dsa4.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Andi Popp
 *
 */
public class YADTHeldenPanel extends YADTAbstractToolPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6589135100286248290L;

	public YADTHeldenPanel(){
		this.add(new JLabel("Heldenpanel"));
	}
	
	public String toString(){
		return "Helden";
	}

	@Override
	protected JPanel createTopPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JPanel createMainPanel() {
		// TODO Auto-generated method stub
		return null;
	}
}
