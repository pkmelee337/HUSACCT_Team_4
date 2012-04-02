package DefineDomain.DTO;

public class RuleDTO {
	public String ruleTypeKey;
	public String[] violationTypeKeys;
	public ModuleDTO moduleFrom;
	public ModuleDTO moduleTo;
	public RuleDTO[] exceptionRules;
}
