/**
 * 
 */
package com.midas.tsp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.Plan;
import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.PlanQ;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.util.Utility;

/**
 * The Model class represents a set of information related to all the TSP
 * annotations processed in a set of source. The data collected is stored as
 * totals for useful information in order to have direct access and avoid
 * recalculations. A number of Maps store data separating each TSP cycle founded
 * in the data processed so if an special calculation is needed you can use the
 * map related to calculate specific data.
 * 
 * @author German Dario Camacho S.
 * @date 20/02/2011
 */

@LogTs({
		@LogT(cycle = 2, date = "22/03/2011", id = "4", time = 70, who = "GDCS"),
		@LogT(cycle = 3, date = "25/03/2011", id = "19", time = 146, who = "GDCS") })
public class Model {

	/**
	 * Stores the estimated total size of code in the analized project
	 */
	private Integer estimatedSize = 0;

	/**
	 *  Stores the estimated total time of development in the analized project
	 */
	private Integer estimatedTime = 0;

	/**
	 * Stores the total size of the project in locs, this value is acummulated
	 * through calls of the add method
	 */
	private Integer totalSize = 0;

	/**
	 * Stores the total quantity of new locs founded int the project, this value
	 * is acummulated through calls of the add method
	 */
	private Integer newLocs = 0;

	/**
	 * Stores the total quantity of modified locs founded int the project, this
	 * value is acummulated through calls of the add method
	 */
	private Integer modifiedLocs = 0;

	/**
	 * Stores the total quantity of reused locs founded int the project, this
	 * value is acummulated through calls of the add method
	 */
	private Integer reusedLocs = 0;

	/**
	 * Stores the total quantity of generated locs founded int the project, this
	 * value is acummulated through calls of the add method
	 */
	private Integer generatedLocs = 0;

	/**
	 * Stores the total size of the project in locs, this value is acummulated
	 * through calls of the add method
	 */
	private Integer removedLocs = 0;

	/**
	 * Stores the total size of the project in locs, this value is acummulated
	 * through calls of the add method
	 */
	private Integer totalTime = 0;

	/**
	 * This <code>Map</code> stores each <code>Plan</code> object per TSP cycle,
	 * only one <code>Plan</code> must exist per cycle, so, many add method
	 * calls on a cycle with a <code>Plan</code> will override the
	 * <code>Plan</code> previously added if that exists
	 */
	private Map<Integer, Plan> planPerCycle;

	/**
	 * This <code>Map</code> stores each <code>PlanQ</code> object per TSP
	 * cycle, only one <code>PlanQ</code> must exist per cycle so, many add
	 * method calls on a cycle with a <code>PlanQ</code> will override the
	 * <code>PlanQ</code> previously added if that exists.
	 */
	private Map<Integer, PlanQ> planQPerCycle;

	/**
	 * This Map stores all the <code>LogT</code> annotations found per TSP
	 * cycle, many add method calls on a cycle will add the <code>LogT</code> in
	 * an <code>ArrayList<LogT></code> object tha will be stored in the map for
	 * each TSP cycle.
	 */
	private Map<Integer, List<LogT>> logTsPerCycle;

	/**
	 * This <code>Map</code> stores all the <code>Loc</code> annotations found
	 * per TSP cycle, many add method calls with an object <code>Loc</code> for
	 * the same cycle will add the Loc in an <code>ArrayList<Loc></code> object
	 * that will be stored in the map for each TSP cycle
	 */
	private Map<Integer, List<Loc>> locsPerCycle;

	/**
	 * This Map stores all the <code>LogD</code> annotations found per TSP
	 * cycle, many add method calls on a cycle will add the LogT in an
	 * <code>ArrayList<LogT></code> object tha will be stored in the map for
	 * each TSP cycle
	 */
	private Map<Integer, List<LogD>> logDsPerCycle;

	/**
	 * Creates an instance of <code>Model</code> and initializes all the maps to
	 * empy instances All totals are set to zero at the creation of the instance
	 */
	public Model() {

		planPerCycle = new HashMap<Integer, Plan>();
		planQPerCycle = new HashMap<Integer, PlanQ>();
		logTsPerCycle = new HashMap<Integer, List<LogT>>();
		locsPerCycle = new HashMap<Integer, List<Loc>>();
		logDsPerCycle = new HashMap<Integer, List<LogD>>();

	}

	/**
	 * Process an <code>LogT</code> adding it to the cycle it belongs and
	 * acummulating the time on the <code>totalTime</code> property.
	 * 
	 * @param object
	 *            the specified <code>LogT</code> to process
	 * @throws TSPException
	 *             In case any error were raised in the method invocation
	 */
	@LocControl(value = {
			@Loc(cycle = 2, size = 7, type = LocControl.LocType.NEW, who = "GDCS"),
			@Loc(cycle = 2, size = 2, type = LocControl.LocType.DELETED, who = "GDCS"),
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "GDCS") })
	public void add(LogT object) throws TSPException {

		Utility.notNullValidation(object, "adding a LogT");

		List<LogT> logTsList = null;
		logTsList = logTsPerCycle.get(object.cycle());

		if (logTsList == null) {
			logTsList = new ArrayList<LogT>();
			logTsPerCycle.put(object.cycle(), logTsList);
		}

		logTsList.add(object);
		totalTime += object.time();

	}

	/**
	 * Process an <code>Plan</code> adding it to the cycle it belongs.
	 * 
	 * @param object
	 *            the specified <code>Plan</code> to process
	 * @throws TSPException
	 *             In case any error were raised in the method invocation
	 */
	@LocControl({
			@Loc(cycle = 2, size = 1, type = LocControl.LocType.NEW, who = "GDCS"),
			@Loc(cycle = 2, size = 2, type = LocControl.LocType.REUSED, who = "GDCS"),
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "GDCS") })
	public void add(Plan object) throws TSPException {

		Utility.notNullValidation(object, "adding a Plan");
		planPerCycle.put(object.cycle(), object);
		estimatedSize += object.size();
		estimatedTime += object.time();
	}

	/**
	 * Process an <code>PlanQ</code> adding it to the cycle it belongs.
	 * 
	 * @param object
	 *            the specified <code>PlanQ</code> to process
	 * @throws TSPException
	 *             In case any error were raised in the method invocation
	 */
	@LocControl(value = {
			@Loc(cycle = 2, size = 1, type = LocControl.LocType.NEW, who = "GDCS"),
			@Loc(cycle = 2, size = 3, type = LocControl.LocType.DELETED, who = "GDCS"),
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "GDCS") })
	public void add(PlanQ object) throws TSPException {

		Utility.notNullValidation(object, "adding a PlanQ");
		planQPerCycle.put(object.cycle(), object);
	}

	/**
	 * Process an <code>LogD</code> adding it to the cycle it belongs.
	 * 
	 * @param object
	 *            the specified <code>LogD</code> to process
	 * @throws TSPException
	 *             In case any error were raised in the method invocation
	 */
	@LocControl(value = {
			@Loc(cycle = 2, size = 6, type = LocControl.LocType.NEW, who = "GDCS"),
			@Loc(cycle = 2, size = 6, type = LocControl.LocType.DELETED, who = "GDCS"),
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "GDCS") })
	public void add(LogD object) throws TSPException {

		Utility.notNullValidation(object, "adding a LogD");
		List<LogD> listaDefectos = null;
		listaDefectos = logDsPerCycle.get(object.cycleDetected());

		if (listaDefectos == null) {
			listaDefectos = new ArrayList<LogD>();
			logDsPerCycle.put(object.cycleDetected(), listaDefectos);
		}
		listaDefectos.add(object);

	}

	/**
	 * Process an <code>Loc</code> adding it to the cycle it belongs.
	 * 
	 * @param object
	 *            the specified <code>Loc</code> to process
	 * @throws TSPException
	 *             In case any error were raised in the method invocation
	 */
	@LocControl(value = {
			@Loc(cycle = 2, size = 6, type = LocControl.LocType.NEW, who = "GDCS"),
			@Loc(cycle = 2, size = 17, type = LocControl.LocType.DELETED, who = "GDCS"),
			@Loc(cycle = 3, size = 20, type = LocControl.LocType.NEW, who = "GDCS") })
	public void add(Loc object) throws TSPException {

		Utility.notNullValidation(object, "adding a Loc");
		List<Loc> listaLocs = null;
		listaLocs = locsPerCycle.get(object.cycle());
		if (listaLocs == null) {
			listaLocs = new ArrayList<Loc>();
			locsPerCycle.put(object.cycle(), listaLocs);
		}
		switch (object.type()) {
		case NEW: {
			newLocs += object.size();
			break;
		}
		case REUSED: {
			reusedLocs += object.size();
			break;
		}
		case MODIFIED: {
			modifiedLocs += object.size();
			break;
		}
		case GENERATED: {
			generatedLocs += object.size();
			break;
		}
		case DELETED: {
			removedLocs += object.size();
			break;
		}
		default: {
			throw new TSPException("Loc without type has been detected.");
		}
		}

		listaLocs.add(object);
		totalSize += object.size();
	}

	/**
	 * Process an <code>LocControl</code> obtaining all the <code>Loc</code>
	 * object it carries.
	 * 
	 * @param object
	 *            the specified <code>LocControl</code> to process
	 * @throws TSPException
	 *             In case any error were raised in the method invocation
	 */
	@LocControl(value = {
			@Loc(cycle = 2, size = 6, type = LocControl.LocType.NEW, who = "GDCS"),
			@Loc(cycle = 2, size = 17, type = LocControl.LocType.DELETED, who = "GDCS"),
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "GDCS") })
	public void add(LocControl object) throws TSPException {

		Utility.notNullValidation(object, "adding a Loc");
		for (Loc tmpLoc : object.value()) {
			add(tmpLoc);
		}
	}

	/**
	 * @return Returns the estimated size of the processed project
	 */
	@LocControl(value = {
			@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS"),
			@Loc(cycle = 2, size = 13, type = LocControl.LocType.DELETED, who = "GDCS") })
	public Integer getEstimatedSize() {
		return estimatedSize;
	}

	/**
	 * @return Returns the estimated time of the processed project
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Integer getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * @return Returns the total size of the processed project
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Integer getTotalSize() {
		return totalSize;
	}

	/**
	 * @return Returns the total time of the processed project
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Integer getTotalTime() {
		return totalTime;
	}

	/**
	 * @return Returns the new locs of the processed project
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Integer getNewLocs() {
		return newLocs;
	}

	/**
	 * @return Returns the modified locs of the processed project
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Integer getModifiedLocs() {
		return modifiedLocs;
	}

	/**
	 * @return Returns the reused locs of the processed project
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Integer getReusedLocs() {
		return reusedLocs;
	}

	/**
	 * @return Returns the removed locs of the processed project
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Integer getRemovedLocs() {
		return removedLocs;
	}

	/**
	 * @return Returns the <code>Map</code> that contains all <code>Plan</code>
	 *         objects per cycle
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Map<Integer, Plan> getPlanPerCycle() {
		return planPerCycle;
	}

	/**
	 * @return Returns the <code>Map</code> that contains all <code>PlanQ</code>
	 *         objects per cycle
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Map<Integer, PlanQ> getPlanQPerCycle() {
		return planQPerCycle;
	}

	/**
	 * @return Returns the <code>Map</code> that contains all <code>LogT</code>
	 *         objects per cycle
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Map<Integer, List<LogT>> getLogTsPerCycle() {

		return logTsPerCycle;
	}

	/**
	 * @return Returns the <code>Map</code> that contains all <code>LogT</code>
	 *         objects per cycle
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Map<Integer, List<Loc>> getLocsPerCycle() {
		return locsPerCycle;
	}

	/**
	 * @return Returns the <code>Map</code> that contains all <code>LogD</code>
	 *         objects per cycle
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Map<Integer, List<LogD>> getLogDsPerCycle() {
		return logDsPerCycle;
	}

	/**
	 * @return Returns the generated locs of the processed project
	 */
	@Loc(cycle = 2, size = 1, type = LocControl.LocType.GENERATED, who = "GDCS")
	public Integer getGeneratedLocs() {
		return generatedLocs;
	}

}
