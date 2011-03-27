package com.midas.tsp.model;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.LogT;

/**
 * @author CIDC
 * @date 22/03/2011
 *
 */
@LogTs({@LogT(cycle=2, date="22/03/2011", id="14", time=3, who="CIDC")})
public class PropertiesTSP {
	/**
	 * Atributo para el id del properties
	 */
	private String id;
	/**
	 * Atributo para la descripci—n del properties
	 */
	private String description;

	/**
	 * @param id
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public String getId() {
		return id;
	}

	/**
	 * @param description
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	@Loc(cycle=1,size=1, type=LocControl.LocType.NEW, who="CIDC")
	public String getDescription() {
		return description;
	}
}