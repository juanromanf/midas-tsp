/**
 * 
 */
package com.midas.tsp.annotations.quality;

/**
 * Describes all the quality plan defined in the planning process of TSP, it has
 * the identifier of the TSP cycle it belongs to and the set of details of quality
 * for each development phase.
 * @author German Dario Camacho
 * @date 07/03/2011
 */
public @interface PlanQ {
	/**
	 * @return the cycle wich the task belongs to
	 */
	int cycle();

	/**
	 * @return an array of <code>DetPlanQ</code> objects
	 */
	DetPlanQ[] details();
}
