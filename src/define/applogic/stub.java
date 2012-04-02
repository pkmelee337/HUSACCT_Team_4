package define.applogic;

import define.DTO.*;

public class Stub implements IDefineService{

	@Override
	public RuleDTO[] getDefinedRules() {
		//Temporary architecture
		ModuleDTO lbDAOModule = new ModuleDTO();
		lbDAOModule.name = "locationbasedDAO";
		lbDAOModule.uniqueNames = new String[] {"infrastructure.socialmedia.locationbased.foursquare.AccountDAO",
				"infrastructure.socialmedia.locationbased.foursquare.FriendsDAO",
				"infrastructure.socialmedia.locationbased.foursquare.IMap",
				"infrastructure.socialmedia.locationbased.foursquare.HistoryDAO"};
		lbDAOModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO latitudeModule = new ModuleDTO();
		latitudeModule.name = "latitudeConnection";
		latitudeModule.uniqueNames = new String[] {"domain.locationbased.latitude.Account",
				"domain.locationbased.latitude.Friends", "domain.locationbased.latitude.Map"};
		latitudeModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO fqConnectionModule = new ModuleDTO();
		fqConnectionModule.name = "foursquareConnection";
		fqConnectionModule.uniqueNames = new String[] {"domain.locationbased.foursquare.Account",
				"domain.locationbased.foursquare.Friends", "domain.locationbased.foursquare.Map"};
		fqConnectionModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO lbHistoryModule = new ModuleDTO();
		lbHistoryModule.name = "locationbasedHistory";
		lbHistoryModule.uniqueNames = new String[] {"domain.locationbased.foursquare.History"};
		lbHistoryModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO lbConnectionsModule = new ModuleDTO();
		lbConnectionsModule.name = "locationbasedConnections";
		lbConnectionsModule.uniqueNames = new String[] {};
		lbConnectionsModule.subModules = new ModuleDTO[]{fqConnectionModule, latitudeModule};
		
		LayerDTO infrastructureLayer = new LayerDTO();
		infrastructureLayer.name = "Infrastructure layer";
		infrastructureLayer.subModules = new ModuleDTO[]{lbDAOModule};
		
		LayerDTO domainLayer = new LayerDTO();
		domainLayer.name = "Domain Layer";
		domainLayer.subModules = new ModuleDTO[]{lbConnectionsModule, lbHistoryModule};
		
		//All violationTypes
//		new String[]{"Invocation of a method/contructor", "Access of a property of field," +
//				"Extending a class/struct", "Implementing an interface", "Declaration", "Annotation of an attribute" +
//				"Import", "Throw an exception of a class"};
		
		//ACTUAL RULES
		//ACTUAL RULES
		RuleDTO ruleOne = new RuleDTO();
		ruleOne.ruleType = "Is not allowed to use";
		ruleOne.violationTypes = new String[]{"Invocation of a method/contructor","Extending an abstract class", "Implementing an interface"};
		ruleOne.moduleFrom = lbConnectionsModule;			
		ruleOne.moduleTo = lbDAOModule;
		ruleOne.exceptionRules = new RuleDTO[]{};

		RuleDTO ruleTwo = new RuleDTO();
		ruleTwo.ruleType = "Is not allowed to use";		
		ruleTwo.violationTypes = new String[] {"Extending a class/struct"};
		ruleTwo.moduleFrom = lbHistoryModule;
		ruleTwo.moduleTo = lbDAOModule;
		ruleOne.exceptionRules = new RuleDTO[]{};
		
		RuleDTO[] rules = new RuleDTO[]{ruleOne, ruleTwo};
		return rules;
	}

	@Override
	public LayerDTO[] getDefinedLayers() {	
		ModuleDTO lbDAOModule = new ModuleDTO();
		lbDAOModule.name = "locationbasedDAO";
		lbDAOModule.uniqueNames = new String[] {"infrastructure.socialmedia.locationbased.foursquare.AccountDAO",
				"infrastructure.socialmedia.locationbased.foursquare.FriendsDAO",
				"infrastructure.socialmedia.locationbased.foursquare.IMap",
				"infrastructure.socialmedia.locationbased.foursquare.HistoryDAO"};
		lbDAOModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO latitudeModule = new ModuleDTO();
		latitudeModule.name = "latitudeConnection";
		latitudeModule.uniqueNames = new String[] {"domain.locationbased.latitude.Account",
				"domain.locationbased.latitude.Friends", "domain.locationbased.latitude.Map"};
		latitudeModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO fqConnectionModule = new ModuleDTO();
		fqConnectionModule.name = "foursquareConnection";
		fqConnectionModule.uniqueNames = new String[] {"domain.locationbased.foursquare.Account",
				"domain.locationbased.foursquare.Friends", "domain.locationbased.foursquare.Map"};
		fqConnectionModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO lbHistoryModule = new ModuleDTO();
		lbHistoryModule.name = "locationbasedHistory";
		lbHistoryModule.uniqueNames = new String[] {"domain.locationbased.foursquare.History"};
		lbHistoryModule.subModules = new ModuleDTO[]{};
		
		ModuleDTO lbConnectionsModule = new ModuleDTO();
		lbConnectionsModule.name = "locationbasedConnections";
		lbConnectionsModule.uniqueNames = new String[] {};
		lbConnectionsModule.subModules = new ModuleDTO[]{fqConnectionModule, latitudeModule};
		
		LayerDTO infrastructureLayer = new LayerDTO();
		infrastructureLayer.name = "Infrastructure layer";
		infrastructureLayer.subModules = new ModuleDTO[]{lbDAOModule};
		
		LayerDTO domainLayer = new LayerDTO();
		domainLayer.name = "Domain Layer";
		domainLayer.subModules = new ModuleDTO[]{lbConnectionsModule, lbHistoryModule};

		LayerDTO[] allLayers = new LayerDTO[]{domainLayer,infrastructureLayer};
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
