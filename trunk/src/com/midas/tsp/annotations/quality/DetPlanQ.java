package com.midas.tsp.annotations.quality;

/**
 * @author German Dario Camacho
 * @date 07/03/2011
 * Anotaci�n que la etapa que se va a analizar para calidad, la cantidad
 * de errores que se estima ser�n inyectados y la cantidad de errores que se
 * espera remover 
 * 
*/
public @interface DetPlanQ {
	ProcessPhase processPhase();
	int inyect() default 0;
	int remove() default 0;

}
