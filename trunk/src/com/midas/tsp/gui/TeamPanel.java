package com.midas.tsp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.controller.impl.TeamController;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.PropertiesTSP;
import com.midas.tsp.model.TeamMember;
import com.midas.tsp.view.AbstractViewPanel;

/**
 * @author Carlos Ivan Duarte Cubides
 * @date 29/03/2011
 *
 */
@LogTs({ @LogT(cycle = 3, date = "28/03/2011", id = "17", time = 120, who = "JCRF"),
		 @LogT(cycle = 3, date = "29/03/2011", id = "17", time = 60, who = "CIDC"),
		 @LogT(cycle = 3, date = "30/03/2011", id = "17", time = 60, who = "CIDC")})
@SuppressWarnings("serial")
public class TeamPanel extends AbstractViewPanel implements TableModelListener {
	private TeamController controller;
	private JTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnSave;
	private final Action addAction = new AddAction();
	private final Action deleteAction = new DeleteAction();
	private final Action saveAction = new SaveAction();
	private TeamTableModel teamTableModel;
	private int indexTeamMemberSelected;

	@LocControl(@Loc(cycle=3, size=5, type=LocControl.LocType.NEW, who="CIDC"))
	public TeamPanel() {
		try {
			initialize();
		} catch (TSPException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}		
	}
	
	@Override
	public void reFill() {	
		
	}

	@Override
	@LocControl(@Loc(cycle=3, size=5, type=LocControl.LocType.NEW, who="CIDC"))
	public void save() {
		try {
			controller.save();
		} catch (TSPException ex) {
			JOptionPane.showMessageDialog(TeamPanel.this, ex.getMessage());
		}
	}

	@Override
	@LocControl(@Loc(cycle=3, size=2, type=LocControl.LocType.NEW, who="CIDC"))
	public void add() {
		controller.getTeamMembers().add(new TeamMember());
		teamTableModel.fireTableDataChanged();		
	}

	@Override
	@LocControl(@Loc(cycle=3, size=6, type=LocControl.LocType.NEW, who="CIDC"))
	public void delete() {
		int option = JOptionPane.showConfirmDialog(this, "Esta seguro que desea eliminar el integrante?");
		if (option == JOptionPane.YES_OPTION) {
			controller.getTeamMembers().remove(indexTeamMemberSelected);
			teamTableModel.fireTableDataChanged();
			table.repaint();	
		}
	}

	@Override
	public void clearForm() {
		
	}

	@Override
	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
	public List<String> validateForm() {
		return null;
	}

	@Override
	@LocControl(@Loc(cycle=3, size=24, type=LocControl.LocType.NEW, who="CIDC"))
	public void tableChanged(TableModelEvent e) {
		try {
			int row = e.getFirstRow();
	        int column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        Object data = model.getValueAt(row, column);
	        TeamMember teamMember = controller.getTeamMembers().get(row);
	        switch (column) {
			case 0:
				teamMember.setId(data.toString());
				break;
			case 1:
				teamMember.setDescription(data.toString());
				break;
			case 2:
				PropertiesTSP rol = controller.findRolByDescription(data.toString());
				teamMember.setRol(Integer.parseInt(rol.getId()));
				break;
			default:
				break;
			}
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(TeamPanel.this, ex);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws TSPException 
	 */
	@LocControl(@Loc(cycle=3, size=26, type=LocControl.LocType.NEW, who="CIDC"))
	private void initialize() throws TSPException {
		controller = new TeamController();
		setLayout(new BorderLayout(0, 0));
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		btnAdd = new JButton("");
		btnAdd.setAction(addAction);
		btnAdd.setIcon(new ImageIcon(TeamPanel.class
				.getResource("/com/midas/tsp/gui/resources/add.png")));
		toolBar.add(btnAdd);
		btnDelete = new JButton("");
		btnDelete.setAction(deleteAction);
		btnDelete.setIcon(new ImageIcon(TeamPanel.class
				.getResource("/com/midas/tsp/gui/resources/remove.png")));
		toolBar.add(btnDelete);
		btnSave = new JButton("");
		btnSave.setAction(saveAction);
		btnSave.setIcon(new ImageIcon(TeamPanel.class
				.getResource("/com/midas/tsp/gui/resources/disc.png")));
		toolBar.add(btnSave);
		table = new JTable();
		teamTableModel = new TeamTableModel();
		teamTableModel.addTableModelListener(this);
		table.setModel(teamTableModel);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		setUpRoleColumn(table, table.getColumnModel().getColumn(2));
		add(table);
	}

	@LocControl(@Loc(cycle=3, size=8, type=LocControl.LocType.NEW, who="CIDC"))
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

	/**
	 * @author Carlos Ivan Duarte Cubides
	 * @date 29/03/2011
	 *
	 */
	private class RowListener implements ListSelectionListener {
		@LocControl(@Loc(cycle=3, size=3, type=LocControl.LocType.NEW, who="CIDC"))
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				indexTeamMemberSelected = table.getSelectionModel().getLeadSelectionIndex();
			}
		}
	}
	
    /**
     * @author Carlos Ivan Duarte Cubides
     * @date 29/03/2011
     *
     */
    private class TeamTableModel extends AbstractTableModel {
    	private static final long serialVersionUID = 2202491816861255310L;
    	private String[] columnNames = {"Id.", "Nombre", "Rol"};
    	private Object[][] data; 
    	
    	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
    	public TeamTableModel() throws TSPException {
    		loadModel();
    	}
    	
    	@LocControl(@Loc(cycle=3, size=10, type=LocControl.LocType.NEW, who="CIDC"))
    	private void loadModel() throws TSPException {
    		List<TeamMember> teamMembers = controller.getTeamMembers();
    		data = new Object[teamMembers.size()][3];
    		for (int i = 0; i < teamMembers.size(); i++) {
    			TeamMember teamMember = teamMembers.get(i);
    			data[i][0] = teamMember.getId();
    			data[i][1] = teamMember.getDescription();
    			if (teamMember.getRol() != null) {
    				data[i][2] = controller.findRolById(teamMember.getRol());
    			}
    		}
    	}

    	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
        public int getColumnCount() {
            return columnNames.length;
        }

    	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
        public int getRowCount() {
            return data.length;
        }

    	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
        public String getColumnName(int col) {
            return columnNames[col];
        }

    	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        @LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
        public boolean isCellEditable(int row, int col) {
            return true;            
        }

        @LocControl(@Loc(cycle=3, size=2, type=LocControl.LocType.NEW, who="CIDC"))
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
       
        @Override
        @LocControl(@Loc(cycle=3, size=5, type=LocControl.LocType.NEW, who="CIDC"))
        public void fireTableDataChanged() {
        	try {
				loadModel();
			} catch (TSPException e) {
				JOptionPane.showMessageDialog(TeamPanel.this, e.getMessage());
			}
        }
    }
    
    /**
     * @author Carlos Ivan Duarte Cubides
     * @date 29/03/2011
     *
     */
    private class AddAction extends AbstractAction {
    	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
		public void actionPerformed(ActionEvent e) {
			add();
		}
	}
    
    /**
     * @author Carlos Ivan Duarte Cubides
     * @date 29/03/2011
     *
     */
    private class DeleteAction extends AbstractAction {
    	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
		public void actionPerformed(ActionEvent e) {
			delete();
		}
	}
    
    /**
     * @author Carlos Ivan Duarte Cubides
     * @date 29/03/2011
     *
     */
    private class SaveAction extends AbstractAction {
    	@LocControl(@Loc(cycle=3, size=1, type=LocControl.LocType.NEW, who="CIDC"))
		public void actionPerformed(ActionEvent e) {
			save();
		}
	}
}
