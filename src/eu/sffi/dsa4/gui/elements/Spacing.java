/**
 * 
 */
package eu.sffi.dsa4.gui.elements;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Andi Popp
 *
 */
public class Spacing {

	public static void addHorizontalSpacer(JComponent Panel, int spacing){
		JPanel spacer = new JPanel();
		spacer.setMinimumSize(new Dimension(spacing, 0));
		Panel.add(spacer);
	}
	
	public static void addVerticalSpacer(JComponent Panel, int spacing){
		JPanel spacer = new JPanel();
		spacer.setMinimumSize(new Dimension(0, spacing));
		Panel.add(spacer);
	}
	
}
