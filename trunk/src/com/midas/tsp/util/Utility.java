package com.midas.tsp.util;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.midas.tsp.Constants;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.exceptions.TSPException;

/**
 * @author German Dario Camacho S.
 * @date 21/02/2011 Clase con diferentes utilerias para conversiones u
 *       operaciones sobre los datos
 */
public class Utility {

	public static NumberFormat numberFormatter = NumberFormat.getInstance();

	/**
	 * Metodo que convierte minutos a horas
	 * 
	 * @param minutes
	 *            minutos a convertir
	 * @return Double con la cantidad de horas calculadas
	 */
	public static Double minutes2hours(Integer minutes) {
		return minutes / 60.0;
	}

	/**
	 * Permite convertir un Map en un Properties con un value separado por | 
	 * @param map
	 * @return
	 * @throws TSPException
	 */
	@LogTs({@LogT(cycle=1, date="14/03/2011", id="???", time=20, who="CIDC")})
	public static Properties convertMapToProperties(Map<String, LinkedList<String>> map)
			throws TSPException {
		Properties prop = null;
		String valueProperties = "";
		try {
			prop = new Properties();
			for (Entry<String, LinkedList<String>> entry : map.entrySet()) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					String value = entry.getValue().get(i);
					valueProperties = valueProperties + value;
					if (entry.getValue().size() < i) {
						valueProperties = valueProperties + Constants.SEPARATOR_PROPERTIES;
					}
				}
				prop.setProperty(entry.getKey(), valueProperties);
			}
		} catch (Exception ex) {
			throw new TSPException(ex);
		}
		return prop;
	}
	
	/**
	 * Permite convertir un Properties en un mapa
	 * @param prop
	 * @return
	 * @throws TSPException
	 */
	@LogTs({@LogT(cycle=1, date="14/03/2011", id="???", time=15, who="CIDC")})
	public static Map<String, LinkedList<String>> convertPropertiesToMap(Properties prop) throws TSPException{
		Map<String, LinkedList<String>> map = null;
		try {
			map = new HashMap<String, LinkedList<String>>();
			for (Entry<Object, Object> entry : prop.entrySet()) {
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();
				String[] valueArray = value.split(Constants.SEPARATOR_PROPERTIES);
				LinkedList<String> list = new LinkedList<String>(Arrays.asList(valueArray));
				map.put(key, list);
			}			
		} catch (Exception ex) {
			throw new TSPException(ex);
		}
		return map;
	}

}
