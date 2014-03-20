package eu.sffi.dsa4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eu.sffi.dsa4.SpielgruppenKonfiguration;
import eu.sffi.dsa4.gui.elements.Icons;

public class YADTMainFrame extends JFrame implements ActionListener, ChecksCurrentChanges{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8490270447332265755L;
	
	
	
	private JPanel contentPane;
	protected YADTMainContentPane mainContentPane;	
	private YADTMainMenu menuBar;

	/**
	 * Der Name des Files in dem die Spielgruppenkonfiguration gespeichert ist
	 */
	File saveFile;
	
	/**
	 * Eine Flage die anzeigt ob es ungespeicherte Änderungen in der Spielgruppenkofiguration
	 * gibt
	 */
	boolean unsavedChangesFlag;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YADTMainFrame frame = new YADTMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public YADTMainFrame() {
		super();
		this.setIconImage(Icons.SWORD_ICON.getImage());
		updateFrameTitle();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(800, 600));
		
		//Create the content pane
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		//Create the menu bar
		this.menuBar = new YADTMainMenu(this);
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		
		this.setLocationByPlatform(true);
		this.pack();
        this.setVisible(true);
	}

	protected void start(SpielgruppenKonfiguration spielgruppenKonfiguration){
		if (this.mainContentPane != null) this.remove(this.mainContentPane);
		this.mainContentPane = new YADTMainContentPane(spielgruppenKonfiguration, this);
		contentPane.add(mainContentPane, BorderLayout.CENTER);
		
		this.setCurrentChanges(false);
		this.updateFrameTitle();
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());		
	}
	
	public void updateFrameTitle(){
		String title = "YADT";
		if (this.saveFile != null) title = title + " - "+this.saveFile.getAbsolutePath();
		if (this.unsavedChangesFlag) title = title + " *";
		this.setTitle(title);
	}

	@Override
	public void setCurrentChanges(boolean changeFlag) {
		this.unsavedChangesFlag = changeFlag;	
		this.updateFrameTitle();
	}

}
