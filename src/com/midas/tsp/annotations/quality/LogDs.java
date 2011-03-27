/**
 * 
 */
package com.midas.tsp.annotations.quality;

/**
 * Annotation to group many <code>LogD</code> objects
 * @author German Dario Camacho Sanchez
 * @date 07/03/2011
 *
 */
public @interface LogDs {
	/**
	 * @return an array of <code>LogD</code> objects
	 */
	LogD[] value();
}
