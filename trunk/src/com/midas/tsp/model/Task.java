package com.midas.tsp.model;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;

/**
 * @author Carlos Ivan Duarte C.
 * @date 22/03/2011
 *
 */
@LogTs({@LogT(cycle=2, date="22/03/2011", id="15", time=3, who="CIDC")})
public class Task extends PropertiesTSP {
	/**
	 * Attribute to the cycle of task
	 */
	private Integer cycle;
	/**
	 * Attribute for the estimated duration task
	 */
	private Integer duration;
	/**
	 * Attribute for the task's estimated size 
	 */
	private Integer size;
	/**
	 * @param duration
	 */
	@LocControl(value = {
			@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	})
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return
	 */
	@LocControl(value = {
			@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	})
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param size
	 */
	@LocControl(value = {
			@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	})
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * @return
	 */
	@LocControl(value = {
			@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	})
	public Integer getSize() {
		return size;
	}

	/**
	 * @param cycle
	 */
	@LocControl(value = {
			@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	})
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	/**
	 * @return
	 */
	@LocControl(value = {
			@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	})
	public Integer getCycle() {
		return cycle;
	}	
}