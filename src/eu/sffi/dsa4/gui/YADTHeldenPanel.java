/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import eu.sffi.dsa4.SpielgruppenKonfiguration;
import eu.sffi.dsa4.gui.elements.Icons;
import eu.sffi.dsa4.gui.elements.Spacing;
import eu.sffi.dsa4.held.Held;

/**
 * @author Andi Popp
 *
 */
public class YADTHeldenPanel extends YADTAbstractNamedObjectEditor<Held> implements ActionListener{

	/* ******
	 * Felder
	 * ******/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6589135100286248290L;

	//Eingabeobjekte, die referenziert werden müssen
	JSpinner[] eigenschaftsSpinner;
	
	
	/* *************************************
	 * Konstruktoren und zugehörige Methoden
	 * *************************************/
	
 	public YADTHeldenPanel(SpielgruppenKonfiguration spielgruppenKonfiguration, YADTMainContentPane parent){
		//Globale Variablen setzen
 		super(spielgruppenKonfiguration.heldenListe, parent, Held.getFather());
	}


 	@Override
	public JPanel getMainContentPanel() {
		JPanel mainConentPanel = new JPanel();
		BoxLayout mainPanelLayout = new BoxLayout(mainConentPanel, BoxLayout.Y_AXIS);
		mainConentPanel.setLayout(mainPanelLayout);
		
		JLabel eigenschaftswerteTitelLabel = new JLabel("<html><h2>Eigenschaftswerte</h2></html>");
		eigenschaftswerteTitelLabel.setHorizontalAlignment(JLabel.LEFT);
		eigenschaftswerteTitelLabel.setLayout(new BorderLayout());
		mainConentPanel.add(eigenschaftswerteTitelLabel);
		mainConentPanel.add(eigenschaftsPanel());
		
		//TODO Main Panel content
		
		//Vertikaler Abstand
		Spacing.addVerticalSpacer(mainConentPanel, 25);
		
		//Speichern Button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
			Spacing.addHorizontalSpacer(buttonPanel, 25);
			
			JButton saveButton = new JButton("Änderungen speichern", Icons.DISK_ICON);
			saveButton.setActionCommand("ElixierArtEditor:Save");
			saveButton.addActionListener(this);
			buttonPanel.add(saveButton);
			
			Spacing.addHorizontalSpacer(buttonPanel, 25);
			
		mainConentPanel.add(buttonPanel);
		
		//return
		return mainConentPanel;
	}
 	
 	/**
 	 * Erzeugt ein Panel mit GridLayout auf dem Spinner zur Auswahl
 	 * der Eigenschaftswerte angebracht werden
 	 * 
 	 * @return das Panel auf dem die eigenschaftsSpinner-Objekte angeordnet sind
 	 */
 	private JPanel eigenschaftsPanel(){
 		//Globale Variablen initialisieren
 		eigenschaftsSpinner = new JSpinner[8];
 		
 		GridLayout eigentschaftsPanelLayout = new GridLayout(2, 8);
 		eigentschaftsPanelLayout.setVgap(5);
 		eigentschaftsPanelLayout.setHgap(5);
 		JPanel eigenschaftsPanel = new JPanel(eigentschaftsPanelLayout);
 		for (byte i = 0; i < 8; i++){
 			JLabel bezeichnerLabel = new JLabel(Held.EIGENSCHAFT(i)+" ");
 			bezeichnerLabel.setHorizontalAlignment(JLabel.RIGHT);
 			eigenschaftsPanel.add(bezeichnerLabel);
 			eigenschaftsSpinner[i] = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
 			eigenschaftsSpinner[i].setValue(new Integer(selectedObject.eigenschaft[i]));
 			eigenschaftsPanel.add(eigenschaftsSpinner[i]);
 		}
 		return eigenschaftsPanel;
 	}

	/* *******
	 * Actions
	 * *******/
	
	@Override
	public boolean specificActionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Platzhaltercommand")){ //TODO
			return true;
		}
		else{
			return false;
		}
	}

	
	/* *********
	 * Overrides
	 * *********/
	
	@Override
	public String toString(){
		return "Helden";
	}


	


	
}
