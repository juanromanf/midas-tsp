package com.midas.tsp.util;

import java.text.NumberFormat;


/**
 * @author German Dario Camacho S.
 * @date 21/02/2011
 * Clase con diferentes utilerias para conversiones u operaciones sobre los datos
 */
public class Utility {

	public static NumberFormat numberFormatter = NumberFormat.getInstance();
		
	/**
	 * Metodo que convierte minutos a horas
	 * @param minutes minutos a convertir
	 * @return Double con la cantidad de horas calculadas
	 */
	public static Double minutes2hours(Integer minutes){
		
		return minutes/60.0;
	}
	
	
	
}
