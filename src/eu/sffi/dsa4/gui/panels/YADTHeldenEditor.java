/**
 * 
 */
package eu.sffi.dsa4.gui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eu.sffi.dsa4.SpielgruppenKonfiguration;
import eu.sffi.dsa4.gui.YADTMainContentPane;
import eu.sffi.dsa4.gui.elements.Icons;
import eu.sffi.dsa4.gui.elements.Spacing;
import eu.sffi.dsa4.held.Held;
import eu.sffi.dsa4.held.talente.Talent;
import eu.sffi.dsa4.held.talente.TalentWert;
import eu.sffi.dsa4.held.talente.WuerfelTalentWert;

/**
 * @author Andi Popp
 * 
 */
public class YADTHeldenEditor extends YADTAbstractNameConstructableObjectEditor<Held>
		implements ActionListener, ChangeListener {

	/* ******
	 * Felder *****
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 6589135100286248290L;

	// Eingabeobjekte, die referenziert werden müssen
	/**
	 * Die Spinner, welche die Eigenschaftswerte angeben
	 */
	private JSpinner[] eigenschaftsSpinner;

	private JComboBox<TalentWert> talentWertComboBox;

	private JSpinner talentWertSpinner;

	private boolean currentlyAutoupdatingTalentWertSpinner;
	
	private JSpinner erschwernisSpinner;

	private SpielgruppenKonfiguration spielgruppenKonfiguration;
	
	/* *************************************
	 * Konstruktoren und zugehörige Methoden
	 * ************************************
	 */

	public YADTHeldenEditor(
			SpielgruppenKonfiguration spielgruppenKonfiguration,
			YADTMainContentPane parent) {
		// Globale Variablen setzen
		super(spielgruppenKonfiguration.heldenListe, parent);
		this.spielgruppenKonfiguration = spielgruppenKonfiguration;
	}

	@Override
	public JPanel getMainContentPanel() {
		JPanel mainConentPanel = new JPanel();
		BoxLayout mainPanelLayout = new BoxLayout(mainConentPanel,
				BoxLayout.Y_AXIS);
		mainConentPanel.setLayout(mainPanelLayout);

		// EigenschaftsPanel
		JPanel eigenschaftsOuterPanel = new JPanel(new BorderLayout());
		JLabel eigenschaftsTitelLabel = new JLabel(
				"<html><h2>Eigenschaftswerte</h2>Änderungen werden automatisch gespeichert<p>&nbsp;</p></html>");
		eigenschaftsOuterPanel.add(eigenschaftsTitelLabel, BorderLayout.NORTH);
		eigenschaftsOuterPanel.add(getEigenschaftsPanel(), BorderLayout.CENTER);
		mainConentPanel.add(eigenschaftsOuterPanel);

		// Vertikaler Abstand
		Spacing.addVerticalSpacer(mainConentPanel, 25);

		// Vertikaler Abstand
		Spacing.addVerticalSpacer(mainConentPanel, 25);

		// TalentPanel
		JPanel talentOuterPanel = new JPanel(new BorderLayout());
		JLabel talentTitelLabel = new JLabel(
				"<html><h2>Talentwerte</h2></html>");
		talentOuterPanel.add(talentTitelLabel, BorderLayout.NORTH);
		talentOuterPanel.add(getTalentPanel(), BorderLayout.CENTER);
		talentOuterPanel.add(getWurfPanel(), BorderLayout.SOUTH);
		mainConentPanel.add(talentOuterPanel);

		// return
		return mainConentPanel;
	}

	/**
	 * Erzeugt ein Panel mit GridLayout auf dem Spinner zur Auswahl der
	 * Eigenschaftswerte angebracht werden
	 * 
	 * @return das Panel auf dem die eigenschaftsSpinner-Objekte angeordnet sind
	 */
	private JPanel getEigenschaftsPanel() {
		// Globale Variablen initialisieren
		eigenschaftsSpinner = new JSpinner[Held.ANZAHL_EIGENSCHAFTEN];

		GridLayout eigentschaftsPanelLayout = new GridLayout(2, 8);
		eigentschaftsPanelLayout.setVgap(5);
		eigentschaftsPanelLayout.setHgap(5);
		JPanel eigenschaftsPanel = new JPanel(eigentschaftsPanelLayout);
		for (byte i = 0; i < Held.ANZAHL_EIGENSCHAFTEN; i++) {
			JLabel bezeichnerLabel = new JLabel(Held.EIGENSCHAFT(i) + " ");
			bezeichnerLabel.setHorizontalAlignment(JLabel.RIGHT);
			eigenschaftsPanel.add(bezeichnerLabel);
			eigenschaftsSpinner[i] = new JSpinner(new SpinnerNumberModel(0, 0,
					99, 1));
			eigenschaftsSpinner[i].setValue(new Integer(
					selectedObject.eigenschaft[i]));
			eigenschaftsSpinner[i].addChangeListener(this);
			eigenschaftsPanel.add(eigenschaftsSpinner[i]);
		}
		return eigenschaftsPanel;
	}

	private JPanel getTalentPanel() {
		JPanel talentPanel = new JPanel();
		talentPanel.setLayout(new FlowLayout());

		talentWertComboBox = new JComboBox<TalentWert>(new Vector<TalentWert>(
				this.selectedObject.talentWerte.values()));
		talentWertComboBox.setActionCommand("Heldeneditor:talentWertComboBoxChanged");
		talentWertComboBox.addActionListener(this);
		talentPanel.add(talentWertComboBox);

		talentWertSpinner = new JSpinner(new SpinnerNumberModel(0, -24, 24, 1));
		talentWertSpinner.addChangeListener(this);
		TalentWert selectedTalentWert = (TalentWert) talentWertComboBox.getSelectedItem();
		if (selectedTalentWert != null) {
			this.currentlyAutoupdatingTalentWertSpinner = true;
			talentWertSpinner.setValue(new Integer(selectedTalentWert.getTAP()));
			this.currentlyAutoupdatingTalentWertSpinner = false;
		}
		talentPanel.add(talentWertSpinner);

		JButton addTalentButton = new JButton("Hinzufügen...");
		addTalentButton.setActionCommand("HeldenEditor:TalentHinzufügen");
		addTalentButton.addActionListener(this);
		talentPanel.add(addTalentButton);

		return talentPanel;
	}

	private JPanel getWurfPanel() {
		JPanel wurfPanel = new JPanel();
		wurfPanel.setLayout(new FlowLayout());

		wurfPanel.add(new JLabel("Talent werfen mit Erschwernis:"));
		this.erschwernisSpinner = new JSpinner(new SpinnerNumberModel(0, -99,
				99, 1));
		wurfPanel.add(this.erschwernisSpinner);

		JButton wurfButton = new JButton(Icons.DICE_ICON);
		wurfButton.setActionCommand("HeldenEditor:TalentWerfen");
		wurfButton.addActionListener(this);
		wurfPanel.add(wurfButton);

		return wurfPanel;
	}

	/* *******
	 * Actions ******
	 */

	@Override
	public boolean specficLevel2ActionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("HeldenEditor:TalentHinzufügen")) {
			addTalent();
			return true;
		}
		else if (e.getActionCommand().equals("Heldeneditor:talentWertComboBoxChanged")){
			this.currentlyAutoupdatingTalentWertSpinner = true;
			updateTalentwertSpinner();
			this.currentlyAutoupdatingTalentWertSpinner = false;
			return true;
		}
		else if (e.getActionCommand().equals("HeldenEditor:TalentWerfen")){
			boolean wuerfelTalent = wirfTalent();
			if (!wuerfelTalent) JOptionPane.showMessageDialog(this, ((TalentWert)talentWertComboBox.getSelectedItem())+" ist kein werfbares Talent.", "Kann Talent nicht werfen	", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else return false;
	}

	private boolean addTalent() {
		// Die Menge der noch nicht hinzugefügten Talente für die Auswahlbox
		Vector<Talent> addbareTalente = new Vector<Talent>(
				spielgruppenKonfiguration.talentListe.values());

		// Wirf alle Talente aus der Auswahlbox, für die der Held bereits
		// Talentwerte hat
		Vector<Talent> vorhandeneTalente = new Vector<Talent>();
		for (Iterator<Talent> it = addbareTalente.iterator(); it.hasNext();) {
			Talent talentToCheck = it.next();
			if (this.selectedObject.talentWerte.containsKey(talentToCheck)) {
				vorhandeneTalente.add(talentToCheck);
			}
		}
		addbareTalente.removeAll(vorhandeneTalente);

		// Falls der Vector nun leer ist, zeig eine Fehlermeldung und brich ab
		if (addbareTalente.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Der Held hat bereits für alle Talente einen Wert",
					"Kann nichts hinzufügen", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// In Array umwandeln für Auswahldialogbox
		Talent[] finalAddbareTalente = new Talent[0];
		finalAddbareTalente = addbareTalente.toArray(finalAddbareTalente);

		// Auswahldialog anzeigen
		Talent gewaehltesTalent = (Talent) JOptionPane.showInputDialog(this, // parentComponent,
				"Für welches Talent soll ein Wert hinzugefügt werden?", // message,
				"Talent wählen", // title,
				JOptionPane.PLAIN_MESSAGE, // messageType,
				null, // icon,
				finalAddbareTalente, // selectionValues,
				finalAddbareTalente[0] // initialSelectionValue
				);

		if (gewaehltesTalent == null)
			return false; // Die Auswahl wurde abgebrochen

		// Dem Helden den Talentwert hinzufügen
		TalentWert neuerTalentWert = gewaehltesTalent.getTalentWert(0,
				selectedObject);
		this.selectedObject.talentWerte.put(neuerTalentWert.getTalent(),
				neuerTalentWert);

		// Auswahlbox updaten
		this.remove(mainPanel);
		this.mainPanel = createMainPanel();
		this.add(this.mainPanel, BorderLayout.CENTER);
		this.revalidate();

		// Auswahl auf den gerade erstellten Talentwert setzen
		this.talentWertComboBox.setSelectedItem(neuerTalentWert);

		// Alles hat geklappt, Talent wurde hinzugefügt return true
		return true;
	}

	private boolean updateTalentwertSpinner(){
		TalentWert selectedTalentWert = (TalentWert)talentWertComboBox.getSelectedItem();
		if (selectedTalentWert == null) return false;
		
		this.currentlyAutoupdatingTalentWertSpinner = true;
		talentWertSpinner.setValue(selectedTalentWert.getTAP());
		this.currentlyAutoupdatingTalentWertSpinner = false;
		return true;
	}

	/**
	 * Wirft das aktuell ausgewählte Talent mit der ausgewählten Erschwernis und
	 * gibt das Ergebnis in einem MessageDialog aus.
	 * @return true wenn das Talent gewürfelt wurde, false wenn es kein Würfeltalent war
	 */
	private boolean wirfTalent(){
		TalentWert talentWert = (TalentWert) this.talentWertComboBox.getSelectedItem();
		if (talentWert instanceof WuerfelTalentWert){
			WuerfelTalentWert wuerfelTalentWert = (WuerfelTalentWert) talentWert;
			int erschwernis = (Integer) this.erschwernisSpinner.getValue();
			int tapStern = wuerfelTalentWert.werfen(erschwernis);
			if (tapStern < 0){
				JOptionPane.showMessageDialog(this, "Talentwurf misslungen mit TAP*="+tapStern, "Talentwurf misslungen", JOptionPane.WARNING_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(this, "Talentwurf gelungen mit TAP*="+tapStern, "Talentwurf gelungen", JOptionPane.INFORMATION_MESSAGE);
			}
			return true;
		}
		else return false;
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// Ist einer der Eigenschaftsspinner die Quelle?
		for (byte i = 0; i < Held.ANZAHL_EIGENSCHAFTEN; i++) {
			if (arg0.getSource().equals(eigenschaftsSpinner[i])) {
				// Der Spinner mit dem Byte-Wert der Eigenschaft i ist der
				// Auslöser
				this.selectedObject.eigenschaft[i] = (Integer) this.eigenschaftsSpinner[i]
						.getValue();
				setCurrentChanges(true);
			}
		}
		// Ist der Talentwertspinner die Quelle?
		if (arg0.getSource().equals(talentWertSpinner)){
			if (!this.currentlyAutoupdatingTalentWertSpinner) { //Wenn das Update automatisch war, muss nichts angepasst werden
				TalentWert selectedTalentWert = (TalentWert) talentWertComboBox.getSelectedItem();
				if (selectedTalentWert != null) {
					selectedTalentWert.setTAP((Integer)talentWertSpinner.getValue());
				}
				setCurrentChanges(true);
			}
		}
	}

	/* *********
	 * Overrides ********
	 */

	@Override
	public String toString() {
		return "Helden";
	}

	@Override
	public Held getNewObject(String name) {
		return new Held(name);
	}

}
