package husacct.define;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import husacct.define.dto.*;

public class DefineServiceImpl implements IDefineService {

	@Override
	public RuleDTO[] getDefinedRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleDTO[] getDefinedLayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationDTO getApplicationDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleDTO[] getChildsFromModule(String logicalPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParentFromModule(String logicalPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document exportLogicalArchitecture()
			throws ParserConfigurationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void importLogicalArchitecture(Document doc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Document exportPhysicalArchitecture()
			throws ParserConfigurationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void importPhysicalArchitecture(Document doc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JFrame getDefinedGUI() {
		// TODO Auto-generated method stub
		return null;
	}

}
