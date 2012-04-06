package husacct.define.task;

import husacct.common.dto.CategoryDTO;
import husacct.common.dto.RuleTypeDTO;
import husacct.define.presentation.helper.DataHelper;
import husacct.define.presentation.jframe.JFrameAppliedRules;
import husacct.validate.ValidateServiceStub;
import husacct.define.presentation.tables.JTableException;
import husacct.define.presentation.tables.JTableTableModel;
import husacct.define.presentation.utils.Log;
import husacct.define.presentation.utils.UiDialogs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;


public class AppliedRulesController extends PopUpController implements KeyListener {

	private JFrameAppliedRules jframe;
	private long appliedrule_id;
	private ValidateServiceStub validateService;

	public AppliedRulesController(int layer_id, long appliedrule_id) {
		Log.i(this, "constructor(" + layer_id + ", " + appliedrule_id + ")");
		setLayerID(layer_id);
		this.appliedrule_id = appliedrule_id;
		validateService = new ValidateServiceStub();
	}

	@Override
	public void initUi() throws Exception {
		Log.i(this, "initUi()");
		jframe = new JFrameAppliedRules();

		// Change view of jframe conforms the action
		loadSelectBoxes();
		fillRuleTypeComboBox();
		if (getAction().equals(SoftwareUnitController.ACTION_NEW)) {
			jframe.jButtonSave.setText("Create");
			jframe.setTitle("New applied rule");
		} else if (getAction().equals(SoftwareUnitController.ACTION_EDIT)) {
			jframe.jButtonSave.setText("Save");
			jframe.setTitle("Edit applied rule");
			if (appliedrule_id != -1L) {
				// Load name & type
				//jframe.jComboBoxToLayer.setSelectedItem(defineDomainService.getLayerName(defineDomainService.getAppliedRuleToLayer(getLayerID(), appliedrule_id)));
				//jframe.jCheckBoxEnabled.setSelected(defineDomainService.getAppliedRuleIsEnabled(getLayerID(), appliedrule_id));

				// Load table with exceptions
				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				ArrayList<Long> exceptions = defineDomainService.getAppliedRuleExceptions(getLayerID(), appliedrule_id);
				for (long exception_id : exceptions) {
					DataHelper datahelper = new DataHelper();
					datahelper.setId(exception_id);
					datahelper.setValue(defineDomainService.getAppliedruleExceptionName(getLayerID(), appliedrule_id, exception_id));

					Object[] row = { datahelper, defineDomainService.getAppliedruleExceptionType(getLayerID(), appliedrule_id, exception_id) };
					tablemodel.addRow(row);
				}
			}
		}

		jframe.jButtonAddExceptionRow.addActionListener(this);
		jframe.jButtonRemoveExceptionRow.addActionListener(this);
		jframe.jButtonSave.addActionListener(this);
		jframe.jButtonCancel.addActionListener(this);
		jframe.addKeyListener(this);

		// Set the visibility of the jframe to true so the jframe is now visible
		UiDialogs.showOnScreen(0, jframe);

		jframe.setVisible(true);
	}

	private void fillRuleTypeComboBox() {
		CategoryDTO[] categories = validateService.getCategories();
		RuleTypeDTO[] ruleTypes = categories[0].getRuleTypes();
		ArrayList<String> ruleTypeKeys = new ArrayList<String>();
		ArrayList<String> ruleTypeValues = new ArrayList<String>();
		
		//foreach ruletype set ruletypekays array
//		ruleTypeKeys = ruleTypes
				
		//foreach ruletypekey get resourcebundle value
		jframe.jComboBoxAppliedRule.setModel((String[])ruleTypeKeys.toArray(), (String[])ruleTypeValues.toArray());
	}

	public void loadSelectBoxes() {
		// loading of all layers
		ArrayList<Integer> layers = defineDomainService.getLayerLevels();

		if (layers != null) {
			// Remove the current layer from the list
			layers.remove(getLayerID()-1);

			ArrayList<DataHelper> layernames = new ArrayList<DataHelper>();
			for (int layer_id : layers) {
				DataHelper datahelper = new DataHelper();
				datahelper.setId(layer_id);
				datahelper.setValue("" + defineDomainService.getLayerNameByLevel(layer_id));
				layernames.add(datahelper);
			}

			DefaultComboBoxModel jComboBoxModel = new DefaultComboBoxModel(layernames.toArray());
			jframe.jComboBoxModuleTo.setModel(jComboBoxModel);
		}
	}

	@Override
	public void save() {
		try {
			if (getAction().equals(PopUpController.ACTION_NEW)) {
				String ruleTypeValue = jframe.jComboBoxAppliedRule.getSelectedItem().toString();
				String ruleTypeKey = ruleTypeValue;
				String description = "";
				long moduleFromId = getLayerID();
				long moduleToId = ((DataHelper) jframe.jComboBoxModuleTo.getSelectedItem()).getIntId();
				appliedrule_id = defineDomainService.addAppliedRule(ruleTypeKey, description, moduleFromId, moduleToId);
				//appliedrule_id = defineDomainService.addAppliedRule(getLayerID(), ((DataHelper) jframe.jComboBoxModuleTo.getSelectedItem()).getIntId(), jframe.jComboBoxAppliedRule.getSelectedItem().toString());
				defineDomainService.setAppliedRuleIsEnabled(appliedrule_id, jframe.jCheckBoxEnabled.isSelected());
				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				int tablerows = tablemodel.getRowCount();
				for (int i = 0; i < tablerows; i++) {
//					defineDomainService.addExceptionToAppliedRule(getLayerID(), appliedrule_id, tablemodel.getValueAt(i, 0).toString(), tablemodel.getValueAt(i, 1).toString());
				}
			} else if (getAction().equals(PopUpController.ACTION_EDIT)) {
				//defineDomainService.setAppliedRuleToLayer(getLayerID(), appliedrule_id, ((DataHelper) jframe.jComboBoxToLayer.getSelectedItem()).getIntId());
				//defineDomainService.setAppliedRuleRuleType(getLayerID(), appliedrule_id, jframe.jComboBoxAppliedRule.getSelectedItem().toString());
				defineDomainService.setAppliedRuleIsEnabled(appliedrule_id, jframe.jCheckBoxEnabled.isSelected());

				defineDomainService.removeAppliedRuleExceptions(appliedrule_id);

				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				int tablerows = tablemodel.getRowCount();
				for (int i = 0; i < tablerows; i++) {
//					defineDomainService.addExceptionToAppliedRule(appliedrule_id,jframe.jComboBoxAppliedRule.getSelectedItemKey(),"", tablemodel.getValueAt(i, 0).toString(), tablemodel.getValueAt(i, 1).toString());
				}
			}
			jframe.dispose();
			pokeObservers();
		} catch (Exception e) {
			UiDialogs.errorDialog(jframe, e.getMessage(), "Error");
		}

	}

	/**
	 * Add a new empty row to the exception table
	 */
	@Override
	public void addExceptionRow() {
		JTableException table = jframe.jTableException;
		JTableTableModel tablemodel = (JTableTableModel) table.getModel();

		Object[] emptyrow = { "", "" };
		tablemodel.addRow(emptyrow);
	}

	/**
	 * Remove the selected row from the exception table
	 */
	@Override
	public void removeExceptionRow() {
		JTableException table = jframe.jTableException;
		int selectedrow = table.getSelectedRow();
		if (selectedrow == -1) {
			UiDialogs.errorDialog(jframe, "Select a table row", "Error");
		} else {
			JTableTableModel tablemodel = (JTableTableModel) table.getModel();
			tablemodel.removeRow(selectedrow);
		}
	}

	public void actionPerformed(ActionEvent action) {
		Log.i(this, "actionPerformed()");
		if (action.getSource() == jframe.jButtonAddExceptionRow) {
			addExceptionRow();
		} else if (action.getSource() == jframe.jButtonRemoveExceptionRow) {
			removeExceptionRow();
		} else if (action.getSource() == jframe.jButtonSave) {
			save();
		} else if (action.getSource() == jframe.jButtonCancel) {
			jframe.dispose();
		} else {
			Log.i(this, "actionPerformed(" + action + ") - unknown button event");
		}
	}

	public void keyPressed(KeyEvent e) {
		// Ignore
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			jframe.dispose();
		}
	}

	public void keyTyped(KeyEvent e) {
		// Ignore
	}

}
