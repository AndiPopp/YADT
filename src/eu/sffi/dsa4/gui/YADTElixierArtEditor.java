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
public class YADTElixierArtEditor extends YADTAbstractToolPanel implements ActionListener, ChecksCurrentChanges {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4662777689043615891L;

	private AlchemieKonfiguration alchemieKonfiguration;

	private ElixierArt selectedElixierArt;
	
	//Bauelemente
	
	
		
	/**
	 * Verweist zurück auf die {@link YADTMainContentPane} auf der der YADTElixierArtEditor liegt
	 */
	private YADTMainContentPane parent;
	
	//Elemente die ausgelesen werde müssen
	JComboBox<ElixierGruppe> gruppenComboBox = new JComboBox<ElixierGruppe>(ElixierGruppe.getAlleGruppen());
	JSpinner analyseSpinner = new JSpinner(new SpinnerNumberModel(0, -99, 99, 1));
	
	/**
	 * 
	 */
	public YADTElixierArtEditor(AlchemieKonfiguration alchemieKonfiguration, YADTMainContentPane parent) {
		//Global variables
		this.alchemieKonfiguration = alchemieKonfiguration;
		this.parent = parent;
		
		//Set Layout
		this.setLayout(new BorderLayout());
		
		//Top Panel
		this.topPanel = createTopPanel();
		this.add(topPanel, BorderLayout.NORTH);
		
		//Main Panel
		this.mainPanel = createMainPanel();
		this.add(this.mainPanel, BorderLayout.CENTER);
		
	}

	protected JPanel createMainPanel() {
		//Außen zentrieren
		JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		//Border
		JPanel mainBorderPanel = new JPanel(new BorderLayout());
		TitledBorder mainBorder = BorderFactory.createTitledBorder(this.selectedElixierArt.getName());
		mainBorder.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,15));
		mainBorderPanel.setBorder(mainBorder);
		
		//InnererAbstand
		JPanel mainConentPanel = new JPanel();
		mainConentPanel.setLayout(new BoxLayout(mainConentPanel, BoxLayout.Y_AXIS));
		mainConentPanel.setBorder(new EmptyBorder(15, 50, 15, 50));
		
		//Gruppenelemente
		JPanel gruppenPanel = new JPanel(); //Das Panel der beiden Gruppenobjekte
		gruppenPanel.setLayout(new BoxLayout(gruppenPanel, BoxLayout.X_AXIS));
			gruppenPanel.add(new JLabel("Gruppe"));
			
			Spacing.addHorizontalSpacer(gruppenPanel, 25);
			
			selectGruppe(gruppenComboBox, selectedElixierArt);
			gruppenPanel.add(gruppenComboBox);
		mainConentPanel.add(gruppenPanel);
		
		//Vertikaler Abstand
		Spacing.addVerticalSpacer(mainConentPanel, 25);
		
		//Analysschwierigkeitselemente
		JPanel analysePanel = new JPanel(); //Das Panel der beiden Analyseobjekte
		analysePanel.setLayout(new BoxLayout(analysePanel, BoxLayout.X_AXIS));
			analysePanel.add(new JLabel("Analyseschwierigkeit"));
			
			Spacing.addHorizontalSpacer(analysePanel, 25);
			
			analyseSpinner.setValue(new Integer(this.selectedElixierArt.analyseSchwierigkeit));
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
		
		//Zusammenpacken
		mainBorderPanel.add(mainConentPanel, BorderLayout.CENTER);
		mainPanel.add(mainBorderPanel);
		return mainPanel;
	}
	
	private void selectGruppe(JComboBox<ElixierGruppe> gruppen, ElixierArt elixierArt){
		for (int i = 0; i < gruppen.getItemCount(); i++){
			if (gruppen.getItemAt(i).getWert() == elixierArt.gruppe){
				gruppen.setSelectedIndex(i);
				break;
			}
		}
	}
		
	protected JPanel createTopPanel(){
		JPanel topPanel = new JPanel(new GridLayout(0, 1));
		
		//Vertikaler Abstand
		Spacing.addVerticalSpacer(topPanel, 25);
		
		//Titlelabel
		JLabel titleLabel = new JLabel("Elixiertypen-Editor");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		topPanel.add(titleLabel);
	
		//Editpanel
		JPanel editPanel = new JPanel(new FlowLayout());
			//Dropdown-Menü
			ElixierArt[] rawList = new ElixierArt[0];
			rawList = this.alchemieKonfiguration.elixierArten.values().toArray(rawList);
			JComboBox<ElixierArt> list = new JComboBox<ElixierArt>(rawList);
			list.setActionCommand("ElixierArtEditor:ComboBoxChanged");
			list.addActionListener(this);
			updateSelectedElixierArt(list);
			editPanel.add(list);
			//Delete Button
			JButton deleteButton = new JButton("Löschen");
			deleteButton.setActionCommand("ElixierArtEditor:Delete");
			deleteButton.addActionListener(this);
			editPanel.add(deleteButton);
			//New Button
			JButton newButton = new JButton("Neu");
			newButton.setActionCommand("ElixierArtEditor:New");
			newButton.addActionListener(this);
			editPanel.add(newButton);
		topPanel.add(editPanel);
			
		return topPanel;
	}
	
	public String toString(){
		return "Elixiertypen-Editor";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getActionCommand().equals("ElixierArtEditor:ComboBoxChanged")){
			updateSelectedElixierArt((JComboBox<ElixierArt>)e.getSource());
			if (this.mainPanel != null) this.remove(this.mainPanel);
			this.mainPanel = createMainPanel();
			this.add(this.mainPanel, BorderLayout.CENTER);
			revalidate();
		}
		else if (e.getActionCommand().equals("ElixierArtEditor:Save")){
			ElixierGruppe elixierGruppe = (ElixierGruppe) this.gruppenComboBox.getSelectedItem();
			int analyseSchwierigkeit = (Integer) this.analyseSpinner.getValue();
			this.selectedElixierArt.analyseSchwierigkeit = analyseSchwierigkeit;
			this.selectedElixierArt.gruppe = elixierGruppe.getWert();
			setCurrentChanges(true);
		}
		else{
			System.out.println("Noch nicht implementiert:"+e.getActionCommand());
		}
	}
	
	public void multiRevalidate(){
		this.revalidate();
		parent.revalidate();
	}
		
	private ElixierArt updateSelectedElixierArt(JComboBox<ElixierArt> comboBox){
		this.selectedElixierArt = (ElixierArt) comboBox.getSelectedItem();
		return this.selectedElixierArt;
	}

	@Override
	public void setCurrentChanges(boolean changeFlag) {
		parent.setCurrentChanges(changeFlag);
	}
}
