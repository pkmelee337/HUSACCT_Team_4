package husacct.define.domain.define_domain;

import husacct.define.domain.define_domain.DTO.*;

public class DefineServiceStub implements IDefineService{

	@Override
	public RuleDTO[] getDefinedRules() {
		//Temporary architecture
		ModuleDTO lbDAOModule = new ModuleDTO();
		lbDAOModule.logicalPath = "InfrastructureLayer.locationbasedDAO";
		lbDAOModule.physicalPaths = new String[] {"infrastructure.socialmedia.locationbased.foursquare.AccountDAO",
				"infrastructure.socialmedia.locationbased.foursquare.FriendsDAO",
				"infrastructure.socialmedia.locationbased.foursquare.IMap",
				"infrastructure.socialmedia.locationbased.foursquare.HistoryDAO"};
		lbDAOModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO latitudeModule = new ModuleDTO();
		latitudeModule.logicalPath = "DomainLayer.locationbasedConnections.latitudeConnection";
		latitudeModule.physicalPaths = new String[] {"domain.locationbased.latitude.Account",
				"domain.locationbased.latitude.Friends", "domain.locationbased.latitude.Map"};
		latitudeModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO fqConnectionModule = new ModuleDTO();
		fqConnectionModule.logicalPath = "DomainLayer.locationbasedConnections.foursquareConnection";
		fqConnectionModule.physicalPaths = new String[] {"domain.locationbased.foursquare.Account",
				"domain.locationbased.foursquare.Friends", "domain.locationbased.foursquare.Map"};
		fqConnectionModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO lbHistoryModule = new ModuleDTO();
		lbHistoryModule.logicalPath = "DomainLayer.locationbasedHistory";
		lbHistoryModule.physicalPaths = new String[] {"domain.locationbased.foursquare.History"};
		lbHistoryModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO lbConnectionsModule = new ModuleDTO();
		lbConnectionsModule.logicalPath = "DomainLayer.locationbasedConnections";
		lbConnectionsModule.physicalPaths = new String[] {};
		lbConnectionsModule.subModules = new ModuleDTO[]{fqConnectionModule, latitudeModule};
		
		ModuleDTO infrastructureLayer = new ModuleDTO();
		infrastructureLayer.logicalPath = "InfrastructureLayer";
		infrastructureLayer.subModules = new ModuleDTO[]{lbDAOModule};
		
		ModuleDTO domainLayer = new ModuleDTO();
		domainLayer.logicalPath = "DomainLayer";
		domainLayer.subModules = new ModuleDTO[]{lbConnectionsModule, lbHistoryModule};
		
		//All violationTypes
		//IGNORE FOR ELABORATION VERSION
//		new String[]{"Invocation of a method/contructor", "Access of a property of field," +
//				"Extending a class/struct", "Implementing an interface", "Declaration", "Annotation of an attribute" +
//				"Import", "Throw an exception of a class"};
		
		//ACTUAL RULES
		//ACTUAL RULES
		RuleDTO ruleOne = new RuleDTO();
		ruleOne.ruleTypeKey = "Is not allowed to use";
			//IGNORE FOR ELABORATION VERSION
			ruleOne.violationTypeKeys = new String[]{"Invocation of a method/contructor","Extending an abstract class", "Implementing an interface"};
		ruleOne.moduleFrom = lbConnectionsModule;			
		ruleOne.moduleTo = lbDAOModule;
		ruleOne.exceptionRules = new RuleDTO[]{};

		RuleDTO ruleTwo = new RuleDTO();
		ruleTwo.ruleTypeKey = "Is not allowed to use";		
			//IGNORE FOR ELABORATION VERSION
			ruleTwo.violationTypeKeys = new String[] {"Extending a class/struct"};
		ruleTwo.moduleFrom = lbHistoryModule;
		ruleTwo.moduleTo = lbDAOModule;
		ruleOne.exceptionRules = new RuleDTO[]{};
		
		RuleDTO[] rules = new RuleDTO[]{ruleOne, ruleTwo};
		return rules;
	}

	@Override
	public ModuleDTO[] getDefinedLayers() {			
		//Gets only the top level abstraction Modules
		
		ModuleDTO infrastructureLayer = new ModuleDTO();
		infrastructureLayer.logicalPath = "InfrastructureLayer";
		infrastructureLayer.subModules = new ModuleDTO[]{};
		
		ModuleDTO domainLayer = new ModuleDTO();
		domainLayer.logicalPath = "DomainLayer";
		domainLayer.subModules = new ModuleDTO[]{};

		ModuleDTO[] allLayers = new ModuleDTO[]{domainLayer,infrastructureLayer};
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

	@Override
	public ModuleDTO[] getChildsFromModule(String logicalPath) {
		ModuleDTO lbHistoryModule = new ModuleDTO();
		lbHistoryModule.logicalPath = "DomainLayer.locationbasedHistory";
		lbHistoryModule.physicalPaths = new String[] {"domain.locationbased.foursquare.History"};
		lbHistoryModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO lbConnectionsModule = new ModuleDTO();
		lbConnectionsModule.logicalPath = "DomainLayer.locationbasedConnections";
		lbConnectionsModule.physicalPaths = new String[] {};
		lbConnectionsModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO[] subModules = new ModuleDTO[]{lbConnectionsModule,lbHistoryModule};
		return subModules;
	}

	@Override
	public String getParentFromModule(String logicalPath) {
		//returns parent from DomainLayer
		return "**";
		
		//another example:
		//parent from module: DomainLayer.locationbasedHistory would be
		//return "DomainLayer";
	}

}