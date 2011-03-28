package com.midas.tsp.model;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;

/**
 * Model of Properties for TSP Application
 * @author Carlos Ivan Duarte C.
 * @date 22/03/2011
 * 
 */
@LogTs({@LogT(cycle=2, date="22/03/2011", id="14", time=72, who="CIDC")})
public class PropertiesTSP {
	/**
	 * Id attribute for properties
	 */
	private String id;
	/**
	 * Description attribute for properties
	 */
	private String description;

	/**
	 * @param id
	 */
	@LocControl(value = {
			@Loc(cycle = 1, size = 1, type = LocControl.LocType.NEW, who = "CIDC")
	})
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	@LocControl(value = {
			@Loc(cycle = 1, size = 1, type = LocControl.LocType.NEW, who = "CIDC")
	})
	public String getId() {
		return id;
	}

	/**
	 * @param description
	 */
	@LocControl(value = {
			@Loc(cycle = 1, size = 1, type = LocControl.LocType.NEW, who = "CIDC")
	})
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	@LocControl(value = {
			@Loc(cycle = 1, size = 1, type = LocControl.LocType.NEW, who = "CIDC")
	})
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	@LocControl(value = {
			@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public String toString() {
		return description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	@LocControl(value = {
			@Loc(size=4, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	@LocControl(value = {
			@Loc(size=13, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertiesTSP other = (PropertiesTSP) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
