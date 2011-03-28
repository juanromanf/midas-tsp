package com.midas.tsp.model;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;

/**
 * @author Carlos Ivan Duarte C.
 * @date 22/03/2011
 *
 */
@LogTs({@LogT(cycle=2, date="22/03/2011", id="16", time=1, who="CIDC")})
public class TeamMember extends PropertiesTSP {
	/**
	 * Attribute to the role of team member
	 */
	private int rol;

	/**
	 * @param rol
	 */
	@LocControl(value = {
			@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=2)
	})
	public void setRol(int rol) {
		this.rol = rol;
	}

	/**
	 * @return
	 */
	@LocControl(value = {
			@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=2)
	})
	public int getRol() {
		return rol;
	}	
}
