/**
 * 
 */
package eu.sffi.dsa4.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;

/**
 * @author Andi Popp
 *
 */
public class YADTNavPanel extends JPanel implements TreeSelectionListener{

	private JTree navigation;
	private YADTMainContentPane parent;
	
	public YADTNavPanel(YADTMainContentPane parent) {
		
		//Globale Variablen füllen
		this.parent = parent;
		
		//Größe modifizieren
		this.setPreferredSize(new Dimension(250, 0));
		
		//Navigationsbaum erstellen
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		
		DefaultMutableTreeNode heldenRoot = new DefaultMutableTreeNode("Heldenmanager");
		root.add(heldenRoot);
	
		DefaultMutableTreeNode alchemieRoot = new DefaultMutableTreeNode("Alchemielabor");
		root.add(alchemieRoot);
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
	
		Object selected = e.getNewLeadSelectionPath().getLastPathComponent();
		
		if (selected instanceof DefaultMutableTreeNode){
			Object selectedContent = ((DefaultMutableTreeNode)selected).getUserObject();
			if (selectedContent instanceof JPanel){
				this.parent.updateMainPanel((JPanel)selectedContent);
			}
		}
	}
}
