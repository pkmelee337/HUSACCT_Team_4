package husacct.define.domain.define_domain.applogic.controller;

import husacct.define.domain.define_domain.DefineServiceOld2011;

import java.awt.event.ActionListener;
import java.util.Observable;

public abstract class PopUpController extends Observable implements ActionListener {
	public static final String ACTION_NEW = "NEW";
	public static final String ACTION_EDIT = "EDIT";

	protected DefineServiceOld2011 definitionService = DefineServiceOld2011.getInstance();
	protected String action = PopUpController.ACTION_NEW;
	protected int layer_id;

	public abstract void initUi() throws Exception;

	public abstract void save();

	public abstract void addExceptionRow();

	public abstract void removeExceptionRow();

	/**
	 * Use this function to notify the definitioncontroller that there is a change
	 */
	protected void pokeObservers() {
		setChanged();
		notifyObservers();
	}

	public void setLayerID(int layer) {
		this.layer_id = layer;
	}

	protected int getLayerID() {
		return layer_id;
	}

	public void setAction(String action) {
		if (action.equals(PopUpController.ACTION_EDIT) || action.equals(PopUpController.ACTION_NEW)) {
			this.action = action;
		}
	}

	protected String getAction() {
		return action;
	}

}
