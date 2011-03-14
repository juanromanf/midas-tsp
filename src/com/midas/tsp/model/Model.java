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
import com.midas.tsp.annotations.Plan;
import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.PlanQ;
import com.midas.tsp.annotations.quality.ProcessPhase;
import com.midas.tsp.util.Utility;



/**
 * Se encarga de mantener la información relacionada a todas las anotaciones
 * que se procesen
 * @author German Dario Camacho S.
 * @date 20/02/2011
 */
public class Model {
	
	private Integer estimatedSize=0;
	private Integer estimatedTime=0;
	private Integer totalSize=0;
	private Integer newLocs=0;
	private Integer modifiedLocs=0;
	private Integer addedLocs=0;
	private Integer removedLocs=0;
	private Integer totalTime=0;
	private Integer totalIntTime=0;
	private List<LogT> logTlist;
	private List<LocControl> locControlList;
	private List<Loc> locList;
	private List<PlanQ> planQList;
	private Map<ProcessPhase,Integer> errorsStage; 
	
	public Model(){
		setLogTlist(new ArrayList<LogT>());
		setLocList(new ArrayList<Loc>());
		setLocControlList(new ArrayList<LocControl>());
		setPlanQList(new ArrayList<PlanQ>());
		setErrorsStage(new HashMap<ProcessPhase,Integer>());
	}


	public void addLogT(LogT tmp){
		totalTime+=tmp.time();
		getLogTlist().add(tmp);
	}
	

	public void setPlan(Plan tmp){
		estimatedSize=estimatedSize+tmp.size();
		estimatedTime=estimatedTime+ tmp.time();
	}
	

	public void addPlanQ(PlanQ tmp){
		getPlanQList().add(tmp);
	}
	

	public void addLogD(LogD tmp){
		Integer tmpInt=0;
		if(errorsStage.get(tmp.phaseDetected())==null){
			errorsStage.put(tmp.phaseDetected(),tmpInt);
		}
		tmpInt=errorsStage.get(tmp.phaseDetected());
		errorsStage.put(tmp.phaseDetected(),tmpInt+1);
	}
	
	public Double getProductivity(){
		if(getTotalTime()<=0){
			return 0.0;
		}
		return getTotalSize().doubleValue()/Utility.minutes2hours(getTotalTime()-getTotalIntTime());
	}
	

	public void addLoc(Loc tmp){
		
		switch (tmp.type()){
			case NUEVA :{
				totalSize+=tmp.size();
				newLocs+=tmp.size();
				break;
			}case REUTILIZADA :{
				totalSize+=tmp.size();
				addedLocs+=tmp.size();
				break;
			}case MODIFICADA :{
				modifiedLocs+=tmp.size();
				break;
			}case ELIMINADA :{
				totalSize-=tmp.size();
				removedLocs+=tmp.size();
				break;
			}
		}
		
		getLocList().add(tmp);
		
	}
	

	public List<Loc> getLocList() {
		return locList;
	}
	

	public void setLocList(List<Loc> locList) {
		this.locList = locList;
	}

	public Integer getEstimatedSize() {
		return estimatedSize;
	}
	
	
	public void setEstimatedSize(Integer estimatedSize) {
		this.estimatedSize = estimatedSize;
	}

	public Integer getEstimatedTime() {
		return estimatedTime;
	}
	

	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	
	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	

	public Integer getTotalTime() {
		return totalTime;
	}
	

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getTotalIntTime() {
		return totalIntTime;
	}
	

	public void setTotalIntTime(Integer totalIntTime) {
		this.totalIntTime = totalIntTime;
	}


	public void setLogTlist(List<LogT> logTlist) {
		this.logTlist = logTlist;
	}

	
	public List<LogT> getLogTlist() {
		return logTlist;
	}

	public List<LocControl> getLocControlList() {
		return locControlList;
	}

	public void setLocControlList(List<LocControl> locControlList) {
		this.locControlList = locControlList;
	}

	public Integer getNewLocs() {
		return newLocs;
	}

	public void setNewLocs(Integer newLocs) {
		this.newLocs = newLocs;
	}

	public Integer getModifiedLocs() {
		return modifiedLocs;
	}

	public void setModifiedLocs(Integer modifiedLocs) {
		this.modifiedLocs = modifiedLocs;
	}

	public Integer getAddedLocs() {
		return addedLocs;
	}

	public void setAddedLocs(Integer addedLocs) {
		this.addedLocs = addedLocs;
	}

	public Integer getRemovedLocs() {
		return removedLocs;
	}

	public void setRemovedLocs(Integer removedLocs) {
		this.removedLocs = removedLocs;
	}

	public void setPlanQList(List<PlanQ> planQList) {
		this.planQList = planQList;
	}

	public List<PlanQ> getPlanQList() {
		return planQList;
	}

	public void setErrorsStage(Map<ProcessPhase,Integer> errorsStage) {
		this.errorsStage = errorsStage;
	}

	public Map<ProcessPhase,Integer> getErrorsStage() {
		return errorsStage;
	}

}
