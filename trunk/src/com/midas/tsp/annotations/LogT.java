package com.midas.tsp.annotations;

import java.lang.annotation.*;

import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.ProcessPhase;

/**
 * Annotation that describes a logT from TSP, wich is a task planned for a determined cycle
 * 
 * @author German Dario Camacho S.
 * @date 20/02/2011
 */
@LogDs({@LogD(cycleDetected=1, cycleInjected=1, date="14/03/2011", phaseDetected=ProcessPhase.IMPLEMENTATION, phaseInjected=ProcessPhase.IMPLEMENTATION, id=141, removed=true)}) 
@Retention(RetentionPolicy.RUNTIME)
public @interface LogT {
	/**
	 * @return a <code>String</code> that indicates the date in wich the task
	 *         was done, the date format is dd/MM/yyyy
	 */
	String date() default "";


	/**
	 * @return the identifier of the task as was planned
	 */
	String id();

	/**
	 * @return the time that developing the task took
	 */
	int time() default 0;

	/**
	 * @return the cycle wich the task belongs to
	 */
	int cycle();

	/**
	 * @return the initals of the engineer who completed the task
	 */
	String who();
}
