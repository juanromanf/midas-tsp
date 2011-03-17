package com.midas.tsp.util;

import static com.midas.tsp.Constants.SEPARATOR_ID;
import static com.midas.tsp.Constants.SEPARATOR_PROPERTIES;
import static com.midas.tsp.Constants.TASK_PROPERTIES;

import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.PropertiesTSP;
import com.midas.tsp.model.Task;

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
	 *//*
	@LogTs({@LogT(cycle=1, date="14/03/2011", id="999", time=20, who="CIDC")})
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
	
	*//**
	 * Permite convertir un Properties en un mapa
	 * @param prop
	 * @return
	 * @throws TSPException
	 *//*
	@LogTs({@LogT(cycle=1, date="14/03/2011", id="999", time=15, who="CIDC")})
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
	}*/
	
	@LogTs({@LogT(cycle=2, date="17/04/2011", id="???", time=20, who="CIDC")})
	public static Properties convertToProperties(List<? extends PropertiesTSP> propertiesTSP) throws TSPException {
		Properties properties = null;
		try {
			properties = new Properties();
			for (PropertiesTSP propTSP : propertiesTSP) {
				if (propTSP instanceof Task) {
					Task task = (Task)propTSP;
					properties.setProperty(task.getCycle() + SEPARATOR_ID + task.getId(), 
									 task.getDescription() + SEPARATOR_PROPERTIES + 
									 task.getDuration() + SEPARATOR_PROPERTIES + 
									 task.getSize());					
				}
				else {
					properties.setProperty(propTSP.getId(), propTSP.getDescription());
				}
			}
		}
		catch (Exception ex) {
			throw new TSPException(ex);
		}
		return properties;
	}
	
	@LogTs({@LogT(cycle=2, date="17/04/2011", id="???", time=30, who="CIDC")})
	public static List<? extends PropertiesTSP> convertoToPropertiesTSP(File file) throws TSPException {
		List<PropertiesTSP> propertiesTSP = null;
		Properties properties = null;
		try {
			properties = new Properties();
			FileInputStream stream = new FileInputStream(file);
			properties.load(stream);
			propertiesTSP = new LinkedList<PropertiesTSP>();
			for (Entry<Object, Object> entry : properties.entrySet()) {
				String key = (String)entry.getKey();
				String[] attributes = ((String)entry.getValue()).split(SEPARATOR_PROPERTIES);
				if (file.getName().equals(TASK_PROPERTIES)) {
					String[] id = key.split(SEPARATOR_ID);
					Task task = new Task();
					task.setCycle(Integer.parseInt(id[0]));
					task.setId(id[1]);
					task.setDescription(attributes[0]);
					task.setSize(Integer.parseInt(attributes[1]));
					task.setDuration(Integer.parseInt(attributes[2]));
					propertiesTSP.add(task);
				}			
				else {
					PropertiesTSP propTSP = new PropertiesTSP();
					propTSP.setId(key);
					propTSP.setDescription(attributes[0]);
					propertiesTSP.add(propTSP);
				}
			}		
		}
		catch (Exception ex) {
			throw new TSPException(ex);
		}
		return propertiesTSP;
	}

}
