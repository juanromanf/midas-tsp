package com.midas.tsp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;

@LogTs({ @LogT(cycle = 3, date = "28/03/2011", id = "17", time = 120, who = "JCRF") })
@SuppressWarnings("serial")
public class TeamPanel extends JPanel {
	private JTextField txtId;
	private JTextField txtName;

	/**
	 * Create the panel.
	 */
	public TeamPanel() {
		setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
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

	}

}
