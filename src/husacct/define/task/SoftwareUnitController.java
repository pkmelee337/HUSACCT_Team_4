package husacct.define.task;

import husacct.common.AnalyseServiceStub;
import husacct.common.dto.AnalysedModuleDTO;
import husacct.define.domain.DefineDomainService;
import husacct.define.domain.domainOLD.SoftwareUnitDefinition;
import husacct.define.presentation.helper.DataHelper;
import husacct.define.presentation.jframe.JFrameSoftwareUnit;
import husacct.define.presentation.tables.JTableException;
import husacct.define.presentation.tables.JTableTableModel;
import husacct.define.presentation.utils.Log;
import husacct.define.presentation.utils.UiDialogs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class SoftwareUnitController extends PopUpController implements KeyListener {

	private JFrameSoftwareUnit jframe;
	private String softwareunit_id;
	private static ArrayList<SoftwareUnitDefinition> softwareUnits = new ArrayList<SoftwareUnitDefinition>();

	public SoftwareUnitController(long layerId, String softwareunit_id) {
		Log.i(this, "constructor(" + layerId + ", " + softwareunit_id + ")");
		setLayerID(layerId);
		this.softwareunit_id = softwareunit_id;
		this.fillSoftwareDefinitionUnits();
		
		if(softwareUnits.isEmpty()) {
			this.fillSoftwareDefinitionUnits();
		}
	}
	
	public void fillSoftwareDefinitionUnits() {
		softwareUnits = new ArrayList<SoftwareUnitDefinition>();
		AnalysedModuleDTO[] modules = this.getAnalyzedModules();
		for(AnalysedModuleDTO module : modules) {
			// TODO:: add different types
			SoftwareUnitDefinition softwareUnit = new SoftwareUnitDefinition(module.name, SoftwareUnitDefinition.Type.PACKAGE);
			softwareUnits.add(softwareUnit);
			//DefineDomainService.addUniqueName(softwareUnit.getName(), softwareUnit);
		}
	}
	
	public AnalysedModuleDTO[] getAnalyzedModules() {
		AnalyseServiceStub analyzeService = new AnalyseServiceStub();
		AnalysedModuleDTO[] modules = analyzeService.getModules();
		return modules;
	}

	@Override
	public void initUi() throws Exception {
		Log.i(this, "initUi()");
		
		String[] softwareUnitStrings = new String[softwareUnits.size()-1];
		int counter = 0;
		for(SoftwareUnitDefinition softwareUnit : softwareUnits) {
			softwareUnitStrings[counter] = softwareUnit.getName();
			counter++;
		}
		jframe = new JFrameSoftwareUnit(softwareUnitStrings);

		// Change view of jframe conforms the action
		if (getAction().equals(PopUpController.ACTION_NEW)) {
			jframe.jButtonSave.setText("Save");
			jframe.setTitle("Select software unit");
		} else if (getAction().equals(PopUpController.ACTION_EDIT)) {
			jframe.jButtonSave.setText("Save");
			jframe.setTitle("Edit software unit");
			if (softwareunit_id != "") {
				// Load name & type
				jframe.jComboBoxSoftwareUnit.setSelectedItem(softwareunit_id);

				
				
				// NIET NODIG IN MIJN OGEN
				// Load table with exceptions
//				JTableException table = jframe.jTableException;
//				JTableTableModel tablemodel = (JTableTableModel) table.getModel();
//
//				ArrayList<Long> exceptions = definitionService.getSoftwareUnitExceptions(getLayerID(), softwareunit_id);
//				for (long exception_id : exceptions) {
//					DataHelper datahelper = new DataHelper();
//					datahelper.setId(exception_id);
//					datahelper.setValue(definitionService.getSoftwareUnitExceptionName(getLayerID(), softwareunit_id, exception_id));
//
//					Object[] row = { datahelper, definitionService.getSoftwareUnitExceptionType(getLayerID(), softwareunit_id, exception_id) };
//					tablemodel.addRow(row);
//				}
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
				//DefineDomainService.setSoftwareUnitLayer(jframe.jComboBoxSoftwareUnit.getSelectedItem().toString(), getLayerID());
				
				// Niet Nodig naar mijn idee!
//				JTableException table = jframe.jTableException;
//				JTableTableModel tablemodel = (JTableTableModel) table.getModel();
//
//				int tablerows = tablemodel.getRowCount();
//				for (int i = 0; i < tablerows; i++) {
//					definitionService.newSoftwareUnitException(getLayerID(), softwareunit_id, tablemodel.getValueAt(i, 0).toString(), tablemodel.getValueAt(i, 1).toString());
//				}
			} else if (getAction().equals(PopUpController.ACTION_EDIT)) {
				// EDIT nog maken
//				definitionService.setSoftwareUnitName(getLayerID(), softwareunit_id, jframe.jTextFieldSoftwareUnitName.getText());
//				definitionService.setSoftwareUnitType(getLayerID(), softwareunit_id, jframe.jComboBoxSoftwareUnitType.getSelectedItem().toString());
//
//				definitionService.removeSoftwareUnitExceptions(getLayerID(), softwareunit_id);
//
//				JTableException table = jframe.jTableException;
//				JTableTableModel tablemodel = (JTableTableModel) table.getModel();
//
//				int tablerows = tablemodel.getRowCount();
//				for (int i = 0; i < tablerows; i++) {
//					definitionService.newSoftwareUnitException(getLayerID(), softwareunit_id, tablemodel.getValueAt(i, 0).toString(), tablemodel.getValueAt(i, 1).toString());
//				}
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
