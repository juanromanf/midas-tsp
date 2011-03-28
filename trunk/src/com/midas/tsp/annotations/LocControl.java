/**
 * 
 */
package com.midas.tsp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to group many <code>Loc</code> objects
 * @author German Dario Camacho Sanchez
 * @date 07/03/2011
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
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
