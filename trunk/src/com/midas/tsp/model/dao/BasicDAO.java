package com.midas.tsp.model.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.ProcessPhase;
import com.midas.tsp.exceptions.TSPException;

/**
 * Class for persist Properties
 * @author Carlos Ivan Duarte C.
 * @date 22/03/2011
 *
 */
@LogTs({@LogT(cycle=1, date="14/03/2011", id="999", time=81, who="CIDC"), 
		@LogT(cycle=1, date="27/03/2011", id="999", time=81, who="CIDC")})
@LogDs({@LogD(cycleDetected=3, cycleInjected=2, date="27/03/2011", id=145, phaseDetected=ProcessPhase.IMPLEMENTATION, phaseInjected=ProcessPhase.IMPLEMENTATION, removed = true)})		
public class BasicDAO {
	
	private String pathProperties;
	
	/**
	 * Constructor that takes the file path to persist properties
	 * @param pathProperties
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(size=2, type=LocControl.LocType.NEW, who="CIDC", cycle=3),
			@Loc(size=2, type=LocControl.LocType.DELETED, who="CIDC", cycle=3),
			@Loc(size=1, type=LocControl.LocType.DELETED, who="CIDC", cycle=3)
	})
	public BasicDAO(String pathProperties) throws TSPException {
		this.pathProperties = pathProperties;
	}
	
	/**
	 * Persist a properties file
	 * @param properties
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(size=9, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public void save(Properties properties) throws TSPException {
		FileOutputStream fos = null;
		try {			
			fos = new FileOutputStream(pathProperties);
			properties.store(fos, null);
			fos.close();
		}
		catch (IOException ex) {
			throw new TSPException(ex);
		}		
	}	
	
	/** Load a properties file
	 * @return
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(size=12, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public Properties load() throws TSPException {
		Properties prop = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(pathProperties);
			prop = new Properties();
			prop.load(fis);
			fis.close();			
		}
		catch (IOException ex) {
			throw new TSPException(ex);
		}	
		return prop;
	}
}
