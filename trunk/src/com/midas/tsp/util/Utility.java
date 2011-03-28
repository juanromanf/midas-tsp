package com.midas.tsp.util;

import static com.midas.tsp.Constants.SEPARATOR_PROPERTIES;
import static com.midas.tsp.Constants.TASK_PROPERTIES;

import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.PropertiesTSP;
import com.midas.tsp.model.Task;

/**
 * Utility class with transversal functionalities 
 * @author German Dario Camacho S.
 * @date 21/02/2011 Clase con diferentes utilerias para conversiones u
 */
public class Utility {

	public static NumberFormat numberFormatter = NumberFormat.getInstance();

	/**
	 * Convert minutes to hours
	 * 
	 * @param minutes
	 *            minutes to convert
	 * @return Double hours calculated
	 */
	@LocControl(value = {
			@Loc(size=1, type=LocControl.LocType.NEW, who="GDCS", cycle=3)
	})
	public static Double minutes2hours(Integer minutes) {
		return minutes / 60.0;
	}
	
	/**
	 * Convert a PropertiesTSP to Properties of API
	 * @param propertiesTSP
	 * @return
	 * @throws TSPException
	 */
	@LogTs({@LogT(cycle=2, date="17/04/2011", id="???", time=20, who="CIDC")})
	@LocControl(value = {
			@Loc(size=17, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public static Properties convertToProperties(List<? extends PropertiesTSP> propertiesTSP) throws TSPException {
		Properties properties = null;
		try {
			properties = new Properties();
			for (PropertiesTSP propTSP : propertiesTSP) {
				if (propTSP instanceof Task) {
					Task task = (Task)propTSP;
					properties.setProperty(task.getId(), 
									 task.getDescription() + SEPARATOR_PROPERTIES + 
									 task.getDuration() + SEPARATOR_PROPERTIES + 
									 task.getSize() + SEPARATOR_PROPERTIES + 
									 task.getCycle());					
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
	
	/** Convert a Properties of API to PropertiesTSP
	 * @param file
	 * @return
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(size=35, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	@LogTs({@LogT(cycle=2, date="17/04/2011", id="???", time=30, who="CIDC")})
	public static List<? extends PropertiesTSP> convertoToPropertiesTSP(String path) throws TSPException {
		List<PropertiesTSP> propertiesTSP = null;
		Properties properties = null;
		File file = null;
		FileInputStream stream = null;
		try {
			properties = new Properties();
			file = new File(path);
			stream = new FileInputStream(file);
			properties.load(stream);
			stream.close();
			propertiesTSP = new LinkedList<PropertiesTSP>();
			for (Entry<Object, Object> entry : properties.entrySet()) {
				String key = (String)entry.getKey();
				String[] attributes = ((String)entry.getValue()).split("\\" + SEPARATOR_PROPERTIES);
				if (file.getName().equals(TASK_PROPERTIES)) {
					Task task = new Task();
					task.setId(key);
					task.setDescription(attributes[0]);
					task.setSize(Integer.parseInt(attributes[1]));
					task.setDuration(Integer.parseInt(attributes[2]));
					task.setCycle(Integer.parseInt(attributes[3]));					
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
	
	/**
	 * Evaluates an object to verify that the reference is not null, if null a excepetion is raised
	 * @param object The reference to be tested
	 * @param where Description of the place in wich the validatoins is done
	 * @throws TSPException Exception with a message about the null reference detected
	 */
	@LocControl({
		@Loc(cycle = 3, size = 3, type = LocControl.LocType.NEW, who = "GDCS"), })
	public static void notNullValidation(Object object, String where)
			throws TSPException {
		if (object == null) {
			throw new TSPException("Error, processing a null object when "
					+ where);
		}

	}

}
