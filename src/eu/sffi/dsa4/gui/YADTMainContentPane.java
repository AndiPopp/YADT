/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eu.sffi.dsa4.SpielgruppenKonfiguration;
import eu.sffi.dsa4.util.VerboseOut;

/**
 * @author Andi Popp
 *
 */
public class YADTMainContentPane extends JPanel implements ChecksCurrentChanges {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4649129815474118255L;
	
	public final SpielgruppenKonfiguration spielgruppenKonfiguration;
	private YADTMainFrame parent;
	
	private YADTNavPanel navigation;
	private JScrollPane mainScrollPane;
	private JPanel mainPanel;
	
	/**
	 * @param spielgruppenKonfiguration
	 */
	public YADTMainContentPane(	SpielgruppenKonfiguration spielgruppenKonfiguration, YADTMainFrame parent) {
		this.spielgruppenKonfiguration = spielgruppenKonfiguration;
		this.parent = parent;
		
		//Layout
		this.setLayout(new BorderLayout());
		
		//Create nav
		this.navigation = new YADTNavPanel(this);
		this.add(this.navigation, BorderLayout.WEST);
				
	}
	
	public void updateMainPanel(JPanel panel){
		VerboseOut.CONSOLE.println("Updating main panel to "+panel);
		if (this.mainScrollPane != null) this.remove(this.mainScrollPane);
		this.mainPanel = panel;
		this.mainScrollPane = new JScrollPane(panel);
 		this.add(this.mainScrollPane, BorderLayout.CENTER);
 		this.revalidate();
	}
	
	public void multiRevalidate(){
		this.revalidate();
		parent.revalidate();
	}

	@Override
	public void setCurrentChanges(boolean changeFlag) {
		parent.setCurrentChanges(changeFlag);
	}
	

}
