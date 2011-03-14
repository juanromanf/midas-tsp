/**
 * 
 */
package com.midas.tsp.annotations.quality;

/**
 * @author German Dario Camacho
 * @date 07/03/2011
 * Anotaci�n que agrupa los detalles de planeaci�n de calidad mediante un conjunto
 * de detalles de plan de calidad, incluye tambi�n informaci�n a cerca d ela fecha del plan
 */
public @interface PlanQ {
	int cycle();
	DetPlanQ[] details();
}
