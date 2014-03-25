/**
 * 
 */
package eu.sffi.dsa4.gui.elements;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eu.sffi.dsa4.kalender.AventurischerMonat;
import eu.sffi.dsa4.kalender.AventurischesDatum;

/**
 * @author Andi Popp
 *
 */
public class AventurischesDatumPanel extends JPanel implements ActionListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8588994143778738936L;
	public JSpinner tagSpinner;
	public JComboBox<AventurischerMonat> monatComboBox;
	public JSpinner jahrSpinner;
	
	public AventurischesDatumPanel(AventurischesDatum datum) {
		tagSpinner = new JSpinner(new SpinnerNumberModel(datum.getNumerischenTag(), 1, 30, 1));
		tagSpinner.addChangeListener(this);
		monatComboBox = new JComboBox<AventurischerMonat>(AventurischerMonat.getMonate());
		monatComboBox.setSelectedItem(datum.getMonat());
		monatComboBox.addActionListener(this);
		jahrSpinner = new JSpinner(new SpinnerNumberModel(datum.getNumerischesJahr(), -9999, 9999, 1));
		jahrSpinner.addChangeListener(this);
		
		//Layout and adding
		this.setLayout(new FlowLayout());
		this.add(tagSpinner);
		this.add(monatComboBox);
		this.add(jahrSpinner);
		this.add(new JLabel("BF"));
	}
	
	public AventurischesDatum readDatum(){
		int jahr = (Integer)jahrSpinner.getValue();
		AventurischerMonat monat = (AventurischerMonat)monatComboBox.getSelectedItem();
		int tag = (Integer)tagSpinner.getValue();
		return new AventurischesDatum(jahr, monat, tag);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(monatComboBox)){
			if ( (((AventurischerMonat)monatComboBox.getSelectedItem()).wert == 13) && 
					((Integer)tagSpinner.getValue()).intValue() > 5 ){
				tagSpinner.setValue(new Integer(5));
			}
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (arg0.getSource().equals(tagSpinner)){
			if ( (((AventurischerMonat)monatComboBox.getSelectedItem()).wert == 13) && 
					((Integer)tagSpinner.getValue()).intValue() > 5 ){
				tagSpinner.setValue(new Integer(5));
			}
		}
		
	}
	
	
}
