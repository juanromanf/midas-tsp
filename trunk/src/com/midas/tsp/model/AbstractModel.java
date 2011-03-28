package com.midas.tsp.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;

/**
 * This class provides base level functionality for all models, including a
 * support for a property change mechanism (using the PropertyChangeSupport
 * class), as well as a convenience method that other objects can use to reset
 * model state.
 * 
 * @author Carlos Ivan Duarte C.
 * @date 27/03/2011
 */
public abstract class AbstractModel {

	/**
	 * Convenience class that allow others to observe changes to the model
	 * properties
	 */
	protected PropertyChangeSupport propertyChangeSupport;

	/**
	 * Default constructor. Instantiates the PropertyChangeSupport class.
	 */

	@LocControl(value = {
			@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public AbstractModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	/**
	 * Adds a property change listener to the observer list.
	 * 
	 * @param l
	 *            The property change listener
	 */
	@LocControl(value = {
			@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.addPropertyChangeListener(l);
	}

	/**
	 * Removes a property change listener from the observer list.
	 * 
	 * @param l
	 *            The property change listener
	 */
	@LocControl(value = {
			@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	public void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.removePropertyChangeListener(l);
	}

	/**
	 * Fires an event to all registered listeners informing them that a property
	 * in this model has changed.
	 * 
	 * @param propertyName
	 *            The name of the property
	 * @param oldValue
	 *            The previous value of the property before the change
	 * @param newValue
	 *            The new property value after the change
	 */
	@LocControl(value = {
			@Loc(size=1, type=LocControl.LocType.NEW, who="CIDC", cycle=3)
	})
	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}
}