/**
 * 
 */
package com.midas.tsp.annotations.quality;


/**
 * @author German Dario Camacho Sanchez
 * @date 07/03/2011
 * Anotación para llevar control de defectos encontrados en determinada etapa
 * del error y tipo de error encontrado
 *
 */
public @interface LogD {
	int id() default 0;
	int cycleDetected();
	int cycleInyected();
	ProcessPhase phaseInyected();
	ProcessPhase phaseDetected();
	String date() default "";
	boolean removed();
}
