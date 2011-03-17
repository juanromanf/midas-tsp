package com.midas.tsp.model;

public class Task extends PropertiesTSP {
	private int cycle;
	private int duration;
	private int size;

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public int getCycle() {
		return cycle;
	}
}