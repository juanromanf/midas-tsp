package com.midas.tsp.model;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;

/**
 * @author CIDC
 * @date 22/03/2011
 *
 */
@LogTs({@LogT(cycle=2, date="22/03/2011", id="49", time=1, who="CIDC")})
public class TeamMember extends PropertiesTSP {
	/**
	 * Atributo para el rol del integrante de equipo
	 */
	private int rol;

	/**
	 * @param rol
	 */
	@Loc(size=1, type=LocControl.LocType.NUEVA, who="CIDC")
	public void setRol(int rol) {
		this.rol = rol;
	}

	/**
	 * @return
	 */
	@Loc(size=1, type=LocControl.LocType.NUEVA, who="CIDC")
	public int getRol() {
		return rol;
	}	
}
