package eu.sffi.dsa4.gui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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

import eu.sffi.dsa4.alchemie.AlchemieException;
import eu.sffi.dsa4.alchemie.Alchemist;
import eu.sffi.dsa4.alchemie.Elixier;
import eu.sffi.dsa4.alchemie.LaborTyp;
import eu.sffi.dsa4.alchemie.Laborqualitaet;
import eu.sffi.dsa4.alchemie.Rezept;
import eu.sffi.dsa4.gui.YADTMainContentPane;
import eu.sffi.dsa4.gui.elements.AventurischesDatumPanel;
import eu.sffi.dsa4.gui.elements.Spacing;
import eu.sffi.dsa4.held.Held;
import eu.sffi.dsa4.held.talente.WuerfelTalentWert;
import eu.sffi.dsa4.util.SimplePersistentNamedCollection;
import eu.sffi.dsa4.wuerfel.Wuerfel;

public class YADTBrauPanel extends YADTAbstractNamedObjectEditor<Alchemist>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6300796497600021930L;

	//Rezepbuchpanel-Elemente
	
	private JComboBox<Rezept> rezeptComboBox;
	
	private JButton newRezepButton;
	
	private JButton deleteRezeptButton;
	
	//Brau-Panel Elemente
	private JComboBox<LaborTyp> laborTypComboBox;
	private JComboBox<Laborqualitaet> laborQualitaetComboBox;
	private JSpinner meisterhandwerksSpinner;
	private JSpinner sonstigeProbenerschwernissSpinner;
	private JSpinner zuruckgehalteneTAPSpinner;
	private JSpinner qualitaetsBonusDurchASPSpinner;
	private JSpinner sonstigerQualitaetsBonusSpinner;
	private AventurischesDatumPanel brauDatumsPanel;
	
 	public YADTBrauPanel(SimplePersistentNamedCollection<Alchemist> objectSet,
			YADTMainContentPane parent) {
		super(objectSet, parent);
	}

	@Override
	protected void createEditPanel(){
		editPanel = new JPanel(new FlowLayout());
		comboBoxBezeichner = new JLabel("Alchemisten: ");
		editPanel.add(comboBoxBezeichner);
		//Dropdown-Menü
		Vector<Alchemist> rawVector = new Vector<Alchemist>(this.objectSet.values());
		objektComboBox = new JComboBox<Alchemist>(rawVector);
		objektComboBox.setActionCommand("NamedObjectEditor:ComboBoxChanged");
		objektComboBox.addActionListener(this);
		updateSelectedObject(objektComboBox);
		editPanel.add(objektComboBox);
		//New Button
		newButton = new JButton("Aktualisieren");
		newButton.setToolTipText("Durchsucht die Heldendatenbank nach Helden mit passenden Talentwerten");
		newButton.setActionCommand("YADTBrauPanel:Update");
		newButton.addActionListener(this);
		editPanel.add(newButton);
	}


	@Override
	public String toString() {
		return "Brauen";
	}



	@Override
	public JPanel getMainContentPanel() {
		JPanel mainConentPanel = new JPanel();
		BoxLayout mainPanelLayout = new BoxLayout(mainConentPanel,
				BoxLayout.Y_AXIS);
		mainConentPanel.setLayout(mainPanelLayout);

		// TODO Talentwertpanel?
		
		// Rezeptbuchpanel
		JPanel rezeptbuchOuterPanel = new JPanel(new BorderLayout());
		JLabel rezeptTitelLabel = new JLabel(
				"<html><h2>Rezeptbuch</h2></html>");
		
		rezeptbuchOuterPanel.add(rezeptTitelLabel, BorderLayout.NORTH);
		rezeptbuchOuterPanel.add(getRezeptPanel(), BorderLayout.CENTER);
		mainConentPanel.add(rezeptbuchOuterPanel);
		
	
		// BrauPanel
		JPanel brauOuterPanel = new JPanel(new BorderLayout());
		JLabel brauTitelLabel = new JLabel(
				"<html><h2>Ausgewähltes Rezept brauen</h2></html>");
		brauOuterPanel.add(brauTitelLabel, BorderLayout.NORTH);
		brauOuterPanel.add(getBrauPanel(), BorderLayout.CENTER);
		mainConentPanel.add(brauOuterPanel);
		
		
		return mainConentPanel;
	}

	private JPanel getRezeptPanel(){
		JPanel rezeptPanel = new JPanel(new FlowLayout());
		Vector<Rezept> rezeptVector = new Vector<Rezept>(selectedObject.rezeptBuch);
		rezeptComboBox = new JComboBox<Rezept>(rezeptVector);
		rezeptPanel.add(rezeptComboBox);
		deleteRezeptButton = new JButton("Entfernen");
		deleteRezeptButton.setActionCommand("BrauPanel:DeleteRezept");
		deleteRezeptButton.addActionListener(this);
		rezeptPanel.add(deleteRezeptButton);
		newRezepButton = new JButton("Hinzufügen...");
		newRezepButton.setActionCommand("BrauPanel:NewRezept");
		newRezepButton.addActionListener(this);
		rezeptPanel.add(newRezepButton);
		return rezeptPanel;
	}
	
	private JPanel getBrauPanel(){
		//Data Panel
		JPanel brauDataPanel = new JPanel();
		brauDataPanel.setLayout(new BoxLayout(brauDataPanel, BoxLayout.Y_AXIS));
		int verticalSpace = 100;
		
		JPanel laborTypPanel = new JPanel();
			laborTypPanel.setLayout(new BoxLayout(laborTypPanel, BoxLayout.X_AXIS));
			laborTypPanel.add(new JLabel("Labortyp: "));
//			Spacing.addHorizontalSpacer(laborTypPanel, 25);
			laborTypComboBox = new JComboBox<LaborTyp>(LaborTyp.getStandardWerte());
			laborTypPanel.add(laborTypComboBox);
		brauDataPanel.add(laborTypPanel);
		
//		Spacing.addVerticalSpacer(brauDataPanel, verticalSpace);
		
		JPanel laborQualitaetPanel = new JPanel();
			laborQualitaetPanel.setLayout(new BoxLayout(laborQualitaetPanel, BoxLayout.X_AXIS));
			laborQualitaetPanel.add(new JLabel("Laborqualität: "));
//			Spacing.addHorizontalSpacer(laborQualitaetPanel, 25);
			laborQualitaetComboBox = new JComboBox<Laborqualitaet>(Laborqualitaet.getStandardWerte());
			laborQualitaetPanel.add(laborQualitaetComboBox);
		brauDataPanel.add(laborQualitaetPanel);
		
//		Spacing.addVerticalSpacer(brauDataPanel, verticalSpace);
		
		JPanel meisterHandwerksPanel = new JPanel();
			meisterHandwerksPanel.setLayout(new BoxLayout(meisterHandwerksPanel, BoxLayout.X_AXIS));
			meisterHandwerksPanel.add(new JLabel("Erleichtertung durch Meisterhandwerk: "));
//			Spacing.addHorizontalSpacer(meisterHandwerksPanel, 25);
			meisterhandwerksSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
			meisterHandwerksPanel.add(meisterhandwerksSpinner);
		brauDataPanel.add(meisterHandwerksPanel);
		
//		Spacing.addVerticalSpacer(brauDataPanel, verticalSpace);
		
		JPanel sonstigeProbeErschwernisPanel = new JPanel();
		sonstigeProbeErschwernisPanel.setLayout(new BoxLayout(sonstigeProbeErschwernisPanel, BoxLayout.X_AXIS));
			sonstigeProbeErschwernisPanel.add(new JLabel("Sonstige Erschwerniss der Talentprobe: "));
//			Spacing.addHorizontalSpacer(sonstigeProbeErschwernisPanel, 25);
			sonstigeProbenerschwernissSpinner = new JSpinner(new SpinnerNumberModel(0, -99, 99, 1));
			sonstigeProbeErschwernisPanel.add(sonstigeProbenerschwernissSpinner);
		brauDataPanel.add(sonstigeProbeErschwernisPanel);
		
//		Spacing.addVerticalSpacer(brauDataPanel, verticalSpace);
		
		JPanel zurueckGehalteneTAPPanel = new JPanel();
		zurueckGehalteneTAPPanel.setLayout(new BoxLayout(zurueckGehalteneTAPPanel, BoxLayout.X_AXIS));
			zurueckGehalteneTAPPanel.add(new JLabel("Zurückgehaltene TAP: "));
//			Spacing.addHorizontalSpacer(zurueckGehalteneTAPPanel, 25);
			zuruckgehalteneTAPSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
			zurueckGehalteneTAPPanel.add(zuruckgehalteneTAPSpinner);
		brauDataPanel.add(zurueckGehalteneTAPPanel);
		
//		Spacing.addVerticalSpacer(brauDataPanel, verticalSpace);
		
		JPanel qualitaetsBonusASPPanel = new JPanel();
		qualitaetsBonusASPPanel.setLayout(new BoxLayout(qualitaetsBonusASPPanel, BoxLayout.X_AXIS));
			qualitaetsBonusASPPanel.add(new JLabel("Qualitätsbonus durch ASP: "));
//			Spacing.addHorizontalSpacer(qualitaetsBonusASPPanel, 25);
			qualitaetsBonusDurchASPSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9, 1));
			qualitaetsBonusASPPanel.add(qualitaetsBonusDurchASPSpinner);
		brauDataPanel.add(qualitaetsBonusASPPanel);
		
//		Spacing.addVerticalSpacer(brauDataPanel, verticalSpace);
		
		JPanel sonstigerQualitaetsBonusPanel = new JPanel();
		sonstigerQualitaetsBonusPanel.setLayout(new BoxLayout(sonstigerQualitaetsBonusPanel, BoxLayout.X_AXIS));
			sonstigerQualitaetsBonusPanel.add(new JLabel("Sonstiger Qulitätsbonus: "));
//			Spacing.addHorizontalSpacer(sonstigerQualitaetsBonusPanel, 25);
			sonstigerQualitaetsBonusSpinner = new JSpinner(new SpinnerNumberModel(0, -99, 99, 1));
			sonstigerQualitaetsBonusPanel.add(sonstigerQualitaetsBonusSpinner);
		brauDataPanel.add(sonstigerQualitaetsBonusPanel);
		
//		Spacing.addVerticalSpacer(brauDataPanel, verticalSpace);
		
		JPanel brauDatumPanel = new JPanel();
		brauDatumPanel.setLayout(new BorderLayout());
			brauDatumPanel.add(new JLabel("BrauDatum: "), BorderLayout.WEST);
			Spacing.addHorizontalSpacer(brauDatumPanel, 25);
			this.brauDatumsPanel = new AventurischesDatumPanel(this.parent.spielgruppenKonfiguration.aktuellesDatum);
			brauDatumPanel.add(this.brauDatumsPanel, BorderLayout.EAST);
		brauDataPanel.add(brauDatumPanel);
		
		Spacing.addVerticalSpacer(brauDataPanel, verticalSpace);
		
		//Button
		JButton brauButton = new JButton("Brauen");
		brauButton.setActionCommand("BrauPanel:Brew");
		brauButton.addActionListener(this);
		
		//Zusammenpacken
		JPanel brauPanel = new JPanel(new BorderLayout());
		brauPanel.add(brauDataPanel, BorderLayout.CENTER);
		brauPanel.add(brauButton, BorderLayout.SOUTH);
		return brauPanel;
	}
	
	/* *******
	 * Actions
	 * *******/

	/* *
	 * (non-Javadoc)
	 * @see eu.sffi.dsa4.gui.YADTAbstractNamedObjectEditor#specificActionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public boolean specificActionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("YADTBrauPanel:Update")){
			updateAlchemisten();
			this.remove(topPanel);
			topPanel = createTopPanel();
			this.add(topPanel, BorderLayout.NORTH);
			this.revalidate();
			return true;
		}
		else if(e.getActionCommand().equals("BrauPanel:Brew")){
			if (brauRezept()) setCurrentChanges(true);
			return true;
		}
		else if(e.getActionCommand().equals("BrauPanel:NewRezept")){
			if (addRezept()) setCurrentChanges(true);
			return true;
		}
		else if(e.getActionCommand().equals("BrauPanel:DeleteRezept")){
			if (deleteRezept()) setCurrentChanges(true);
			return true;
		}
		else return false;
	}
	
	private boolean addRezept(){
		// Die Menge der noch nicht hinzugefügten Talente für die Auswahlbox
		Vector<Rezept> addbareRezepte = new Vector<Rezept>(
				this.parent.spielgruppenKonfiguration.alchemieKonfiguration.rezepte.values());

		// Wirf alle Talente aus der Auswahlbox, für die der Held bereits
		// Talentwerte hat
		Vector<Rezept> vorhandeneRezepte = new Vector<Rezept>();
		for (Iterator<Rezept> it = addbareRezepte.iterator(); it.hasNext();) {
			Rezept rezeptToCheck = it.next();
			if (this.selectedObject.rezeptBuch.contains(rezeptToCheck)) {
				vorhandeneRezepte.add(rezeptToCheck);
			}
		}
		addbareRezepte.removeAll(vorhandeneRezepte);

		// Falls der Vector nun leer ist, zeig eine Fehlermeldung und brich ab
		if (addbareRezepte.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Der Alchemist hat bereits für alle Rezepte in seinem Rezeptbuch",
					"Kann nichts hinzufügen", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// In Array umwandeln für Auswahldialogbox
		Rezept[] finalAddbareRezepte = new Rezept[0];
		finalAddbareRezepte = addbareRezepte.toArray(finalAddbareRezepte);

		// Auswahldialog anzeigen
		Rezept gewaehltesRezept = (Rezept) JOptionPane.showInputDialog(this, // parentComponent,
				"Welches Rezept soll hinzugefügt werden?", // message,
				"Rezept wählen", // title,
				JOptionPane.PLAIN_MESSAGE, // messageType,
				null, // icon,
				finalAddbareRezepte, // selectionValues,
				finalAddbareRezepte[0] // initialSelectionValue
				);

		if (gewaehltesRezept == null)
			return false; // Die Auswahl wurde abgebrochen

		// Dem Alchemisten das Rezept hinzufügen
		this.selectedObject.rezeptBuch.add(gewaehltesRezept);

		// Auswahlbox updaten
		this.remove(mainPanel);
		this.mainPanel = createMainPanel();
		this.add(this.mainPanel, BorderLayout.CENTER);
		this.revalidate();

		// Auswahl auf das gerade erstellte Rezept setzen
		this.rezeptComboBox.setSelectedItem(gewaehltesRezept);

		// Alles hat geklappt, Talent wurde hinzugefügt return true
		return true;
	}
	
	/**
	 * Entfernt das aktuell ausgewählte Rezept aus dem Rezeptbuch
	 * @return true, falls das Rezept entfernt wurde, false sonst
	 */
	private boolean deleteRezept(){
		Rezept rezept = (Rezept) this.rezeptComboBox.getSelectedItem();
		if (rezept == null) return false;
		//Bestätigungsdialog
		if( JOptionPane.showConfirmDialog(this, "Rezept "+rezept+" wirklich entfernen?", "Bitte bestätigen", JOptionPane.OK_CANCEL_OPTION)
			!= JOptionPane.OK_OPTION) return false;
		//Sollte eigentlich nicht passieren, aber zum Zweck der Fehlerrobustheit...
		if (!this.selectedObject.rezeptBuch.contains(rezept)){
			JOptionPane.showMessageDialog(this, "Fehler beim Löschen von Rezept. Rezept "+rezept+" nicht in Rezeptbuch von "+this.selectedObject, "Fehler", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		this.selectedObject.rezeptBuch.remove(rezept);
		this.remove(mainPanel);
		this.mainPanel = createMainPanel();
		this.add(mainPanel, BorderLayout.CENTER);
		this.revalidate();
		return true;
	}
	
	private boolean brauRezept(){
		Rezept rezept = (Rezept) this.rezeptComboBox.getSelectedItem();
		
		try {
			Elixier elixier = rezept.brauen(
					this.selectedObject.talentWert,	//talentWert, 
					((LaborTyp)this.laborTypComboBox.getSelectedItem()).wert,	//labor, 
					((Laborqualitaet)this.laborQualitaetComboBox.getSelectedItem()).wert,	//laborQualitaet, 
					(Integer) this.meisterhandwerksSpinner.getValue(),	//erleichterungMeisterhandwerk, 
					(Integer) this.sonstigeProbenerschwernissSpinner.getValue(),	//sonstigeProbeErschwerniss, 
					(Integer) this.zuruckgehalteneTAPSpinner.getValue(),	//zurueckgehalteneTAP, 
					(Integer) this.qualitaetsBonusDurchASPSpinner.getValue(),	//qualitaetsBonusASP, 
					(Integer) this.sonstigerQualitaetsBonusSpinner.getValue(),	//sonstigerQualitaetsBonus, 
					this.brauDatumsPanel.readDatum(),	//brauDatum, 
					new Wuerfel()); //wuerfel
			
			this.selectedObject.talentWert.getHeld().getInventar().add(elixier);
			String message = this.selectedObject.talentWert.getHeld()+" hat gebraut: "+elixier.getName();
			message = message + "\nDas Elixier hat die Quallitätsstufe "+elixier.getBuchstabeQualitaet()
					+ "\n    und hält sich bis "+elixier.haltbarkeitsDatum.toShortString();
			
			//Wenn das Elixier Qualitätsstufe M hat, abfragen ob es sofortige Auswirkungen geben soll
			if (elixier.qualitaet == Elixier.QUALITAET_M) {
				message = message + "\nDas Elixier ist misslungen. Gegebenfalls sind sofortige Auswirkungen des Misslingens möglich."
						+ "\n\nSoll das Elixier "+this.selectedObject.talentWert.getHeld()+"s Inventar hinzugefügt werden?";
				int confirm = JOptionPane.showConfirmDialog(this, message, "Misslungenes Elixier", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) this.selectedObject.talentWert.getHeld().getInventar().add(elixier);
				return true;
			}
			//Ansonsten wird das neue Elixier direkt dem Inventar des Alchimisten hinzugefügt
			else{
				message = message +"\nDas Elixier wurde "+this.selectedObject.talentWert.getHeld()+"s Inventar hinzugefügt";
				this.selectedObject.talentWert.getHeld().getInventar().add(elixier);
				JOptionPane.showMessageDialog(this, message, "Elixier gelungen", JOptionPane.PLAIN_MESSAGE);
				return true;
			}
		} catch (AlchemieException ex) {
			JOptionPane.showMessageDialog(this, "Konnte Elixier nicht brauen.\n"+ex.getMessage(), "Fehler beim Brauen", JOptionPane.ERROR_MESSAGE);
			return false;
		} 	
	}
	
	private void updateAlchemisten(){
		for (Iterator<Held> it = this.parent.spielgruppenKonfiguration.heldenListe.values().iterator(); it.hasNext();){
			Held held = it.next();
			
			//Überprüfe ob Held das Talent Alchemie hat
			if (held.talentWerte.containsKey(this.parent.spielgruppenKonfiguration.talentListe.getObject("Alchemie"))){
				WuerfelTalentWert alchemieTalentWert = (WuerfelTalentWert) held.talentWerte.get(this.parent.spielgruppenKonfiguration.talentListe.getObject("Alchemie"));
				
				//Überprüfe ob ein Alchemist mit diesem Talentwert schon besteht
				//An dieser Stelle soll nicht einfach mittel "objectSet.containsName()" geprüft
				//Da der Name des Alchemisten den Talentwert enthält, der keinen eindeutigen Namen hat
				boolean bereitsVorhanden = false;
				for (Iterator<Alchemist> it2 = this.objectSet.values().iterator(); it2.hasNext();){
					Alchemist alchemist = it2.next();
					if (alchemist.talentWert.equals(alchemieTalentWert)){
						bereitsVorhanden = true;
						break;
					}
				}
				
				//Falls es ein neuer Alchemist ist, füge ihn dem Set hinzu
				if (!bereitsVorhanden) {
					this.objectSet.putObject(new Alchemist(alchemieTalentWert));
					setCurrentChanges(true);
				}
			}
			//Gleicher Spaß für Kochen (Tränke)
			if (held.talentWerte.containsKey(this.parent.spielgruppenKonfiguration.talentListe.getObject("Kochen"))
					&& held.talentWerte.get(this.parent.spielgruppenKonfiguration.talentListe.getObject("Kochen")).spezialisierungen.contains("Tränke")){
				WuerfelTalentWert alchemieTalentWert = (WuerfelTalentWert) held.talentWerte.get(this.parent.spielgruppenKonfiguration.talentListe.getObject("Kochen"));
				
				//Überprüfe ob ein Alchemist mit diesem Talentwert schon besteht
				//An dieser Stelle soll nicht einfach mittel "objectSet.containsName()" geprüft
				//Da der Name des Alchemisten den Talentwert enthält, der keinen eindeutigen Namen hat
				boolean bereitsVorhanden = false;
				for (Iterator<Alchemist> it2 = this.objectSet.values().iterator(); it2.hasNext();){
					Alchemist alchemist = it2.next();
					if (alchemist.talentWert.equals(alchemieTalentWert)){
						bereitsVorhanden = true;
						break;
					}
				}
				
				//Falls es ein neuer Alchemist ist, füge ihn dem Set hinzu
				if (!bereitsVorhanden) {
					this.objectSet.putObject(new Alchemist(alchemieTalentWert));
					setCurrentChanges(true);
				}
			}
		}
		
		//Update das selected Object auf das erste
		if (!this.objectSet.isEmpty()){
			this.selectedObject = this.objectSet.values().iterator().next();
			this.objektComboBox.setSelectedItem(selectedObject);
		}
	}
	
	
}
