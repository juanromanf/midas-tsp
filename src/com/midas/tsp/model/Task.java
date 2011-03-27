package com.midas.tsp.model;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;

/**
 * @author CIDC
 * @date 22/03/2011
 *
 */
@LogTs({@LogT(cycle=2, date="22/03/2011", id="15", time=3, who="CIDC")})
public class Task extends PropertiesTSP {
	/**
	 * Atributo para el ciclo de la tarea
	 */
	private int cycle;
	/**
	 * Atributo para la duraci—n estimada de la tarea
	 */
	private int duration;
	/**
	 * Atributo para el tama–o estimado de la tarea
	 */
	private int size;

	/**
	 * @param duration
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public int getDuration() {
		return duration;
	}

	/**
	 * @param size
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public int getSize() {
		return size;
	}

	/**
	 * @param cycle
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	/**
	 * @return
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public int getCycle() {
		return cycle;
	}
}