package husacct.define;

import husacct.define.dto.*;

public interface IDefineService {
	
	public RuleDTO[] getDefinedRules();
	public ModuleDTO[] getDefinedLayers();
	public ApplicationDTO getApplicationDetails();	
	public ModuleDTO[] getChildsFromModule(String logicalPath);
	public String getParentFromModule(String logicalPath);
}
