/**
 * 
 */
package eu.sffi.dsa4.gui.elements;

import javax.swing.JButton;

import eu.sffi.dsa4.items.Item;

/**
 * @author Andi Popp
 *
 */
public class ItemButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5464619470875073867L;

	public final Item item;
	
	public ItemButton(Item item){
		super(Icons.EDIT_ICON);
		this.item = item;
	}
}
