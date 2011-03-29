package com.midas.tsp.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import javax.swing.UIManager;

/**
 * Application GUI entry point Class.
 * 
 * @author Juan Carlos Román
 * @date 14/03/2011
 *
 */
@LogTs({ 
	@LogT(cycle = 1, date = "14/03/2011", id = "1_46", time = 60, who = "JCRF")	 
})
public class TSPApplication {

	private JFrame frmTspMidas;
	private JTable tableTeam;
	private JTextField txtSource;
	private JTextField txtProperties;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TSPApplication window = new TSPApplication();
					window.frmTspMidas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TSPApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTspMidas = new JFrame();
		frmTspMidas.setTitle("TSP");
		frmTspMidas.setBounds(100, 100, 725, 497);
		frmTspMidas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmTspMidas.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic('F');
		menuBar.add(menuFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		menuFile.add(mntmExit);
		
		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About...");
		menuHelp.add(mntmAbout);
		frmTspMidas.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmTspMidas.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 2, 4, 0));
		
		JPanel panelProject = new JPanel();
		panel.add(panelProject);
		panelProject.setBorder(new TitledBorder(null, "Project", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProject.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblSources = new JLabel("Sources:");
		panelProject.add(lblSources, "2, 2, left, default");
		
		txtSource = new JTextField();
		panelProject.add(txtSource, "2, 4, 3, 1, fill, default");
		txtSource.setColumns(10);
		
		JButton btnSource = new JButton("");
		btnSource.setIcon(new ImageIcon(TSPApplication.class.getResource("/com/midas/tsp/gui/resources/folder.png")));
		panelProject.add(btnSource, "6, 4");
		
		JLabel lblProperties = new JLabel("Properties Path:");
		panelProject.add(lblProperties, "2, 6, left, default");
		
		txtProperties = new JTextField();
		panelProject.add(txtProperties, "2, 8, 3, 1, fill, default");
		txtProperties.setColumns(10);
		
		JButton btnProperties = new JButton("");
		btnProperties.setIcon(new ImageIcon(TSPApplication.class.getResource("/com/midas/tsp/gui/resources/folder.png")));
		panelProject.add(btnProperties, "6, 8");
		
		JButton btnAnalize = new JButton("Analize");
		btnAnalize.setIcon(new ImageIcon(TSPApplication.class.getResource("/com/midas/tsp/gui/resources/report.png")));
		panelProject.add(btnAnalize, "4, 12");
		
		JPanel panelTeam = new JPanel();
		panel.add(panelTeam);
		panelTeam.setBorder(new TitledBorder(null, "Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTeam.setLayout(new BorderLayout(4, 0));
		
		tableTeam = new JTable();
		tableTeam.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Names", "Role"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableTeam.getColumnModel().getColumn(0).setPreferredWidth(141);
		tableTeam.getColumnModel().getColumn(1).setPreferredWidth(145);
		panelTeam.add(tableTeam);
		
		
		// TabPanels
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTspMidas.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel tabTeam = new JPanel();
		tabbedPane.addTab("Team", new ImageIcon(TSPApplication.class.getResource("/com/midas/tsp/gui/resources/people.png")), tabTeam, null);
		tabTeam.setLayout(new BorderLayout(0, 0));
		TeamPanel teamPanel = new TeamPanel();
		tabTeam.add(teamPanel);
		
		JPanel tabTask = new JPanel();
		tabbedPane.addTab("Tasks", new ImageIcon(TSPApplication.class.getResource("/com/midas/tsp/gui/resources/task.png")), tabTask, null);
		tabTask.setLayout(new BorderLayout(0, 0));
		JPanel tasksPanel = new TasksPanel();
		tabTask.add(tasksPanel);
		
		JPanel tabDefects = new JPanel();
		tabbedPane.addTab("Defects", new ImageIcon(TSPApplication.class.getResource("/com/midas/tsp/gui/resources/project-open.png")), tabDefects, null);
		tabDefects.setLayout(new BorderLayout(0, 0));
		//DefectsPanel defectsPanel = new DefectsPanel();
		//tabDefects.add(defectsPanel);
		
	}
	
	/**
	 * Exit from application.
	 */
	public void exit() {
		
		frmTspMidas.dispose();
		System.exit(0);
	}
}
