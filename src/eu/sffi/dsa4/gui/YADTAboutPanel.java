/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Andi Popp
 *
 */
public class YADTAboutPanel extends JPanel {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5394969051045417524L;

	public static final short VERSION = 10;
	
	/**
	 * Erstellt ein neues About-Panel
	 */
	public YADTAboutPanel() {
		this.setLayout(new BorderLayout());
		
		JEditorPane aboutPane = new JEditorPane("text/html",
				"<html>"+
				"<p>YADT Version "+(YADTAboutPanel.VERSION/100.0)+" alpha</p>"+
				"<p>(c) 2014 Andi Popp</p>"+
				"<p><b>Lizenz</b></p>"+
				"<p>Diese Software steht unter GNU GPL Version 3 (http://www.gnu.de/documents/gpl-3.0.de.html)</p>"+
				"<p><b>Anerkennungshinweise</b></p>"+
				"<p>Dieses Produkt beinhaltet Software, die von der Apache Software Foundation entwickelt wurde. (http://www.apache.org/)</p>"+
				"<p>Apache Commons Codec. Copyright 2002-2013 The Apache Software Foundation.</p> "+
				"<p>Apache Commons Lang. Copyright 2001-2014 The Apache Software Foundation.</p> "+
				"</html>"
				);
		
		
		
		this.add(new JScrollPane(aboutPane));
		this.setPreferredSize(new Dimension(400, 200));

	}
	
	
}
