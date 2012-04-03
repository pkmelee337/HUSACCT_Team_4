package husacct.define.domain.define_domain.DTO;

public class RuleDTO {
	public String ruleTypeKey;
	public String[] violationTypeKeys;
	public ModuleDTO moduleFrom;
	public ModuleDTO moduleTo;
	public RuleDTO[] exceptionRules;
}
