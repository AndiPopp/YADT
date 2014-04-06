/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
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
	
	public YADTNavPanel navigation;
	private JComponent centerComponent;

	
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
		if (this.centerComponent != null) this.remove(this.centerComponent);
		
		if (panel instanceof ScrollCheck){
			if (((ScrollCheck)panel).putInScrollPane()){
				this.centerComponent = new JScrollPane(panel);
		 		this.add(this.centerComponent, BorderLayout.CENTER);
			}
			else{
				this.centerComponent = new JPanel(new BorderLayout());
				this.centerComponent.add(panel);
				this.add(this.centerComponent, BorderLayout.CENTER);
			}
		}
		else{
			this.centerComponent = new JScrollPane(panel);
	 		this.add(this.centerComponent, BorderLayout.CENTER);
		}
		
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
