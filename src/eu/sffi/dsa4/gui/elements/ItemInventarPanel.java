/**
 * 
 */
package eu.sffi.dsa4.gui.elements;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import eu.sffi.dsa4.items.Item;

/**
 * @author Andi Popp
 *
 */
public class ItemInventarPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -564051330500780954L;

	/**
	 * The item represented on this panel
	 */
	Item item;
	
	/**
	 * The button that opens the context menu for the item
	 */
	ItemButton itemButton;
	
	
	
	public ItemInventarPanel(Item item, ActionListener actionListener){
		this.item = item;
		this.itemButton = new ItemButton(item, actionListener);
		itemButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.setLayout(new BorderLayout());
		JTextField itemTextField = new JTextField(item.toString());
		itemTextField.setEditable(false);
		itemTextField.setBorder(new EmptyBorder(0, 10, 0, 10));
		this.add(itemTextField, BorderLayout.CENTER);
		this.add(itemButton, BorderLayout.EAST);
		this.setBorder(new EtchedBorder());
	}

}
