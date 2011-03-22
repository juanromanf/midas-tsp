package com.midas.tsp.model;

/**
 * @author CIDC
 * @date 22/03/2011
 *
 */
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
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param cycle
	 */
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	/**
	 * @return
	 */
	public int getCycle() {
		return cycle;
	}
}