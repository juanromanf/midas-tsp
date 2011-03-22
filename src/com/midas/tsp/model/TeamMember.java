package com.midas.tsp.model;

/**
 * @author CIDC
 * @date 22/03/2011
 *
 */

public class TeamMember extends PropertiesTSP {
	/**
	 * Atributo para el rol del integrante de equipo
	 */
	private int rol;

	/**
	 * @param rol
	 */
	public void setRol(int rol) {
		this.rol = rol;
	}

	/**
	 * @return
	 */
	public int getRol() {
		return rol;
	}	
}
