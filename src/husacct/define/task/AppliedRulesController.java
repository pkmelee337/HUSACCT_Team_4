package husacct.define.task;

import husacct.define.presentation.helper.DataHelper;
import husacct.define.presentation.jframe.JFrameAppliedRules;
import husacct.define.presentation.tables.JTableException;
import husacct.define.presentation.tables.JTableTableModel;
import husacct.define.presentation.utils.Log;
import husacct.define.presentation.utils.UiDialogs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;


public class AppliedRulesController extends PopUpController implements KeyListener {

	private JFrameAppliedRules jframe;
	private long appliedrule_id;

	public AppliedRulesController(int layer_id, long appliedrule_id) {
		Log.i(this, "constructor(" + layer_id + ", " + appliedrule_id + ")");
		setLayerID(layer_id);
		this.appliedrule_id = appliedrule_id;
	}

	@Override
	public void initUi() throws Exception {
		Log.i(this, "initUi()");
		jframe = new JFrameAppliedRules();

		// Change view of jframe conforms the action
		loadSelectBoxes();
		if (getAction().equals(SoftwareUnitController.ACTION_NEW)) {
			jframe.jButtonSave.setText("Create");
			jframe.setTitle("New applied rule");
		} else if (getAction().equals(SoftwareUnitController.ACTION_EDIT)) {
			jframe.jButtonSave.setText("Save");
			jframe.setTitle("Edit applied rule");
			if (appliedrule_id != -1L) {
				// Load name & type
				jframe.jComboBoxAppliedRule.setSelectedItem(definitionServiceOLD.getAppliedRuleRuleType(getLayerID(), appliedrule_id));
				jframe.jComboBoxToLayer.setSelectedItem(definitionServiceOLD.getLayerName(definitionServiceOLD.getAppliedRuleToLayer(getLayerID(), appliedrule_id)));
				jframe.jCheckBoxEnabled.setSelected(definitionServiceOLD.getAppliedRuleIsEnabled(getLayerID(), appliedrule_id));

				// Load table with exceptions
				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				ArrayList<Long> exceptions = definitionServiceOLD.getAppliedRuleExceptions(getLayerID(), appliedrule_id);
				for (long exception_id : exceptions) {
					DataHelper datahelper = new DataHelper();
					datahelper.setId(exception_id);
					datahelper.setValue(definitionServiceOLD.getAppliedruleExceptionName(getLayerID(), appliedrule_id, exception_id));

					Object[] row = { datahelper, definitionServiceOLD.getAppliedruleExceptionType(getLayerID(), appliedrule_id, exception_id) };
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

	public void loadSelectBoxes() {
		// Laden van alle layers
		ArrayList<Integer> layers = definitionServiceOLD.getLayers();

		if (layers != null) {
			// Remove the current layer from the list
			layers.remove(getLayerID());

			ArrayList<DataHelper> layernames = new ArrayList<DataHelper>();
			for (int layer_id : layers) {
				DataHelper datahelper = new DataHelper();
				datahelper.setId(layer_id);
				datahelper.setValue("" + definitionServiceOLD.getLayerName(layer_id));
				layernames.add(datahelper);
			}

			DefaultComboBoxModel jComboBoxModel = new DefaultComboBoxModel(layernames.toArray());
			jframe.jComboBoxToLayer.setModel(jComboBoxModel);
		}
	}

	@Override
	public void save() {
		try {
			if (getAction().equals(PopUpController.ACTION_NEW)) {
				appliedrule_id = definitionServiceOLD.newAppliedRule(getLayerID(), ((DataHelper) jframe.jComboBoxToLayer.getSelectedItem()).getIntId(), jframe.jComboBoxAppliedRule.getSelectedItem().toString());
				definitionServiceOLD.setAppliedRuleIsEnabled(getLayerID(), appliedrule_id, jframe.jCheckBoxEnabled.isSelected());
				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				int tablerows = tablemodel.getRowCount();
				for (int i = 0; i < tablerows; i++) {
					definitionServiceOLD.newAppliedRuleException(getLayerID(), appliedrule_id, tablemodel.getValueAt(i, 0).toString(), tablemodel.getValueAt(i, 1).toString());
				}
			} else if (getAction().equals(PopUpController.ACTION_EDIT)) {
				definitionServiceOLD.setAppliedRuleToLayer(getLayerID(), appliedrule_id, ((DataHelper) jframe.jComboBoxToLayer.getSelectedItem()).getIntId());
				definitionServiceOLD.setAppliedRuleRuleType(getLayerID(), appliedrule_id, jframe.jComboBoxAppliedRule.getSelectedItem().toString());
				definitionServiceOLD.setAppliedRuleIsEnabled(getLayerID(), appliedrule_id, jframe.jCheckBoxEnabled.isSelected());

				definitionServiceOLD.removeAppliedRuleExceptions(getLayerID(), appliedrule_id);

				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				int tablerows = tablemodel.getRowCount();
				for (int i = 0; i < tablerows; i++) {
					definitionServiceOLD.newAppliedRuleException(getLayerID(), appliedrule_id, tablemodel.getValueAt(i, 0).toString(), tablemodel.getValueAt(i, 1).toString());
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
