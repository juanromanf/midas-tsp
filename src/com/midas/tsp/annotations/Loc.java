/**
 * 
 */
package com.midas.tsp.annotations;

import java.lang.annotation.*;

import com.midas.tsp.annotations.LocControl.LocType;

/**
 * @author German Dario Camacho S.
 * @date 20/02/2011
 * Anotaci�n para tomar las interrupciones presentadas junto con su fecha y duraci�n
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loc {
	int size() default 0;
	LocType type() default com.midas.tsp.annotations.LocControl.LocType.NUEVA;
	String who();
}
