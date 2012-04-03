package husacct.define.domain.define_domain;

import husacct.define.domain.define_domain.DTO.*;

public interface IDefineService {
	
	public RuleDTO[] getDefinedRules();
	public ModuleDTO[] getDefinedLayers();
	public ApplicationDTO getApplicationDetails();	
	public ModuleDTO[] getChildsFromModule(String logicalPath);
	public String getParentFromModule(String logicalPath);
}
