/**
 * 
 */
package com.midas.tsp.annotations;


/**
 * Annotation to group many <code>LogT</code> objects
 * @author German Dario Camacho Sanchez
 * @date 20/02/2011
 *
 */
public @interface LogTs {
	/**
	 * @return an array of <code>Loc</code> objects
	 */
	LogT[] value();
}
