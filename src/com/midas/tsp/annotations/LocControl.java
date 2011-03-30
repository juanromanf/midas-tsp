/**
 * 
 */
package com.midas.tsp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.ProcessPhase;

/**
 * Annotation to group many <code>Loc</code> objects
 * @author German Dario Camacho Sanchez
 * @date 07/03/2011
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@LogDs({@LogD(cycleDetected=3, cycleInjected=2, date="27/03/2011", id=138, phaseDetected=ProcessPhase.IMPLEMENTATION, phaseInjected=ProcessPhase.IMPLEMENTATION, removed = true)})
public @interface LocControl {
	/**
	 * <code>Enum</code> to identify the diferent types of line of code
	 * @author German Dario Camacho Sanchez
	 * @date 07/03/2011
	 */
	enum LocType{
		NEW,REUSED,MODIFIED,DELETED, GENERATED
	}
	/**
	 * @return an array of <code>Loc</code> objects
	 */
	Loc[] value();
	

}
