/**
 * 
 */
package com.midas.tsp.annotations;

import java.lang.annotation.*;

/**
 * Annotation that describes the Plan made for a TSP cycle, it has
 * size in lines of code, time in minutes and the cycle wich the plan belongs to.
 * @author German Dario Camacho S.
 * @date 20/02/2011
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Plan {
	/**
	 * @return the estimated size in lines of code.
	 */
	int size() ;
	/**
	 * @return the estimated time in minutes.
	 */
	int time() ;
	/**
	 * @return the cycle wich the plan belongs to.
	 */
	int cycle() ;
}
