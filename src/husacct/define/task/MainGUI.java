package husacct.define.task;

import husacct.define.task.applogic.controller.ApplicationController;

public class MainGUI {

	public static void main(String args[])
	{
//		ImportController importc = new ImportController();
//		importc.importXML(new File("./xml/architecture.xml"));

		// Start the Application controller
		ApplicationController gui = new ApplicationController();

		// Init the Ui
		gui.initUi();
	}
}