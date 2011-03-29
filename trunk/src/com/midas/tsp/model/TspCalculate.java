package com.midas.tsp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.Plan;
import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.PlanQ;
import com.midas.tsp.annotations.quality.ProcessPhase;


/**
 * TspCalculate Class includes a Model object as basic data source. Using it calculates the performance data 
 * of the TSP project
 * 
 * @author Rodolfo Hernandez
 * @date 27/02/2011
 */

public class TspCalculate {
	@LogTs({@LogT(cycle = 3, date = "27/03/2011", id = "23", time = 234, who = "RHR"),
		@LogT(cycle = 3, date = "26/03/2011", id = "999", time = 90, who = "RHR"), 
		@LogT(cycle = 3, date = "27/03/2011", id = "999", time = 246, who = "RHR"),
		@LogT(cycle = 3, date = "27/03/2011", id = "999", time = 139, who = "RHR"),
		@LogT(cycle = 3, date = "27/03/2011", id = "999", time = 60, who = "RHR"),
		@LogT(cycle = 3, date = "27/03/2011", id = "999", time = 12, who = "RHR")})
	/**
	 * the main Model data object with all annotation data
	 */
	private Model tspProject;
	
	/**
	 * the number of cycles contained in the tspProject Model object
	 */
	private int lastCycle = 0;
	
	/**
	 * Creates an instance of <code>TspCalculate</code> having a Model Object as main parameter.
	 * initializes the attribute lastCycle with the last cycle included in the 
	 * PlanPerCycle Map of Model attribute tspProject
	 */
	public TspCalculate( Model tspdata) {
		tspProject = tspdata;
		checkModel();
	}

	@LocControl(value = { @Loc(cycle = 3, size = 2, type = LocControl.LocType.NEW, who = "RHR"), 
						  @Loc(cycle = 3, size = 11, type = LocControl.LocType.NEW, who = "RHR") })
	/**
	 * checks the Model object tspProject to find out the last cycle included
	 * <br> post </br> lastCycle contains the last included cycle (1...N)
	 */
	private void checkModel(){
		lastCycle = 0;
		if (tspProject == null)
			return;
		
		Set keys = tspProject.getPlanPerCycle().keySet();
		Iterator keyIter = keys.iterator();
	    while (keyIter.hasNext()) {
	         Object key = keyIter.next();  // Get the next key.
	         if (lastCycle < (Integer) key) {
	        	 lastCycle = (Integer) key;
	         }
	      }
	}
	
	@LocControl(value = { @Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "RHR") })
	/**
	 * @return the last cycle number in PlanPerCycle Map of Model attribute tspProject (calculated at construction)
	 */
	public int getLastCycleIncluded(){
		return lastCycle;
	}
	
	@LocControl(value = { @Loc(cycle = 3, size = 13, type = LocControl.LocType.NEW, who = "RHR") })
	/**
	 * uses the Model tspProject to access the LocsPerCycle map, and sum the LOCs recorded
	 * @param cycle the target cycle
	 * @param loc_type the type of LOC to be counted
	 * @return the number of LOCs in the cycle "cycle" and have the type "loc_type"
	 */
	public int getCycleLOCsByType(int cycle, LocControl.LocType locType){
		int loc_size=0;
		if ((lastCycle < 1) || (lastCycle < cycle)) {
			return loc_size;
		}
		
		List<Loc> listaLocs = tspProject.getLocsPerCycle().get(cycle);
		if (listaLocs == null) {
			return loc_size;
		}
		
		for (int ind=0; ind< listaLocs.size(); ind++) {
			if (listaLocs.get(ind).type().equals(locType)) {
				loc_size += listaLocs.get(ind).size();
			}
		}
		
		return loc_size;
	}

	@LocControl(value = { @Loc(cycle = 3, size = 9, type = LocControl.LocType.NEW, who = "RHR") })
	/**
	 * uses the Model tspProject to calculate the error in size estimation
	 * LOC: counting only NEW and GENERATED type of LOCs
	 * @param cycle the target cycle
	 * @return the percentage error between estimated size and planed size
	 */
	public double getSizeEstimationError(int cycle){
		double errorValue = 0.0;
		if (tspProject.getPlanPerCycle().get(cycle) == null)
			return errorValue;
		
		int realSize = (getCycleLOCsByType(cycle, LocControl.LocType.NEW) + getCycleLOCsByType(cycle, LocControl.LocType.GENERATED));
		int estimatedSize = (tspProject.getPlanPerCycle().get(cycle).size());
		if (estimatedSize == 0) {
			return errorValue;
		}
		
		errorValue = 100.0 *  (double) ( (realSize - estimatedSize)/estimatedSize) ;
		return errorValue;
	}
	
	@LocControl(value = { @Loc(cycle = 3, size = 17, type = LocControl.LocType.NEW, who = "RHR") })
	/**
	 * uses the Model tspProject to calculate the error in duration estimation
	 * LogT provides the duration values for the cycle
	 * @param cycle the target cycle
	 * @return the percentage error between estimated duration and planed duration (0.0% if cycle doesn't exist)
	 */
	public double getDurationEstimationError(int cycle){
		double errorValue = 0.0;
		if ((lastCycle < 1) || (lastCycle < cycle))
			return errorValue;
		
		if (tspProject.getPlanPerCycle().get(cycle) == null) {
			return errorValue;
		}
		
		int estimatedTime = (tspProject.getPlanPerCycle().get(cycle).time());
		if (estimatedTime == 0) {
			return errorValue;
		}
		
		List<LogT> logTsList = tspProject.getLogTsPerCycle().get(cycle);
		if (logTsList == null)  {
			return errorValue;
		}
		
		int realTime = 0; 
		for (int ind=0; ind< logTsList.size(); ind++) {
			realTime += logTsList.get(ind).time();
		}
		
		errorValue = 100.0 * (double) ((realTime - estimatedTime )/estimatedTime) ;
		return errorValue;
	}
	
	@LocControl(value = { @Loc(cycle = 3, size = 14, type = LocControl.LocType.NEW, who = "RHR") })
	/**
	 * uses the Model tspProject to calculate the amount of defects by detected phase
	 * LogD provides the number of defects detected
	 * @param cycle the target cycle. the defects
	 * @param justunit true to count only the defects detected in TEST phase, false for the defects in all the phases but TEST 
	 * @return the number of defects in the chosen phases
	 */
	public int getCycleDefects(int cycle, boolean just_junit){
		int defectCounter=0;
		
		if ((lastCycle < 1) || (lastCycle < cycle)) {
			return defectCounter;
		}
		
		if (tspProject.getLogDsPerCycle().get(cycle) == null) {
			return defectCounter;
		}
		
		List<LogD> logDsList = tspProject.getLogDsPerCycle().get(cycle);
		if (logDsList == null) {
			return defectCounter;
		}
		
		for (int ind=0; ind< logDsList.size(); ind++) {
			boolean comp = logDsList.get(ind).phaseDetected().equals(ProcessPhase.TESTS);
			if( (comp && just_junit) || (!comp && !just_junit)) {
				defectCounter += 1;
			}
		}
		
		return defectCounter;
	}

	@LocControl(value = { @Loc(cycle = 3, size = 9, type = LocControl.LocType.NEW, who = "RHR") })
	/** 
	 * uses the Model tspProject to calculate the defect ratio for the phases until INSPECTION or just for TEST phase
	 * LogD provides the duration values for the cycle
	 * @param cycle the target cycle
	 * @param justunit  true: just for the TEST phase, false: for all the phases but TEST
	 * @return the number of defects per KLOC for the chosen phases (0 if the LOC size is zero or if the cycle is not included)
	 */
	public double getCycleDKLOCSPartial(int cycle, boolean justJunit){
		double dkloc = 0.0;
		if ((lastCycle < 1) || (lastCycle < cycle)) {
			return dkloc;
		}
		
		int realSize = (getCycleLOCsByType(cycle, LocControl.LocType.NEW) + getCycleLOCsByType(cycle, LocControl.LocType.GENERATED));
		if (realSize == 0) {
			return dkloc;
		}
		
		int defectAmount = getCycleDefects(cycle, justJunit);
		
		dkloc = 1000.0 *  (double) ( defectAmount / realSize) ;
		return dkloc;
	}

	@LocControl(value = { @Loc(cycle = 3, size = 9, type = LocControl.LocType.NEW, who = "RHR") })
	/** 
	 * uses the Model tspProject to calculate the defect ratio for the cycle for all the phases
	 * LogD provides the duration values for the cycle
	 * @param cycle the target cycle
	 * @return the number of defects per KLOC for the chosen phases (0 if the LOC size is zero or if the cycle is not included)
	 */
	public double getCycleDKLOCS(int cycle){
		double dkloc = 0.0;
		if ((lastCycle < 1) || (lastCycle < cycle)) {
			return dkloc;
		}
		
		int realSize = (getCycleLOCsByType(cycle, LocControl.LocType.NEW) + getCycleLOCsByType(cycle, LocControl.LocType.GENERATED));
		if (realSize == 0) {
			return dkloc;
		}
		
		int defectAmount = getCycleDefects(cycle, true) + getCycleDefects(cycle, false);
		
		dkloc = 1000.0 *  (double) ( defectAmount / realSize) ;
		return dkloc;
	}
	
	
	@LocControl(value = { @Loc(cycle = 3, size = 15, type = LocControl.LocType.NEW, who = "RHR") })
	/**
	 * uses the Model tspProject to calculate the productivity by cycle
	 * LOC: counting only NEW and GENERATED type of LOCs
	 * LogT provides the duration values for the cycle
	 * @param cycle the target cycle
	 * @return the productivity value in LOCs/hour
	 */
	public double getCycleProductivity(int cycle){
		double productivity = 0.0;
		if ((lastCycle < 1) || (lastCycle < cycle)) {
			return productivity;
		}
		
		int realSize = (getCycleLOCsByType(cycle, LocControl.LocType.NEW) + getCycleLOCsByType(cycle, LocControl.LocType.GENERATED));
		if (realSize  == 0) {
			return productivity;
		}
		
		List<LogT> logTsList = tspProject.getLogTsPerCycle().get(cycle);
		if (logTsList == null) {
			return productivity;
		}
		
		int realTime = 0; 
		for (int ind=0; ind< logTsList.size(); ind++) {
			realTime += logTsList.get(ind).time();
		}
		productivity = 60.0 * (double) (realSize/realTime);
		return productivity;
	}
}
