/**
 * 
 */
package com.midas.tsp.annotations.quality;


/**
 * Describes any defect detected by an engineer in determined TSP cycle.
 * @author German Dario Camacho Sanchez
 * @date 07/03/2011
 *
 */
public @interface LogD {
	/**
	 * @return id of the defect description
	 */
	int id() default 0;
	/**
	 * @return cycle of TSP where the defect was detected
	 */
	int cycleDetected();
	/**
	 * @return cycle of TSP where the defect was injected 
	 */
	int cycleInjected();
	/**
	 * @return Development phase where the defect was injected
	 */
	ProcessPhase phaseInjected();
	/**
	 * @return Development phase where the defect was detected
	 */
	ProcessPhase phaseDetected();
	/**
	 * @return date when the defect was founded
	 */
	String date() default "";
	/**
	 * @return indicates if the defect was already removed
	 */
	boolean removed();
}
