/**
 * 
 */
package com.midas.tsp.annotations.quality;

/**
 * @author German Dario Camacho
 * @date 07/03/2011
 * Anotación que agrupa los detalles de planeación de calidad mediante un conjunto
 * de detalles de plan de calidad, incluye también información a cerca d ela fecha del plan
 */
public @interface PlanQ {
	int cycle();
	DetPlanQ[] details();
}
