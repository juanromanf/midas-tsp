package com.midas.tsp.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.AbstractModel;
import com.midas.tsp.view.AbstractViewPanel;

/**
 * Contains the methods that define each Controller
 * 
 * @author Carlos Ivan Duarte C.
 * @date 27/03/2011
 */
public abstract class AbstractController {

	/**
	 * Views registered in the controller
	 */
	private ArrayList<AbstractViewPanel> registeredViews;
	/**
	 * Models registered in the controller
	 */
	private ArrayList<AbstractModel> registeredModels;

	/** Creates a new instance of Controller */
	@LocControl(value = {
			@Loc(cycle = 3, size = 2, type = LocControl.LocType.NEW, who = "CIDC")})
	public AbstractController() {
		registeredViews = new ArrayList<AbstractViewPanel>();
		registeredModels = new ArrayList<AbstractModel>();
	}

	/**
	 * Binds a view to this controller. The controller will propogate all model
	 * property changes to each view for consideration.
	 * 
	 * @param view
	 *            The view to be added
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "CIDC")})
	public void addView(AbstractViewPanel view) {
		registeredViews.add(view);
	}

	/**
	 * Unbinds a view from this controller.
	 * 
	 * @param view
	 *            The view to be removed
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "CIDC")})
	public void removeView(AbstractViewPanel view) {
		registeredViews.remove(view);
	}

	/**
	 * Convienence method that subclasses can call upon to fire off property
	 * changes back to the models. This method used reflection to inspect each
	 * of the model classes to determine if it is the owner of the property in
	 * question. If it isn't, a NoSuchMethodException is throws (which the
	 * method ignores).
	 * 
	 * @param propertyName
	 *            The name of the property
	 * @param newValue
	 *            An object that represents the new value of the property.
	 */
	@LocControl(value = { @Loc(cycle = 3, size = 13, type = LocControl.LocType.NEW, who = "CIDC") })
	protected void setModelProperty(String propertyName, Object newValue)
			throws TSPException {
		for (AbstractModel model : registeredModels) {
			try {
				Method method = model.getClass().getMethod(
						"set" + propertyName,
						new Class[] { newValue.getClass() });
				method.invoke(model, newValue);

			} catch (Exception ex) {
				throw new TSPException(ex);
			}
		}
	}
}
