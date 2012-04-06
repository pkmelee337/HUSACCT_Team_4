package husacct.define.task;

import husacct.define.domain.DefineDomainService;
import husacct.define.domain.DefineDomainServiceOld2011;

import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;

public abstract class PopUpController extends Observable implements ActionListener {
	public static final String ACTION_NEW = "NEW";
	public static final String ACTION_EDIT = "EDIT";
	public ResourceBundle resourceBundle = ResourceBundle.getBundle("husacct/define/presentation/gui", new Locale("en", "GB"));

	//protected DefineDomainServiceOld2011 definitionServiceOLD = DefineDomainServiceOld2011.getInstance();
	protected DefineDomainService defineDomainService = DefineDomainService.getInstance();
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
