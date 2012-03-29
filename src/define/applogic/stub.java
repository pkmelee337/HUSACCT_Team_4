package define.applogic;

import define.DTO.*;
import define.domain.*;

public class stub implements IDefineService{

	@Override
	public RuleDTO[] getDefinedRules() {
		RuleDTO ruleOne = new RuleDTO();
		ruleOne.ruleType = "Is not allowed to use";
		ruleOne.violationTypes = new String[]{"Import","Annotation"};
//		ruleOne.Module
		RuleDTO ruleTwo = new RuleDTO();
		
		RuleDTO[] rules = new RuleDTO[]{ruleOne, ruleTwo};
		return rules;
	}

	@Override
	public LayerDTO[] getDefinedLayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationDTO getApplicationDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
