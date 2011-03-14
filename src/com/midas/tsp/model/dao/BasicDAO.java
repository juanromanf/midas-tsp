package com.midas.tsp.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.exceptions.TSPException;

/**
 * Clase que permite persistir un properties
 * @author carlos.duarte
 *
 */
@LogTs({@LogT(cycle=1, date="14/03/2011", id="???", time=81, who="CIDC")})
public class BasicDAO {

	private FileInputStream input;
	private FileOutputStream output;
	
	/**
	 * Constructor que recibe el path del archivo de propiedades a persistir
	 * @param pathProperties
	 * @throws TSPException
	 */
	public BasicDAO(String pathProperties) throws TSPException {
		try {
			input = new FileInputStream(pathProperties);
			output = new FileOutputStream(pathProperties);
		}
		catch (FileNotFoundException ex) {
			throw new TSPException(ex);
		}
	}
	
	/**
	 * Permite persistir un archivo de propiedades
	 * @param properties
	 * @throws TSPException
	 */
	public void save(Properties properties) throws TSPException {
		try {
			properties.store(output, null);
		}
		catch (IOException ex) {
			throw new TSPException(ex);
		}
	}	
	
	/** Permite cargar un archivo de propiedadess
	 * @return
	 * @throws TSPException
	 */
	public Properties load() throws TSPException {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(input);
		}
		catch (IOException ex) {
			throw new TSPException(ex);
		}	
		return prop;
	}
}
