package husacct.define.domain.define_domain.applogic.view;


public class MainUI {

	public static void main(String[] args) throws Exception {
//		ImportController importc = new ImportController();
//		importc.importXML(new File("./xml/architecture.xml"));

		// Start the Application controller
		ApplicationController gui = new ApplicationController();

		// Init the Ui
		gui.initUi();
	}
}