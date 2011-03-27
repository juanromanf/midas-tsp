package com.midas.tsp.annotations;

/**
 * Annotation to group many <code>Plan</code> objects
 * @author German Dario Camacho Sanchez
 * @date 20/02/2011
 *
 */
public @interface Plans {
	/**
	 * @return an array of <code>Plan</code> objects
	 */
	Plan[] value();

}
