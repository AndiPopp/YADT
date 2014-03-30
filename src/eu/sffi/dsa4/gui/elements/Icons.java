/**
 * 
 */
package eu.sffi.dsa4.gui.elements;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import eu.sffi.dsa4.util.RessourceLoader;

/**
 * @author Andi Popp
 * A class with a static collection of Icons
 */
public class Icons {

	public static int menuIconSize = 24;
	
	public static final ImageIcon DISK_ICON = getICON("/icons/media-floppy.png");
	public static final ImageIcon NEW_ICON = getICON("/icons/document-new.png");
	public static final ImageIcon OPEN_ICON = getICON("/icons/document-open.png");
	public static final ImageIcon SAVE_ICON = getICON("/icons/document-save.png");
	public static final ImageIcon SAVE_AS_ICON = getICON("/icons/document-save-as.png");
	public static final ImageIcon SWORD_ICON = getICON("/icons/nicubunu_Toy_Sword.png");
	public static final ImageIcon DICE_ICON = getICON("/icons/dice.png");
	public static final ImageIcon EDIT_ICON = getICON("/icons/edit.png");
	public static final ImageIcon REFRESH_ICON = getICON("/icons/view-refresh.png");
	public static final ImageIcon REFRESH_ICON_SMALL = getICON("/icons/view-refresh_small.png");
	
	private static final ImageIcon getICON(String fileName){
		try {
			BufferedImage iconImage = ImageIO.read(RessourceLoader.load(fileName));
			return new ImageIcon(iconImage);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
