/**
 * 
 */
package eu.sffi.dsa4.gui.elements;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Andi Popp
 * A class with a static collection of Icons
 */
public class Icons {

	public static int menuIconSize = 24;
	
	public static final ImageIcon DISK_ICON = getICON("media/icons/media-floppy.png");
	public static final ImageIcon NEW_ICON = getICON("media/icons/document-new.png");
	public static final ImageIcon OPEN_ICON = getICON("media/icons/document-open.png");
	public static final ImageIcon SAVE_ICON = getICON("media/icons/document-save.png");
	public static final ImageIcon SAVE_AS_ICON = getICON("media/icons/document-save-as.png");
	public static final ImageIcon SWORD_ICON = getICON("media/icons/nicubunu_Toy_Sword.png");
	public static final ImageIcon DICE_ICON = getICON("media/icons/dice.png");
	
	private static final ImageIcon getICON(String fileName){
		ImageIcon tempIcon = new ImageIcon(fileName);
		Image iconImage = tempIcon.getImage().getScaledInstance(menuIconSize, menuIconSize, Image.SCALE_SMOOTH);
		return new ImageIcon(iconImage);
	}
	
}
