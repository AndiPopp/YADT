/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;

import eu.sffi.dsa4.gui.panels.YADTAbstractToolPanel;
import eu.sffi.dsa4.gui.panels.YADTBrauPanel;
import eu.sffi.dsa4.gui.panels.YADTElixierArtEditor;
import eu.sffi.dsa4.gui.panels.YADTHeldenEditor;
import eu.sffi.dsa4.gui.panels.YADTInventarPanel;

/**
 * @author Andi Popp
 *
 */
public class YADTNavPanel extends JPanel implements TreeSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7225007895274878045L;
	private JTree navigation;
	private YADTMainContentPane parent;
	
	public YADTInventarPanel inventarPanel;
	
	public YADTNavPanel(YADTMainContentPane parent) {
		
		//Globale Variablen füllen
		this.parent = parent;
		
		//Größe modifizieren
		this.setPreferredSize(new Dimension(230, 0));
		
		//Navigationsbaum erstellen
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		
		DefaultMutableTreeNode heldenRoot = new DefaultMutableTreeNode("Heldengruppe");
		root.add(heldenRoot);
			DefaultMutableTreeNode helden = new DefaultMutableTreeNode(new YADTHeldenEditor(this.parent.spielgruppenKonfiguration, this.parent));
			heldenRoot.add(helden);
			this.inventarPanel = new YADTInventarPanel(this.parent);
			DefaultMutableTreeNode inventare = new DefaultMutableTreeNode(inventarPanel);
			heldenRoot.add(inventare);
	
		DefaultMutableTreeNode alchemieRoot = new DefaultMutableTreeNode("Alchemielabor");
		root.add(alchemieRoot);
			DefaultMutableTreeNode brauPanel = new DefaultMutableTreeNode(new YADTBrauPanel(this.parent.spielgruppenKonfiguration.alchemieKonfiguration.alchemisten, this.parent));
			alchemieRoot.add(brauPanel);
			DefaultMutableTreeNode rezeptDatenbank = new DefaultMutableTreeNode("Rezeptdatenbank");
			alchemieRoot.add(rezeptDatenbank);
				DefaultMutableTreeNode elixierArtEditorNode = new DefaultMutableTreeNode(new YADTElixierArtEditor(this.parent.spielgruppenKonfiguration.alchemieKonfiguration, this.parent));
				rezeptDatenbank.add(elixierArtEditorNode);
			
		
		
		//Tree erstellen und konfigurieren
		navigation = new JTree(root);
		navigation.setRootVisible(false);
		DefaultTreeSelectionModel treeSelectionModel = new DefaultTreeSelectionModel();
		treeSelectionModel.setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
		navigation.setSelectionModel(treeSelectionModel);
		navigation.addTreeSelectionListener(this);
		
		navigation.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JScrollPane(navigation));
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
	
		Object selected = null;
		if (e.getNewLeadSelectionPath() != null) selected = e.getNewLeadSelectionPath().getLastPathComponent();
		if (selected instanceof DefaultMutableTreeNode){
			Object selectedContent = ((DefaultMutableTreeNode)selected).getUserObject();
			if (selectedContent instanceof YADTAbstractToolPanel){
				this.parent.updateMainPanel((JPanel)selectedContent);
			}
		}
	}
}
