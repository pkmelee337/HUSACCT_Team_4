package husacct.define.domain.define_domain.applogic.controller;

import husacct.define.domain.define_domain.applogic.view.helper.DataHelper;
import husacct.define.domain.define_domain.applogic.view.jframe.JFrameSoftwareUnit;
import husacct.define.domain.define_domain.applogic.view.tables.JTableException;
import husacct.define.domain.define_domain.applogic.view.tables.JTableTableModel;
import husacct.define.domain.define_domain.applogic.view.utils.Log;
import husacct.define.domain.define_domain.applogic.view.utils.UiDialogs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class SoftwareUnitController extends PopUpController implements KeyListener {

	private JFrameSoftwareUnit jframe;
	private long softwareunit_id;

	public SoftwareUnitController(int layer_id, long softwareunit_id) {
		Log.i(this, "constructor(" + layer_id + ", " + softwareunit_id + ")");
		setLayerID(layer_id);
		this.softwareunit_id = softwareunit_id;
	}

	@Override
	public void initUi() throws Exception {
		Log.i(this, "initUi()");
		jframe = new JFrameSoftwareUnit();

		// Change view of jframe conforms the action
		if (getAction().equals(PopUpController.ACTION_NEW)) {
			jframe.jButtonSave.setText("Create");
			jframe.setTitle("New software unit");
		} else if (getAction().equals(PopUpController.ACTION_EDIT)) {
			jframe.jButtonSave.setText("Save");
			jframe.setTitle("Edit software unit");
			if (softwareunit_id != -1L) {
				// Load name & type
				jframe.jTextFieldSoftwareUnitName.setText(definitionService.getSoftwareUnitName(getLayerID(), softwareunit_id));
				jframe.jComboBoxSoftwareUnitType.setSelectedItem(definitionService.getSoftwareUnitType(getLayerID(), softwareunit_id));

				// Load table with exceptions
				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				ArrayList<Long> exceptions = definitionService.getSoftwareUnitExceptions(getLayerID(), softwareunit_id);
				for (long exception_id : exceptions) {
					DataHelper datahelper = new DataHelper();
					datahelper.setId(exception_id);
					datahelper.setValue(definitionService.getSoftwareUnitExceptionName(getLayerID(), softwareunit_id, exception_id));

					Object[] row = { datahelper, definitionService.getSoftwareUnitExceptionType(getLayerID(), softwareunit_id, exception_id) };
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

	@Override
	public void save() {
		try {
			if (getAction().equals(PopUpController.ACTION_NEW)) {
				softwareunit_id = definitionService.newSoftwareUnit(getLayerID(), jframe.jTextFieldSoftwareUnitName.getText(), jframe.jComboBoxSoftwareUnitType.getSelectedItem().toString());

				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				int tablerows = tablemodel.getRowCount();
				for (int i = 0; i < tablerows; i++) {
					definitionService.newSoftwareUnitException(getLayerID(), softwareunit_id, tablemodel.getValueAt(i, 0).toString(), tablemodel.getValueAt(i, 1).toString());
				}
			} else if (getAction().equals(PopUpController.ACTION_EDIT)) {
				definitionService.setSoftwareUnitName(getLayerID(), softwareunit_id, jframe.jTextFieldSoftwareUnitName.getText());
				definitionService.setSoftwareUnitType(getLayerID(), softwareunit_id, jframe.jComboBoxSoftwareUnitType.getSelectedItem().toString());

				definitionService.removeSoftwareUnitExceptions(getLayerID(), softwareunit_id);

				JTableException table = jframe.jTableException;
				JTableTableModel tablemodel = (JTableTableModel) table.getModel();

				int tablerows = tablemodel.getRowCount();
				for (int i = 0; i < tablerows; i++) {
					definitionService.newSoftwareUnitException(getLayerID(), softwareunit_id, tablemodel.getValueAt(i, 0).toString(), tablemodel.getValueAt(i, 1).toString());
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

	public void keyPressed(KeyEvent arg0) {
		// Ignore
	}

	public void keyReleased(KeyEvent arg0) {
		Log.i(this, "keyreleased");
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			jframe.dispose();
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// Ignore
	}
}
