package com.midas.tsp.view;

import java.util.List;

import javax.swing.JPanel;

import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.ProcessPhase;

/**
 * Contains the abstract methods for all Panels
 * 
 * @author carlos.duarte
 * @date 27/03/2011
 * 
 */
@LogDs(@LogD(cycleDetected=3, cycleInjected=3, date="27/03/2011", phaseDetected=ProcessPhase.IMPLEMENTATION, phaseInjected=ProcessPhase.DESIGN, removed=true, id=143))
public abstract class AbstractViewPanel extends JPanel {

	private static final long serialVersionUID = 5280454748340309961L;

	/**
	 * Repaint the JPanel
	 */
	public abstract void reFill();

	/**
	 * Save Action
	 */
	public abstract void save();

	/**
	 * Add Action
	 */
	public abstract void add();

	/**
	 * Delete Action
	 */
	public abstract void delete();

	/**
	 * Clear Form Action
	 */
	public abstract void clearForm();
	
	/**
	 * Validate Forms
	 */
	public abstract List<String> validateForm();
}
