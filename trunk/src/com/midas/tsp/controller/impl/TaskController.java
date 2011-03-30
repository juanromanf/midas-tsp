package com.midas.tsp.controller.impl;

import java.io.File;
import java.util.List;
import java.util.Properties;

import com.midas.tsp.Constants;
import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.controller.AbstractController;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.Task;
import com.midas.tsp.model.dao.BasicDAO;
import com.midas.tsp.util.Utility;


/**
 * Controller TaskPanel
 * @author Carlos Ivan Duarte C.
 * @date 27/03/2011
 */
@LogTs({@LogT(cycle=3, date="27/03/2011", id="28", time=120, who="CIDC")})
public class TaskController extends AbstractController {
	//private static final String PATH_PROPERTIES = "/Volumes/Archivos/ECOS_NotSync/workspace/midas-tsp/properties/task.properties";
	private static final String PATH_PROPERTIES = "properties" + File.separator + Constants.TASK_PROPERTIES;
	private BasicDAO dao;
	private List<Task> tasks;
	
	/**
	 * Default Constructor, initialize DAO
	 * @throws TSPException
	 */
	public TaskController() throws TSPException {
		dao = new BasicDAO(PATH_PROPERTIES);
		tasks = findTasks();
	}
	
	/**
	 * Find Tasks into Properties file
	 * @return
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "CIDC")})
	@SuppressWarnings("unchecked")
	public List<Task> findTasks() throws TSPException {
		return (List<Task>) Utility.convertoToPropertiesTSP(PATH_PROPERTIES);
	}
	
	/**
	 * Find One Task from Id
	 * @param id
	 * @return
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 6, type = LocControl.LocType.NEW, who = "CIDC")})
	public Task findTask(String id) throws TSPException {
		for (Task task: tasks) {
			if (task.getId().equals(id)) {
				return task;
			}
		}
		return null;
	}
	
	/**
	 * Save the actual List of Task
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 2, type = LocControl.LocType.NEW, who = "CIDC")})
	public void save() throws TSPException {
		Properties prop = Utility.convertToProperties(tasks);
		dao.save(prop);
	}
	
	/**
	 * getter Tasks
	 * @return
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "CIDC")})
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * setter Tasks
	 * @param tasks
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "CIDC")})
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
