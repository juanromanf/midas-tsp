package com.midas.tsp.annotations.quality;

/**
 * Annotation that describes the planned quality detail for a determined phase of
 * development and the number of defects to inyect and remove.
 * @author German Dario Camacho S.
 * @date 07/03/2011
 * 
*/
public @interface DetPlanQ {
	/**
	 * @return Development phase of the quality detail
	 */
	ProcessPhase processPhase();
	/**
	 * @return number of defects planned to inyect
	 */
	int inyect() default 0;
	/**
	 * @return number of defects planned to remove
	 */
	int remove() default 0;

}
