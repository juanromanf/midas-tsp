package com.midas.tsp.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.controller.impl.TeamController;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.PropertiesTSP;
import com.midas.tsp.model.TeamMember;
import com.midas.tsp.view.AbstractViewPanel;

@LogTs({ @LogT(cycle = 3, date = "28/03/2011", id = "17", time = 120, who = "JCRF") })
@SuppressWarnings("serial")
public class TeamPanel extends AbstractViewPanel {
	//private JTextField txtId;
	//private JTextField txtName;
	private TeamController controller;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TeamPanel() {
		try {
			initialize();
		} catch (TSPException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		/*JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.3);
		splitPane.setMinimumSize(new Dimension(300, 25));
		add(splitPane);

		JTree treeMembers = new JTree();
		treeMembers.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(
				"Members") {
			{
			}
		}));
		splitPane.setLeftComponent(treeMembers);

		JPanel panelEditMember = new JPanel();
		splitPane.setRightComponent(panelEditMember);
		panelEditMember
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblId = new JLabel("ID:");
		panelEditMember.add(lblId, "4, 4, right, default");

		txtId = new JTextField();
		panelEditMember.add(txtId, "6, 4, fill, default");
		txtId.setColumns(10);

		JLabel lblNames = new JLabel("Names:");
		panelEditMember.add(lblNames, "4, 6, right, default");

		txtName = new JTextField();
		panelEditMember.add(txtName, "6, 6, fill, default");
		txtName.setColumns(10);

		JLabel lblRole = new JLabel("Role:");
		panelEditMember.add(lblRole, "4, 8, right, default");

		JComboBox cmbRole = new JComboBox();
		panelEditMember.add(cmbRole, "6, 8, fill, default");
		*/		
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws TSPException 
	 */
	private void initialize() throws TSPException {
		controller = new TeamController();
		setLayout(new BorderLayout(0, 0));
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(TeamPanel.class
				.getResource("/com/midas/tsp/gui/resources/add.png")));
		toolBar.add(btnAdd);

		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(TeamPanel.class
				.getResource("/com/midas/tsp/gui/resources/remove.png")));
		toolBar.add(btnDelete);

		JButton btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon(TeamPanel.class
				.getResource("/com/midas/tsp/gui/resources/disc.png")));
		toolBar.add(btnSave);
		table = new JTable();
		table.setModel(new DefectsTableModel());
		table.getSelectionModel().addListSelectionListener(new RowListener());
		setUpRoleColumn(table, table.getColumnModel().getColumn(2));
		add(table);
	}

	private void setUpRoleColumn(JTable table, TableColumn roleColumn) throws TSPException {
		JComboBox comboBox = new JComboBox();
		List<PropertiesTSP> roles = controller.findRoles();
		for (PropertiesTSP rol : roles) {
			comboBox.addItem(rol);
		}
		roleColumn.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		roleColumn.setCellRenderer(renderer);
	}

	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
		}
	}
	
    private class DefectsTableModel extends AbstractTableModel {
    	private static final long serialVersionUID = 2202491816861255310L;
    	private String[] columnNames = {"Id.", "Nombre", "Rol"};
    	private Object[][] data; 
    	
    	public DefectsTableModel() throws TSPException {
    		List<TeamMember> teamMembers = controller.getTeamMembers();
    		data = new Object[teamMembers.size()][3];
    		for (int i = 0; i < teamMembers.size(); i++) {
    			TeamMember teamMember = teamMembers.get(i);
    			data[i][0] = teamMember.getId();
    			data[i][1] = teamMember.getDescription();
    			data[i][2] = controller.findRol(teamMember.getRol());    			
    		}
    	}

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @SuppressWarnings("unchecked")
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
        	return true;            
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

    }

	@Override
	public void reFill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearForm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> validateForm() {
		// TODO Auto-generated method stub
		return null;
	}

}
