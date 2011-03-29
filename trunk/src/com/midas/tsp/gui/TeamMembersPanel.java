package com.midas.tsp.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.midas.tsp.controller.impl.TeamMembersController;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.PropertiesTSP;
import com.midas.tsp.model.TeamMember;
import com.midas.tsp.view.AbstractViewPanel;

public class TeamMembersPanel extends AbstractViewPanel {

	private static final long serialVersionUID = 6781110420151476167L;
	private JFrame frame;
	private JTable table;
	private TeamMembersController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamMembersPanel window = new TeamMembersPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TeamMembersPanel() {
		try {
			initialize();
		} catch (TSPException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws TSPException 
	 */
	private void initialize() throws TSPException {
		controller = new TeamMembersController();
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
		frame.getContentPane().add(table, BorderLayout.NORTH);
		table.setModel(new DefectsTableModel());
		table.getSelectionModel().addListSelectionListener(new RowListener());
		setUpRoleColumn(table, table.getColumnModel().getColumn(2));
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
