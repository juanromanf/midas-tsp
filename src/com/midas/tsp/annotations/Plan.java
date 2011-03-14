/**
 * 
 */
package com.midas.tsp.annotations;

import java.lang.annotation.*;

/**
 * @author German Dario Camacho S.
 * @date 20/02/2011
 * Anotación para controlar el tamaño y tiempo planeados para el desarrollo
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Plan {
	int size() ;
	int time() ;
	int cycle() ;
}
