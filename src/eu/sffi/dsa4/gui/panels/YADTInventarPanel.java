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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eu.sffi.dsa4.SpielgruppenKonfiguration;
import eu.sffi.dsa4.gui.ScrollCheck;
import eu.sffi.dsa4.gui.YADTMainContentPane;
import eu.sffi.dsa4.gui.elements.Icons;
import eu.sffi.dsa4.gui.elements.ItemButton;
import eu.sffi.dsa4.gui.elements.ItemInventarPanel;
import eu.sffi.dsa4.held.Held;
import eu.sffi.dsa4.items.HatInventar;
import eu.sffi.dsa4.items.Item;
import eu.sffi.dsa4.util.VerboseOut;

/**
 * @author Andi Popp
 *
 */
public class YADTInventarPanel extends YADTAbstractToolPanel implements ActionListener, ScrollCheck {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1305903505246896558L;

	/**
	 * Beinhaltet die Helden und den Itempool
	 */
	private Vector<HatInventar> inventare;
	
	private HatInventar selectedObject;
	
	//Swing-Objekte
	
	JScrollPane mainScrollPane;
	
	JPanel bottomPanel;
	
	private JComboBox<HatInventar> inventarComboBox;
	
	//Konstruktoren und verwandte Methoden;
	
	public YADTInventarPanel(YADTMainContentPane parent){
		super(parent);
		SpielgruppenKonfiguration spielgruppenKonfiguration = this.parent.spielgruppenKonfiguration;
		
		//Set Layout
		this.setLayout(new BorderLayout());
			
		//Top Panel
		updateTopPanel();
		
		//Main Panel
		if (this.selectedObject != null) updateMainPanel();
		
		//Bottom Panel
		bottomPanel = createBottomPanel();
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		this.inventarComboBox.setSelectedItem(spielgruppenKonfiguration.itemPool);
	}
	
	/* (non-Javadoc)
	 * @see eu.sffi.dsa4.gui.YADTAbstractToolPanel#createTopPanel()
	 */
	@Override
	protected JPanel createTopPanel() {
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("Inventar auswählen: "));
		inventarComboBox = new JComboBox<HatInventar>(this.inventare);
		inventarComboBox.addActionListener(this);
		topPanel.add(inventarComboBox);
		JButton refreshButton = new JButton(Icons.REFRESH_ICON_SMALL);
		refreshButton.setActionCommand("InventarPanel:Refresh");
		refreshButton.addActionListener(this);
		topPanel.add(refreshButton);
		return topPanel;
	}

	/* (non-Javadoc)
	 * @see eu.sffi.dsa4.gui.YADTAbstractToolPanel#createMainPanel()
	 */
	@Override
	protected JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new GridLayout(0, 2));
		Vector<Item> inventar = new Vector<Item>(selectedObject.getInventar());
		
		int halfSize = (int) Math.ceil(inventar.size()/2.0);
				
		for (int i = 0; i < halfSize; i++){
			mainPanel.add(new ItemInventarPanel(inventar.get(i), this));
			if (halfSize+i<inventar.size()) mainPanel.add(new ItemInventarPanel(inventar.get(halfSize+i),this));
		}
		
		//Packen in ein Panel mit passendem Layout
		JPanel mainPanelWrapper = new JPanel();
		mainPanelWrapper.add(mainPanel);
		return mainPanelWrapper;
	}

	private JPanel createBottomPanel(){
		JPanel bottomPanel = new JPanel(new FlowLayout());
		
		JButton addItemButton = new JButton("Füge neuen Gegenstand hinzu vom Typ: ");
		bottomPanel.add(addItemButton);
		
		return bottomPanel;
	}
	
	public void updateTopPanel(){
		SpielgruppenKonfiguration spielgruppenKonfiguration = this.parent.spielgruppenKonfiguration;

		this.inventare = new Vector<HatInventar>();
		this.inventare.add(spielgruppenKonfiguration.itemPool);
		for(Iterator<Held> it = spielgruppenKonfiguration.heldenListe.values().iterator();it.hasNext();){
			this.inventare.add(it.next());
		}
		
		if (this.topPanel != null) this.remove(topPanel);
		this.topPanel = createTopPanel();
		this.add(topPanel, BorderLayout.NORTH);
		this.selectedObject = spielgruppenKonfiguration.itemPool;
		this.revalidate();
	}
	
	//Actions
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(inventarComboBox)){
			this.selectedObject = (HatInventar) inventarComboBox.getSelectedItem();
			updateMainPanel();
		}
		else if (e.getSource() instanceof ItemButton){
			ItemButton itemButton = (ItemButton) e.getSource();
			itemButton.itemContextMenu.show(itemButton, 0, 0);
		}
		else if (e.getActionCommand().equals("InventarPanel:Refresh")){
			updateMainPanel();
		}
		else{
			VerboseOut.CONSOLE.println("Noch nicht implementiert: "+e.getActionCommand());
		}
	}
	
	private void updateMainPanel(){
		if (this.mainScrollPane != null) this.remove(mainScrollPane);
		mainScrollPane = new JScrollPane(createMainPanel());
		this.add(mainScrollPane, BorderLayout.CENTER);
		revalidate();
	}
	
	
	//Overrides
	
	@Override
	public String toString(){
		return "Inventare";
	}

	@Override
	public boolean putInScrollPane() {
		return false;
	}

}
