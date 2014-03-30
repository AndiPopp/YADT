/**
 * 
 */
package eu.sffi.dsa4.gui.elements;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import eu.sffi.dsa4.items.Item;

/**
 * @author Andi Popp
 *
 */
public class ItemContextMenu extends JPopupMenu {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 2060296923710573559L;

	/**
	 * The item manipulated by this popup menu
	 */
	private Item item;

	public ItemContextMenu(Item item, ActionListener actionListener) {
		this.item = item;
		
		JMenuItem mi;
		
		mi = new JMenuItem("Entfernen");
		mi.addActionListener(actionListener);
		this.add(mi);
		
	}
	
	/**
	 * Gets the item manipulated by this popup menu
	 * @return the item manipulated by this popup menu
	 */
	public Item getItem(){
		return this.item;
	}
	
}
