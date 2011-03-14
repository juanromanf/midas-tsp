package com.midas.tsp.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.midas.tsp.exceptions.TSPException;

public class BasicDAO {

	private FileInputStream input;
	private FileOutputStream output;
	
	public BasicDAO(String pathProperties) throws TSPException {
		try {
			input = new FileInputStream(pathProperties);
			output = new FileOutputStream(pathProperties);
		}
		catch (FileNotFoundException ex) {
			throw new TSPException(ex);
		}
	}
	
	public void save(Properties properties) throws TSPException {
		try {
			properties.store(output, null);
		}
		catch (IOException ex) {
			throw new TSPException(ex);
		}
	}	
	
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
