package define.applogic;

import define.DTO.*;
import define.domain.*;

public class stub implements IDefineService{

	@Override
	public RuleDTO[] getDefinedRules() {
		//DEFINE ARCHITECTURE
		//DEFINE ARCHITECTURE
		//IGNORE
		//IGNORE
		//IGNORE
		ModuleDTO module5 = new ModuleDTO();
		module5.name = "m5";
		module5.uniqueNames = new String[] {};
		module5.subModules = new ModuleDTO[]{};
		
		ModuleDTO module6 = new ModuleDTO();
		module6.name = "m6";
		module6.uniqueNames = new String[] {};
		module6.subModules = new ModuleDTO[]{};
		
		ModuleDTO module1 = new ModuleDTO();
		module1.name = "m1";
		module1.uniqueNames = new String[] {};
		module1.subModules = new ModuleDTO[]{module5};
		
		ModuleDTO module2 = new ModuleDTO();
		module2.name = "m2";
		module2.uniqueNames = new String[] {};
		module2.subModules = new ModuleDTO[]{module6};
		
		ModuleDTO module3 = new ModuleDTO();
		module3.name = "m3";
		module3.uniqueNames = new String[] {};
		module3.subModules = new ModuleDTO[]{};
		
		ModuleDTO module4 = new ModuleDTO();
		module4.name = "m4";
		module4.uniqueNames = new String[] {};
		module4.subModules = new ModuleDTO[]{};
		
		LayerDTO layer1 = new LayerDTO();
		layer1.name = "l1";
		layer1.subModules = new ModuleDTO[]{module1, module2};
		
		LayerDTO layer2 = new LayerDTO();
		layer2.name = "l2";
		layer2.subModules = new ModuleDTO[]{module3, module4};
		
		//ACTUAL RULES
		//ACTUAL RULES
		RuleDTO ruleOne = new RuleDTO();
		ruleOne.ruleType = "Is not allowed to use";
		ruleOne.violationTypes = new String[]{"Import","Annotation"};
		ruleOne.moduleFrom = module1;			
		ruleOne.moduleTo = module2;
			RuleDTO exceptionRuleOnRuleOne = new RuleDTO();
			ruleOne.ruleType = "Exception";
			ruleOne.moduleFrom = module5;			
			ruleOne.moduleTo = module6;
		ruleOne.exceptionRules = new RuleDTO[]{exceptionRuleOnRuleOne};

		RuleDTO ruleTwo = new RuleDTO();
		ruleTwo.ruleType = "Must Use";
		ruleTwo.violationTypes = new String[]{"Invocation of a method/contructor", "Access of a proprty of field," +
				"Extending a class/struct", "Implementing an interface", "Declaration", "Annotation of an attribute" +
				"Import", "Throw an exception of a class"};
		ruleTwo.moduleFrom = module5;
		ruleTwo.moduleTo = module6;
		ruleOne.exceptionRules = new RuleDTO[]{};
		
		RuleDTO[] rules = new RuleDTO[]{ruleOne, ruleTwo};
		return rules;
	}

	@Override
	public LayerDTO[] getDefinedLayers() {
		ModuleDTO module5 = new ModuleDTO();
		module5.name = "m5";
		module5.uniqueNames = new String[] {};
		module5.subModules = new ModuleDTO[]{};
		
		ModuleDTO module6 = new ModuleDTO();
		module6.name = "m6";
		module6.uniqueNames = new String[] {};
		module6.subModules = new ModuleDTO[]{};
		
		ModuleDTO module1 = new ModuleDTO();
		module1.name = "m1";
		module1.uniqueNames = new String[] {};
		module1.subModules = new ModuleDTO[]{module5};
		
		ModuleDTO module2 = new ModuleDTO();
		module2.name = "m2";
		module2.uniqueNames = new String[] {};
		module2.subModules = new ModuleDTO[]{module6};
		
		ModuleDTO module3 = new ModuleDTO();
		module3.name = "m3";
		module3.uniqueNames = new String[] {};
		module3.subModules = new ModuleDTO[]{};
		
		ModuleDTO module4 = new ModuleDTO();
		module4.name = "m4";
		module4.uniqueNames = new String[] {};
		module4.subModules = new ModuleDTO[]{};
		
		LayerDTO layer1 = new LayerDTO();
		layer1.name = "l1";
		layer1.subModules = new ModuleDTO[]{module1, module2};
		
		LayerDTO layer2 = new LayerDTO();
		layer2.name = "l2";
		layer2.subModules = new ModuleDTO[]{module3, module4};

		LayerDTO[] allLayers = new LayerDTO[]{layer1,layer2};
		return allLayers;
	}

	@Override
	public ApplicationDTO getApplicationDetails() {
		ApplicationDTO application = new ApplicationDTO();
		application.name = "Application1";
		application.path = "c:/Application1/";
		application.programmingLanguage = "Java";
		return application;
	}

}
