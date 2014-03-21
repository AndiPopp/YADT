/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import eu.sffi.dsa4.alchemie.AlchemieKonfiguration;
import eu.sffi.dsa4.alchemie.ElixierArt;
import eu.sffi.dsa4.alchemie.ElixierGruppe;
import eu.sffi.dsa4.gui.elements.Icons;
import eu.sffi.dsa4.gui.elements.Spacing;

/**
 * @author Andi Popp
 *
 */
public class YADTElixierArtEditor extends YADTAbstractNamedObjectEditor<ElixierArt>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4662777689043615891L;
		
	//Elemente die ausgelesen werde müssen
	JComboBox<ElixierGruppe> gruppenComboBox;
	JSpinner analyseSpinner = new JSpinner(new SpinnerNumberModel(0, -99, 99, 1));
	
	/**
	 * 
	 */
	public YADTElixierArtEditor(AlchemieKonfiguration alchemieKonfiguration, YADTMainContentPane parent) {
		super(alchemieKonfiguration.elixierArten, parent, ElixierArt.getFather());
	}
	
	
	private void selectGruppe(JComboBox<ElixierGruppe> gruppen, ElixierArt elixierArt){
		for (int i = 0; i < gruppen.getItemCount(); i++){
			if (gruppen.getItemAt(i).getWert() == elixierArt.gruppe){
				gruppen.setSelectedIndex(i);
				break;
			}
		}
	}
		
	public String toString(){
		return "Elixiertypen";
	}

	@Override
	public boolean specificActionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ElixierArtEditor:Save")){
			ElixierGruppe elixierGruppe = (ElixierGruppe) this.gruppenComboBox.getSelectedItem();
			int analyseSchwierigkeit = (Integer) this.analyseSpinner.getValue();
			this.selectedObject.analyseSchwierigkeit = analyseSchwierigkeit;
			this.selectedObject.gruppe = elixierGruppe.getWert();
			setCurrentChanges(true);
			return true;
		}
		else{
			return false;
		}
	}


	@Override
	public JPanel getMainContentPanel() {
		//Globale Variablen initialisieren
		this.gruppenComboBox = new JComboBox<ElixierGruppe>(ElixierGruppe.getAlleGruppen());
		this.analyseSpinner = new JSpinner(new SpinnerNumberModel(0, -99, 99, 1));
		
		//Panel zur Ausgabe initialisieren
		JPanel mainConentPanel = new JPanel();
		mainConentPanel.setLayout(new BoxLayout(mainConentPanel, BoxLayout.Y_AXIS));
		
		//Gruppenelemente
		JPanel gruppenPanel = new JPanel(); //Das Panel der beiden Gruppenobjekte
		gruppenPanel.setLayout(new BoxLayout(gruppenPanel, BoxLayout.X_AXIS));
			gruppenPanel.add(new JLabel("Gruppe"));
			
			Spacing.addHorizontalSpacer(gruppenPanel, 25);
			
			selectGruppe(gruppenComboBox, selectedObject);
			gruppenPanel.add(gruppenComboBox);
		mainConentPanel.add(gruppenPanel);
		
		//Vertikaler Abstand
		Spacing.addVerticalSpacer(mainConentPanel, 25);
		
		//Analysschwierigkeitselemente
		JPanel analysePanel = new JPanel(); //Das Panel der beiden Analyseobjekte
		analysePanel.setLayout(new BoxLayout(analysePanel, BoxLayout.X_AXIS));
			analysePanel.add(new JLabel("Analyseschwierigkeit"));
			
			Spacing.addHorizontalSpacer(analysePanel, 25);
			
			analyseSpinner.setValue(new Integer(this.selectedObject.analyseSchwierigkeit));
			analysePanel.add(analyseSpinner);
		mainConentPanel.add(analysePanel);
		
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


	
}
