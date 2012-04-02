package DefineDomain.applogic.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DefineDomain.applogic.DefinitionService;
import DefineDomain.applogic.view.ApplicationController;
import DefineDomain.applogic.view.helper.DataHelper;
import DefineDomain.applogic.view.helper.XmlFileFilter;
import DefineDomain.applogic.view.jpanel.DefinitionJPanel;
import DefineDomain.applogic.view.tables.JTableTableModel;
import DefineDomain.applogic.view.utils.JPanelStatus;
import DefineDomain.applogic.view.utils.Log;
import DefineDomain.applogic.view.utils.UiDialogs;

public class DefinitionController implements ActionListener, ListSelectionListener, KeyListener, Observer {

	private DefinitionJPanel definitionJPanel;
	// create own service
	private DefinitionService definitionService;
	private ApplicationController mainController;

	public DefinitionController(ApplicationController mc) {
		Log.i(this, "constructor()");
		mainController = mc;
		definitionJPanel = new DefinitionJPanel();
		definitionService = DefinitionService.getInstance();
	}

	/**
	 * Init the user interface for creating/editting the definition.
	 * 
	 * @return JPanel The jpanel
	 */
	public JPanel initUi() {
		Log.i(this, "initUi()");

		updateLayerList();

		// Set actionlisteners to buttons, lists etc.
		definitionJPanel.jListLayers.addListSelectionListener(this);
		definitionJPanel.jButtonNewLayer.addActionListener(this);
		definitionJPanel.jButtonRemoveLayer.addActionListener(this);
		definitionJPanel.jButtonMoveLayerUp.addActionListener(this);
		definitionJPanel.jButtonMoveLayerDown.addActionListener(this);

		definitionJPanel.jTextFieldLayerName.addKeyListener(this);
		definitionJPanel.jTextAreaLayerDescription.addKeyListener(this);
		definitionJPanel.jCheckBoxAccess.addActionListener(this);

		definitionJPanel.jButtonAddComponentToLayer.addActionListener(this);
		definitionJPanel.jButtonEditComponentFromLayer.addActionListener(this);
		definitionJPanel.jButtonRemoveComponentFromLayer.addActionListener(this);

		definitionJPanel.jButtonAddRuleToLayer.addActionListener(this);
		definitionJPanel.jButtonEditRuleFromLayer.addActionListener(this);
		definitionJPanel.jButtonRemoveRuleFromLayer.addActionListener(this);

		// Return the definition jpanel
		return definitionJPanel;
	}

	/**
	 * Create an new configuration.
	 */
	public void newConfiguration() {
		Log.i(this, "newConfiguration()");
		try {
			// Ask the user for the architecture name
			String response = UiDialogs.inputDialog(definitionJPanel, "Please enter the architecture name", "Please input a value", JOptionPane.QUESTION_MESSAGE);
			if (response != null) {
				Log.i(this, "newDefinition() - response from inputdialog: " + response);
				JPanelStatus.getInstance("Creating new configuration").start();

				// Create a new configuration
				definitionService.newConfiguration(response, "");

				// Update the layer list, this method is called because it will also clear the existing layers
				updateLayerList();

				// Set the architecture name in the jframe title
				mainController.jframe.setTitle(response);
			}
		} catch (Exception e) {
			Log.e(this, "newConfiguration() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	/**
	 * Open an configuration.
	 */
	public void openConfiguration() {
		Log.i(this, "openConfiguration()");
		try {
			// Create a file chooser
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new XmlFileFilter());

			// In response to a button click:
			int returnVal = fc.showOpenDialog(definitionJPanel);

			// The user did click on Open
			if (returnVal == JFileChooser.APPROVE_OPTION) {

				JPanelStatus.getInstance("Opening configuration").start();

				// Getting selected file from dialog
				File file = fc.getSelectedFile();
				Log.i(this, "openConfiguration() - opening file: " + file.getName());

				// Pass the file to the service
				definitionService.importConfiguration(file);

				// Set the architecture name in the jframe title
				mainController.jframe.setTitle(definitionService.getArchitectureName());

				Log.i(this, "openConfiguration() - updating layers list");
				updateLayerList();

				Log.i(this, "openConfiguration() - success opening configuration");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this, "openConfiguration() - exeption: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	/**
	 * Save the current configuration to a file.
	 */
	public void saveConfiguration() {
		Log.i(this, "saveConfiguration()");
		try {
			// Create a file chooser
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new XmlFileFilter());

			// In response to a button click:
			int returnVal = fc.showSaveDialog(definitionJPanel);

			// The user did click on Save
			if (returnVal == JFileChooser.APPROVE_OPTION) {

				JPanelStatus.getInstance("Saving configuration").start();

				// Getting selected file from dialog
				File file;
				if (fc.getSelectedFile().getName().endsWith(".xml")) {
					file = fc.getSelectedFile();
				} else {
					file = new File(fc.getSelectedFile().getAbsolutePath() + ".xml");
				}
				Log.i(this, "saveConfiguration() - configuration needs to be saved to file: " + file.getName());

				// Pass the file to the service
				definitionService.exportConfiguration(file);

				Log.i(this, "saveConfiguration() - success saving configuration");
			}
		} catch (Exception e) {
			Log.e(this, "saveConfiguration() - exeption: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	/**
	 * Create a new layer
	 */
	private void newLayer() {
		Log.i(this, "newLayer()");
		try {
			// Ask the user for the layer name
			String layerName = UiDialogs.inputDialog(definitionJPanel, "Please enter layer name", "Please input a value", JOptionPane.QUESTION_MESSAGE);
			if (layerName != null) {
				JPanelStatus.getInstance("Creating new layer").start();

				Log.i(this, "newLayer() - value: " + layerName);
				// Create the layer
				definitionService.newLayer(layerName, "");

				// Update the layer list
				updateLayerList();
			}
		} catch (Exception e) {
			Log.e(this, "newLayer() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	/**
	 * Remove a layer which is selected in the JPanel.
	 */
	private void removeLayer() {
		Log.i(this, "removeLayer()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();
			if (layer_id != -1) {
				boolean confirm = UiDialogs.confirmDialog(definitionJPanel, "Are you sure you want to remove layer: \"" + definitionService.getLayerName(layer_id) + "\"", "Remove?");
				if (confirm) {
					JPanelStatus.getInstance("Removing layer").start();

					definitionService.removeLayer(layer_id);

					updateLayerList();
				}

			}
		} catch (Exception e) {
			Log.e(this, "removeLayer() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	/**
	 * Move a layer 1 up in hierarchy
	 */
	private void moveLayerUp() {
		Log.i(this, "moveLayerUp()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();

			if (layer_id != -1) {
				JPanelStatus.getInstance("Moving layer up").start();

				definitionService.moveLayerUp(layer_id);

				updateLayerList();
			}
		} catch (Exception e) {
			Log.e(this, "moveLayerUp() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	/**
	 * Move a layer 1 down in hierarchy
	 */
	private void moveLayerDown() {
		Log.i(this, "moveLayerDown()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();

			if (layer_id != -1) {
				JPanelStatus.getInstance("Moving layer down").start();

				definitionService.moveLayerDown(layer_id);

				updateLayerList();
			}
		} catch (Exception e) {
			Log.e(this, "moveLayerDown() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	/**
	 * Add a new software unit to the selected layer. This method will make pop-up a new jframe who will handle everything for creating a new sotware unit.
	 */
	private void addSoftwareUnit() {
		Log.i(this, "addSoftwareUnit()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();

			if (layer_id != -1) {
				// Create a new software unit controller
				SoftwareUnitController c = new SoftwareUnitController(layer_id, -1L);
				// Set the action of the view
				c.setAction(PopUpController.ACTION_NEW);
				c.addObserver(this);
				// Build and show the ui
				c.initUi();
			}
		} catch (Exception e) {
			Log.e(this, "addSoftwareUnit() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		}
	}

	/**
	 * Edit the selected software unit. This method will make a new jframe who will handle everything for editing a new software unit
	 */
	private void editSoftwareUnit() {
		Log.i(this, "editSoftwareUnit()");

		try {
			int layer_id = definitionJPanel.getSelectedLayer();
			long softwareunit_id = definitionJPanel.getSelectedSoftwareUnit();

			if (layer_id != -1 && softwareunit_id != -1L) {
				// Create a new software unit controller
				SoftwareUnitController c = new SoftwareUnitController(layer_id, softwareunit_id);
				// Set the action of the view
				c.setAction(PopUpController.ACTION_EDIT);
				c.addObserver(this);
				// Build and show the ui
				c.initUi();
			} else {
				Log.e(this, "editSoftwareUnit() - no software unit selected");
				UiDialogs.errorDialog(definitionJPanel, "Select a software unit", "Error");
			}
		} catch (Exception e) {
			Log.e(this, "editSoftwareUnit() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		}
	}

	/**
	 * Remove the selected software unit from the table
	 */
	private void removeSoftwareUnit() {
		Log.i(this, "removeSoftwareUnit()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();
			long softwareunit_id = definitionJPanel.getSelectedSoftwareUnit();

			if (layer_id != -1 && softwareunit_id != -1L) {
				// Ask the user if he is sure to remove the software unit
				boolean confirm = UiDialogs.confirmDialog(definitionJPanel, "Are you sure you want to remove software unit: \"" + definitionService.getSoftwareUnitName(layer_id, softwareunit_id) + "\"", "Remove?");
				if (confirm) {
					// Remove the software unit
					JPanelStatus.getInstance("Removing software unit").start();
					definitionService.removeSoftwareUnit(layer_id, softwareunit_id);
					// Update the software unit table
					updateSoftwareUnitTable();
				}
			}
		} catch (Exception e) {
			Log.e(this, "removeSoftwareUnit() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	private void addRuleToLayer() {
		Log.i(this, "addRuleToLayer()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();

			if (layer_id != -1) {
				// Create a new software unit controller
				AppliedRulesController a = new AppliedRulesController(layer_id, -1L);
				// Set the action of the view
				a.setAction(PopUpController.ACTION_NEW);
				a.addObserver(this);
				// Build and show the ui
				a.initUi();
			}
		} catch (Exception e) {
			Log.e(this, "addRuleToLayer() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		}
	}

	private void editRuleToLayer() {
		Log.i(this, "editRuleToLayer()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();
			long appliedrule_id = definitionJPanel.getSelectedAppliedRule();

			if (layer_id != -1 && appliedrule_id != -1L) {
				// Create a new software unit controller
				AppliedRulesController a = new AppliedRulesController(layer_id, appliedrule_id);
				// Set the action of the view
				a.setAction(PopUpController.ACTION_EDIT);
				a.addObserver(this);
				// Build and show the ui
				a.initUi();
			} else {
				Log.e(this, "editRuleToLayer() - no applied rule selected");
				UiDialogs.errorDialog(definitionJPanel, "Select an applied rule", "Error");
			}
		} catch (Exception e) {
			Log.e(this, "editRuleToLayer() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		}
	}

	private void removeRuleToLayer() {
		Log.i(this, "removeRuleToLayer()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();
			long appliedrule_id = definitionJPanel.getSelectedAppliedRule();

			if (layer_id != -1 && appliedrule_id != -1L) {
				// Ask the user if he is sure to remove the software unit
				boolean confirm = UiDialogs.confirmDialog(definitionJPanel, "Are you sure you want to remove the applied rule: \"" + definitionService.getAppliedRuleRuleType(layer_id, appliedrule_id) + "\"", "Remove?");
				if (confirm) {
					// Remove the software unit
					JPanelStatus.getInstance("Removing applied rule").start();
					definitionService.removeAppliedRule(layer_id, appliedrule_id);

					// Update the applied rules table
					updateAppliedRulesTable();
				}
			}
		} catch (Exception e) {
			Log.e(this, "removeRuleToLayer() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}

	}

	/**
	 * Function which will save the name and description changes to the layer
	 */
	private void updateLayer() {
		Log.i(this, "updateLayer()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();

			JPanelStatus.getInstance("Saving layer").start();

			if (layer_id != -1) {
				definitionService.setLayerName(layer_id, definitionJPanel.jTextFieldLayerName.getText());
				definitionService.setLayerDescription(layer_id, definitionJPanel.jTextAreaLayerDescription.getText());
				definitionService.setLayerInterfaceAccesOnly(layer_id, definitionJPanel.jCheckBoxAccess.isSelected());

				//To update the layer list: we need to fetch the DataHelper from the list, update it and fire an updateUI to notice that there is an update
				DefaultListModel dlm = (DefaultListModel) definitionJPanel.jListLayers.getModel();				
				for (int i = 0; i < dlm.getSize(); i++) {
					DataHelper datahelper = (DataHelper) dlm.getElementAt(i);
					if (datahelper.getIntId() == layer_id) {
						datahelper.setValue(definitionJPanel.jTextFieldLayerName.getText());
					}
				}
				definitionJPanel.jListLayers.updateUI();
			}
		} catch (Exception e) {
			Log.e(this, "updateLayer() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	/**
	 * This method updates the layers list in the jpanel.
	 */
	private void updateLayerList() {
		Log.i(this, "updateLayerList()");

		JPanelStatus.getInstance("Updating layers").start();

		// Get all layers from the service
		ArrayList<Integer> layers = definitionService.getLayers();

		// Get ListModel from listlayers
		DefaultListModel dlm = (DefaultListModel) definitionJPanel.jListLayers.getModel();

		// Remove all items in the list
		dlm.removeAllElements();

		// Add layers to the list
		if (layers != null) {
			for (int layer_id : layers) {
				DataHelper datahelper = new DataHelper();
				datahelper.setId(layer_id);
				datahelper.setValue(definitionService.getLayerName(layer_id));
				dlm.addElement(datahelper);
			}
		}

		enablePanel();

		JPanelStatus.getInstance().stop();
	}

	/**
	 * This function will load the layer name, descriptin and interface acces only checkbox. Next it will call two methods which will load the two tables.
	 */
	private void loadLayerDetail() {
		Log.i(this, "loadLayerDetail()");

		int layer_id = definitionJPanel.getSelectedLayer();

		if (layer_id != -1) {
			// Set the values
			definitionJPanel.jTextFieldLayerName.setText(definitionService.getLayerName(layer_id));
			definitionJPanel.jTextAreaLayerDescription.setText(definitionService.getLayerDescription(layer_id));
			definitionJPanel.jCheckBoxAccess.setSelected(definitionService.getLayerInterfaceOnly(layer_id));

			// Update the tables
			updateSoftwareUnitTable();
			updateAppliedRulesTable();

			// Enable or disable the ui elements
			enablePanel();
		}
	}

	/**
	 * This method will check if the ui elements should be enabled or disabled
	 */
	private void enablePanel() {
		Log.i(this, "enablePanel()");

		int layer_id = definitionJPanel.getSelectedLayer();

		boolean enabled;
		if (layer_id == -1) {
			Log.i(this, "enablePanel() - false");
			enabled = false;
		} else {
			Log.i(this, "enablePanel() - true");
			enabled = true;
		}
		// Buttons, textfields, tables etc.
		definitionJPanel.jTextFieldLayerName.setEnabled(enabled);
		definitionJPanel.jTextAreaLayerDescription.setEnabled(enabled);
		definitionJPanel.jCheckBoxAccess.setEnabled(enabled);
		definitionJPanel.jButtonAddComponentToLayer.setEnabled(enabled);
		definitionJPanel.jButtonEditComponentFromLayer.setEnabled(enabled);
		definitionJPanel.jButtonRemoveComponentFromLayer.setEnabled(enabled);
		definitionJPanel.jTableSoftwareUnits.setEnabled(enabled);
		definitionJPanel.jTableAppliedRules.setEnabled(enabled);
		definitionJPanel.jButtonAddRuleToLayer.setEnabled(enabled);
		definitionJPanel.jButtonEditRuleFromLayer.setEnabled(enabled);
		definitionJPanel.jButtonRemoveRuleFromLayer.setEnabled(enabled);
		definitionJPanel.jButtonMoveLayerUp.setEnabled(enabled);
		definitionJPanel.jButtonMoveLayerDown.setEnabled(enabled);
		definitionJPanel.jButtonRemoveLayer.setEnabled(enabled);

		// Enable or disable menu items
		if (!definitionService.hasArchitectureDefinition()) {
			definitionJPanel.jButtonNewLayer.setEnabled(false);
			mainController.jframe.jMenuItemSaveArchitecture.setEnabled(false);
			mainController.jframe.jMenuItemStartAnalyse.setEnabled(false);
			mainController.jframe.jMenuItemCheckDependencies.setEnabled(false);
		} else {
			definitionJPanel.jButtonNewLayer.setEnabled(true);
			mainController.jframe.jMenuItemSaveArchitecture.setEnabled(true);
			mainController.jframe.jMenuItemStartAnalyse.setEnabled(true);
			mainController.jframe.jMenuItemCheckDependencies.setEnabled(true);
		}
	}

	/**
	 * This method updates the component table in the jpanel
	 * 
	 * @param layer
	 */
	private void updateSoftwareUnitTable() {
		Log.i(this, "updateSoftwareUnitTable()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();

			if (layer_id != -1) {
				JPanelStatus.getInstance("Updating software unit table").start();

				// Get all components from the service
				ArrayList<Long> softwareUnits = definitionService.getSoftwareUnits(layer_id);

				// Get the tablemodel from the table
				JTableTableModel atm = (JTableTableModel) definitionJPanel.jTableSoftwareUnits.getModel();

				// Remove all items in the table
				atm.getDataVector().removeAllElements();
				if (softwareUnits != null) {
					for (long softwareUnit_id : softwareUnits) {
						DataHelper datahelper = new DataHelper();
						datahelper.setId(softwareUnit_id);
						datahelper.setValue(definitionService.getSoftwareUnitName(layer_id, softwareUnit_id));

						// Number of exceptions
						ArrayList<Long> softwareUnitExceptions = definitionService.getSoftwareUnitExceptions(layer_id, softwareUnit_id);
						int numberofexceptions = 0;
						if (softwareUnitExceptions != null) {
							numberofexceptions = softwareUnitExceptions.size();
						}

						Object rowdata[] = { datahelper, definitionService.getSoftwareUnitType(layer_id, softwareUnit_id), numberofexceptions };
						atm.addRow(rowdata);
					}
				}
				atm.fireTableDataChanged();
			}
		} catch (Exception e) {
			Log.e(this, "updateSoftwareUnitTable() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error!");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	private void updateAppliedRulesTable() {
		Log.i(this, "updateAppliedRulesTable()");
		try {
			int layer_id = definitionJPanel.getSelectedLayer();

			if (layer_id != -1) {
				JPanelStatus.getInstance("Updating rules applied table").start();

				// Get all applied rules from the service
				ArrayList<Long> appliedrules = definitionService.getAppliedRules(layer_id);

				// Get the tablemodel from the table
				JTableTableModel atm = (JTableTableModel) definitionJPanel.jTableAppliedRules.getModel();

				// Remove all items in the table
				atm.getDataVector().removeAllElements();
				if (appliedrules != null) {
					for (long appliedrule_id : appliedrules) {
						DataHelper datahelper = new DataHelper();
						datahelper.setId(appliedrule_id);
						datahelper.setValue(definitionService.getAppliedRuleRuleType(layer_id, appliedrule_id));

						// To layer
						int appliedRuleToLayer = definitionService.getAppliedRuleToLayer(layer_id, appliedrule_id);

						// Is enabled
						boolean appliedRuleIsEnabled = definitionService.getAppliedRuleIsEnabled(layer_id, appliedrule_id);
						String enabled = "Off";
						if (appliedRuleIsEnabled) {
							enabled = "On";
						}
						// Number of exceptions
						ArrayList<Long> appliedRulesExceptions = definitionService.getAppliedRuleExceptions(layer_id, appliedrule_id);
						int numberofexceptions = 0;
						if (appliedRulesExceptions != null) {
							numberofexceptions = appliedRulesExceptions.size();
						}

						Object rowdata[] = { datahelper, definitionService.getLayerName(appliedRuleToLayer), enabled, numberofexceptions };

						atm.addRow(rowdata);
					}
				}
				atm.fireTableDataChanged();
			}
		} catch (Exception e) {
			Log.e(this, "updateAppliedRulesTable() - exception: " + e.getMessage());
			UiDialogs.errorDialog(definitionJPanel, e.getMessage(), "Error!");
		} finally {
			JPanelStatus.getInstance().stop();
		}
	}

	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == definitionJPanel.jButtonNewLayer) {
			newLayer();
		} else if (action.getSource() == definitionJPanel.jButtonRemoveLayer) {
			removeLayer();
		} else if (action.getSource() == definitionJPanel.jButtonMoveLayerUp) {
			moveLayerUp();
		} else if (action.getSource() == definitionJPanel.jButtonMoveLayerDown) {
			moveLayerDown();
		} else if (action.getSource() == definitionJPanel.jButtonAddComponentToLayer) {
			addSoftwareUnit();
		} else if (action.getSource() == definitionJPanel.jButtonEditComponentFromLayer) {
			editSoftwareUnit();
		} else if (action.getSource() == definitionJPanel.jButtonRemoveComponentFromLayer) {
			removeSoftwareUnit();
		} else if (action.getSource() == definitionJPanel.jButtonAddRuleToLayer) {
			addRuleToLayer();
		} else if (action.getSource() == definitionJPanel.jButtonEditRuleFromLayer) {
			editRuleToLayer();
		} else if (action.getSource() == definitionJPanel.jButtonRemoveRuleFromLayer) {
			removeRuleToLayer();
		} else if (action.getSource() == definitionJPanel.jCheckBoxAccess) {
			updateLayer();
		} else {
			Log.i(this, "actionPerformed(" + action + ")");
		}
	}

	public void valueChanged(ListSelectionEvent event) {
		if (event.getSource() == definitionJPanel.jListLayers && !event.getValueIsAdjusting()) {
			loadLayerDetail();
		}
	}

	public void keyPressed(KeyEvent arg0) {
		// Ignore
	}

	public void keyReleased(KeyEvent arg0) {
		Log.i(this, "keyReleased(" + arg0 + ")");

		if (arg0.getSource() == definitionJPanel.jTextFieldLayerName || arg0.getSource() == definitionJPanel.jTextAreaLayerDescription) {
			updateLayer();
		} else {
			Log.i(this, "keyReleased(" + arg0 + ")");
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// Ignore
	}

	public void update(Observable o, Object arg) {
		Log.i(this, "update(" + o + ", " + arg + ")");
		updateAppliedRulesTable();
		updateSoftwareUnitTable();
	}

}
