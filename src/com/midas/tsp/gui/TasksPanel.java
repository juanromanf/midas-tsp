package com.midas.tsp.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.apache.commons.lang.StringUtils;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.midas.tsp.Constants;
import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.controller.impl.TaskController;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.PropertiesTSP;
import com.midas.tsp.model.Task;
import com.midas.tsp.view.AbstractViewPanel;

/**
 * Contains the task manager panel
 * @author Carlos Ivan Duarte C.
 * @date 22/03/2011
 *
 */
@LogTs({@LogT(cycle=2, date="22/03/2011", id="11", time=72, who="CIDC"),
		@LogT(cycle=3, date="27/03/2011", id="28", time=240, who="CIDC"),
		@LogT(cycle=3, date="28/03/2011", id="28", time=150, who="CIDC")})
public class TasksPanel extends AbstractViewPanel implements TreeSelectionListener{

	private static final long serialVersionUID = 4336026812403883469L;
	private TaskController controller;
	private JFrame frame;
	private JTextField nameTextField;
	private JTextField idTextField;
	private JTextField durationTextField;
	private JTextField sizeTextField;
	private JTree taskTree;
	private JButton addButton;
	private JButton deleteButton;
	private JPanel taskPanel;
	private Task taskSelected;
	private Integer cycleSelected;
	private final Action acceptAction = new SaveAction();
	private final Action addAction = new AddAction();
	private final Action deleteAction = new DeleteAction();
	private DefaultMutableTreeNode nodeSelected; 
	private DefaultTreeModel treeModel;
	//private DefaultMutableTreeNode rootNode;
    private static final String ROOT_NODE = "Tareas";
	private static final String CYCLE_NODE = "Ciclo ";
	
	/**
	 * Initializes the window
	 * @param args
	 */
	@LocControl(value = {
			@Loc(size=10, type=LocControl.LocType.REUSED, who="CIDC", cycle=2)
	})
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TasksPanel window = new TasksPanel();
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
	@LocControl(value = {
			@Loc(size=5, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public TasksPanel() {
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
	@LocControl(value = {
			@Loc(size=122, type=LocControl.LocType.REUSED, who="CIDC", cycle=2)
	})
	private void initialize() throws TSPException {
		controller = new TaskController();
		frame = new JFrame();
		frame.setBounds(100, 100, 722, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		JPanel mainPanel = new JPanel();
		
		taskPanel = new JPanel();
		//GroupLayout groupLayout = new GroupLayout(this);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(taskPanel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(taskPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(mainPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		taskPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel nameLabel = new JLabel("Nombre Tarea");
		taskPanel.add(nameLabel, "2, 6, right, default");
		
		nameTextField = new JTextField();
		taskPanel.add(nameTextField, "4, 6, 21, 1, fill, default");
		nameTextField.setColumns(10);
		
		JLabel idLabel = new JLabel("ID Tarea");
		taskPanel.add(idLabel, "2, 8, right, default");
		
		idTextField = new JTextField();
		taskPanel.add(idTextField, "4, 8, 15, 1, fill, default");
		idTextField.setColumns(10);
		
		JLabel durationLabel = new JLabel("Duraci\u00F3n Estimada");
		taskPanel.add(durationLabel, "2, 10");
		
		durationTextField = new JTextField();
		taskPanel.add(durationTextField, "4, 10, 15, 1, fill, default");
		durationTextField.setColumns(10);
		
		JLabel sizeLabel = new JLabel("Tama\u00F1o Estimado");
		taskPanel.add(sizeLabel, "2, 12, right, default");
		
		sizeTextField = new JTextField();
		taskPanel.add(sizeTextField, "4, 12, 15, 1, fill, default");
		sizeTextField.setColumns(10);
		
		JButton saveButton = new JButton("Guardar");
		saveButton.setAction(acceptAction);
		taskPanel.add(saveButton, "24, 16");
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
		taskTree = new JTree();
		treeModel = new DefaultTreeModel(new TaskTreeModel());
		taskTree.setModel(treeModel);
		taskTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		taskTree.addTreeSelectionListener(this);
		taskTree.setEditable(true);
		taskTree.setShowsRootHandles(true);
		scrollPane.setViewportView(taskTree);
		
		JPanel buttonsPanel = new JPanel();
		mainPanel.add(buttonsPanel, BorderLayout.NORTH);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		addButton = new JButton("+");
		addButton.setAction(addAction);
		buttonsPanel.add(addButton);
		
		deleteButton = new JButton("-");
		deleteButton.setAction(deleteAction);
		buttonsPanel.add(deleteButton);
		//this.setLayout(groupLayout);
		frame.getContentPane().setLayout(groupLayout);
		reFill();		
	}

	/* (non-Javadoc)
	 * @see com.midas.tsp.view.AbstractViewPanel#reFill()	 * 
	 */
	@LocControl(value = {
			@Loc(size=8, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	@Override
	public void reFill() {
		try {
			addButton.setEnabled(false);
			deleteButton.setEnabled(false);
			taskPanel.setVisible(false);
			taskTree.setModel(new DefaultTreeModel(new TaskTreeModel()));
		} catch (TSPException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}	
	
	/* (non-Javadoc)
	 * @see com.midas.tsp.view.AbstractViewPanel#save()
	 */
	@LocControl(value = {
			@Loc(size=20, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	@Override
	public void save() {
		try {
			Task task = null;
			if (taskSelected != null) {
				task = controller.findTask(taskSelected.getId());				
			}
			else {
				task = new Task();
				controller.getTasks().add(task);
				task.setCycle(cycleSelected);
			}
			task.setDescription(nameTextField.getText());
			task.setId(idTextField.getText());
			task.setSize(Integer.parseInt(sizeTextField.getText()));
			task.setDuration(Integer.parseInt(durationTextField.getText()));	
			controller.save();
			//addObject(task);
			taskTree.setModel(new DefaultTreeModel(new TaskTreeModel()));
			taskPanel.setVisible(false);
			
		} catch (TSPException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}	
	
	/* (non-Javadoc)
	 * @see com.midas.tsp.view.AbstractViewPanel#add()
	 */
	@LocControl(value = {
			@Loc(size=2, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public void add() {
		clearForm();
		taskPanel.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see com.midas.tsp.view.AbstractViewPanel#delete()
	 */
	@LocControl(value = {
			@Loc(size=12, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public void delete() {
		try {
			int option = JOptionPane.showConfirmDialog(this, "Esta seguro que desea eliminar la tarea?");
			if (option == JOptionPane.YES_OPTION) {
				Task task = (Task) nodeSelected.getUserObject();
				controller.getTasks().remove(task);
				controller.save();
				taskTree.setModel(new DefaultTreeModel(new TaskTreeModel()));
				taskPanel.setVisible(false);
			}			
		} catch (TSPException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@LocControl(value = {
			@Loc(size=29, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		nodeSelected = (DefaultMutableTreeNode) taskTree.getLastSelectedPathComponent();
		if (nodeSelected == null)
			return;

		Object nodeInfo = nodeSelected.getUserObject();
		if (nodeInfo instanceof String) {
			if (nodeInfo.toString().equals(ROOT_NODE)) {
				addButton.setEnabled(false);
				deleteButton.setEnabled(false);	
				cycleSelected = null;
			}
			else {
				addButton.setEnabled(true);
				deleteButton.setEnabled(false);	
				String cycle = nodeInfo.toString();
				cycleSelected = Integer.parseInt(cycle.substring(CYCLE_NODE.length(), cycle.length())); 
			}			
			taskPanel.setVisible(false);
			taskSelected = null;
		}
		else if (nodeInfo instanceof Task) {
			taskSelected = (Task) nodeInfo;
			addButton.setEnabled(false);
			deleteButton.setEnabled(true);
			taskPanel.setVisible(true);
			nameTextField.setText(taskSelected.getDescription());
			idTextField.setText(taskSelected.getId());
			durationTextField.setText(taskSelected.getDuration().toString());
			sizeTextField.setText(taskSelected.getSize().toString());			
		}			
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#validate()
	 */
	@LocControl(value = {
			@Loc(size=14, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public List<String> validateForm() {
		List<String> messages = new ArrayList<String>();
		if (StringUtils.isBlank(nameTextField.getText())) {
			messages.add("El nombre de la tarea no puede estar vacío");
		}
		if (StringUtils.isBlank(sizeTextField.getText())) {
			messages.add("El tamaño de la tarea no puede estar vacío");
		}
		if (StringUtils.isBlank(durationTextField.getText())) {
			messages.add("La duración de la tarea no puede estar vacía");
		}
		if (StringUtils.isBlank(sizeTextField.getText())) {
			messages.add("El ID de la tarea no puede estar vacío");
		}
		return messages;
	}
	
	/* (non-Javadoc)
	 * @see com.midas.tsp.view.AbstractViewPanel#clearForm()
	 */
	@LocControl(value = {
			@Loc(size=4, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public void clearForm() {
		nameTextField.setText(Constants.BLANK);
		durationTextField.setText(Constants.BLANK);
		sizeTextField.setText(Constants.BLANK);
		idTextField.setText(Constants.BLANK);
	}
	
	/**
	 * Add Node Object to Tree
	 * @param child
	 * @return
	 */
	@LocControl(value = {
			@Loc(size=6, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = taskTree.getSelectionPath();

		if (parentPath != null) {
			parentNode = (DefaultMutableTreeNode) (parentPath
					.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	/**
	 * Add Node Object to parent
	 * @param parent
	 * @param child
	 * @param shouldBeVisible
	 * @return
	 */
	@LocControl(value = {
			@Loc(size=8, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
			Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			taskTree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}
	
	/**
	 * Build Tree's Model
	 * @author Carlos Ivan Duarte C.
	 * @date 27/03/2011
	 */
	private class TaskTreeModel extends DefaultMutableTreeNode {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Default Constructor
		 * @throws TSPException
		 */
		@LocControl(value = {
				@Loc(size=9, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
		})
		public TaskTreeModel() throws TSPException {
			super(ROOT_NODE);
			//rootNode = this;
			TreeMap<String, List<Task>> tasks = convertListToMap(controller.getTasks());
			for (Map.Entry<String, List<Task>> entry : tasks.entrySet()) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry.getKey());
				for (Task task : entry.getValue()) {
					node.add(new DefaultMutableTreeNode(task));
				}
				add(node);
			}		
		}
		
		/**
		 * Convert List<Task> to Map
		 * @param tasks
		 * @return
		 */
		@LocControl(value = {
				@Loc(size=15, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
		})
		private TreeMap<String, List<Task>> convertListToMap(List<Task> tasks) {
			TreeMap<String, List<Task>> map = new TreeMap<String, List<Task>>();
			for (PropertiesTSP prop : tasks) {
				Task task = (Task)prop;
				String cycle = CYCLE_NODE + task.getCycle();
				if (map.containsKey(cycle)) {
					List<Task> value = map.get(cycle);
					value.add(task);
				}
				else {
					List<Task> taskList = new ArrayList<Task>();
					taskList.add(task);
					map.put(cycle, taskList);
				}				
			}
			return map;
		}
	}	
	/**
	 * Build the Action for addButton JButton
	 * @author Carlos Ivan Duarte C.
	 * @date 27/03/2011
	 */
	private class AddAction extends AbstractAction {
		private static final long serialVersionUID = 2117023037126456945L;
		@LocControl(value = {
				@Loc(size=2, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
		})
		public AddAction() {
			putValue(NAME, "+");
			putValue(SHORT_DESCRIPTION, "Add");
		}
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@LocControl(value = {
				@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
		})
		public void actionPerformed(ActionEvent e) {
			add();
		}
	}
	
	/**
	 * Build the Action for deleteButton JButton
	 * @author Carlos Ivan Duarte C.
	 * @date 27/03/2011
	 */
	private class DeleteAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		@LocControl(value = {
				@Loc(size=2, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
		})
		public DeleteAction() {
			putValue(NAME, "-");
			putValue(SHORT_DESCRIPTION, "Delete");
		}
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@LocControl(value = {
				@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
		})
		public void actionPerformed(ActionEvent e) {
			delete();
		}
	}
	
	/**
	 * Build the Action for saveButton JButton
	 * @author Carlos Ivan Duarte C.
	 * @date 27/03/2011
	 */
	private class SaveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		@LocControl(value = {
				@Loc(size=2, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
		})
		public SaveAction() {
			putValue(NAME, "Guardar");
			putValue(SHORT_DESCRIPTION, "Guardar");
		}
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@LocControl(value = {
				@Loc(size=7, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
		})
		public void actionPerformed(ActionEvent e) {
			List<String> messages = validateForm();
			if (!messages.isEmpty()) {
				JOptionPane.showMessageDialog(TasksPanel.this, messages);
			}
			else {
				save();
			}
		}		
	}
}