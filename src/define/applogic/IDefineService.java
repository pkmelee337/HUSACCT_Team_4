package define.applogic;

import define.DTO.*;

public interface IDefineService {
	
	public RuleDTO[] getDefinedRules();
	
	public LayerDTO[] getDefinedLayers();
	
	public ApplicationDTO getApplicationDetails();	
}
