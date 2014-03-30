/**
 * 
 */
package eu.sffi.dsa4.gui.elements;

import java.awt.event.ActionListener;

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
	
	public final ItemContextMenu itemContextMenu;
	
	public ItemButton(Item item, ActionListener actionListener){
		super(Icons.EDIT_ICON);
		this.item = item;
		this.addActionListener(actionListener);
		itemContextMenu = new ItemContextMenu(item, actionListener);
	}
}
