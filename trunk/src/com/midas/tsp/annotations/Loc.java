/**
 * 
 */
package com.midas.tsp.annotations;

import java.lang.annotation.*;

import com.midas.tsp.annotations.LocControl.LocType;

/**
 * Annotation to gather information about size, who did,type and cycle a Line of code
 * was made.
 * @author German Dario Camacho S.
 * @date 20/02/2011
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loc {
	/**
	 * @return The number of lines of code.
	 */
	int size() default 0;
	/**
	 * @return The TSP when the line of code was made
	 */
	int cycle();
	/**
	 * @return The type of line of code
	 */
	LocType type() default com.midas.tsp.annotations.LocControl.LocType.NEW;
	/**
	 * @return Returns the initials of the engineer who write the lines of code
	 */
	String who();
}
