package husacct.define.domain;

import java.util.ArrayList;

import husacct.define.domain.module.Layer;
import husacct.define.domain.module.Module;

public class SoftwareArchitecture {
	
	private String name;
	private String description;
	private ArrayList<Module> modules;
	private ArrayList<AppliedRule> appliedRules;
	
	private static SoftwareArchitecture instance = null;
	public static SoftwareArchitecture getInstance()
	{
		return instance == null ? (instance = new SoftwareArchitecture()) : instance;
	}
	
	public static void setInstance(SoftwareArchitecture sA)
	{
		instance = sA;
	}
	
	public SoftwareArchitecture() {
		
		setName("");
		setDescription("");
		setModules(new ArrayList<Module>());
		setAppliedRules(new ArrayList<AppliedRule>());
	}
	
	public SoftwareArchitecture(String name, String description) {
		
		this.setName(name);
		this.setDescription(description);
		setModules(new ArrayList<Module>());
		setAppliedRules(new ArrayList<AppliedRule>());
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}

	public ArrayList<Module> getModules() {
		return modules;
	}
	
	public void setAppliedRules(ArrayList<AppliedRule> appliedRules) {
		this.appliedRules = appliedRules;
	}

	public ArrayList<AppliedRule> getAppliedRules() {
		return appliedRules;
	}

	
	//Module
	public long addModule(Module module)
	{
		long moduleId;
		if(!modules.contains(module) && !this.hasModule(module.getName())) {
			modules.add(module);
			moduleId = module.getId();
		}else{
			throw new RuntimeException("This module has already been addded!");
		}
		return moduleId;
	}
	
	public boolean removeLayerByLevel(int level)
	{
		for(Module layer : modules) 
		{
			if(layer instanceof Layer)
			{
				modules.remove(layer);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void removeModule(Module module)
	{
		//TODO BUGFIX, THIS WILL GO WRONG
		boolean moduleFound = false;
		if(modules.contains(module)) {
			modules.remove(module);
		}else{
			for (Module mod : modules){
				if(mod.getSubModules().contains(module)) {
					mod.getSubModules().remove(module);
					moduleFound = true;
					break;
				}
			}
		}
		if (moduleFound) {
			throw new RuntimeException("This module does not exist!");
		}
		else {
			return;
		}
	}
	
	private boolean hasModule(String name) 
	{
		for(Module module : modules) 
		{
			
			if(module.getName().equals(name))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void setLayerName(int level, String newName)
	{
		for(Module layer : modules) 
		{
			if(layer instanceof Layer)
			{
				if (((Layer) layer).getHierarchicalLevel() == level)
				{
					layer.setName(newName);
				}
			}
		}
		
		throw new RuntimeException("This layer name cannot be set!");
	}
	
//	public ArrayList<Integer> getLevelFromLayers()
//	{
//		ArrayList<Integer> integerList = new ArrayList<Integer>();
//		for(Module layer : modules)
//		{
//			if(layer instanceof Layer)
//			{
//				integerList.add(((Layer) layer).getHierarchicalLevel());
//			}
//		}
//		return integerList;
//	}
	
	//AppliedRule
	public void addAppliedRule(AppliedRule rule)
	{
		if(!appliedRules.contains(rule) && !this.hasAppliedRule(rule.getId()))
		{
			appliedRules.add(rule);
		}else{
			throw new RuntimeException("This rule has already been added!");
		}
	}
	
	public void removeAppliedRule(long appliedRuleId)
	{
		if(this.hasAppliedRule(appliedRuleId))
		{
			AppliedRule rule = getAppliedRuleById(appliedRuleId);
			appliedRules.remove(rule);	
		}else{
			throw new RuntimeException("This rule does not exist!");
		}
	}
	
	private boolean hasAppliedRule(long l) 
	{
		for(AppliedRule rule : appliedRules) 
		{
			if(rule.getId() == l)
			{
				return true;
			}
		}
		
		return false;
	}

	public String getLayerNameByLevel(int layerLevel) {
		String layerName = "";
		for(Module layer : modules)
		{
			if(layer instanceof Layer && ((Layer) layer).getHierarchicalLevel() == layerLevel)
			{
				layerName = layer.getName();
				break;
			}
		}
		return layerName;
	}

	public String getRuleTypeByAppliedRule(long appliedruleId) {
		AppliedRule rule = getAppliedRuleById(appliedruleId);
		return rule.getRuleType();
	}
	
	public AppliedRule getAppliedRuleById(long appliedRuleId){
		AppliedRule appliedRule = new AppliedRule();
		if(this.hasAppliedRule(appliedRuleId))
		{
			for(AppliedRule rule : appliedRules) 
			{
				if(rule.getId() == appliedRuleId)
				{
					appliedRule = rule;
					break;
				}
			}		
		}else{
			throw new RuntimeException("This rule does not exist!");
		}
		return appliedRule;
	}

	public Module getModuleById(long moduleId) {
		Module currentModule = null;
		for(Module module : modules) 
		{
			if (module.getId() == moduleId ||
					module.hasSubModule(moduleId)){

				currentModule = module;
				while (currentModule.getId() != moduleId){
					for (Module subModule : currentModule.getSubModules()){
						if (subModule.getId() == moduleId ||
								subModule.hasSubModule(moduleId)){
							currentModule = subModule;
						}
					}
				}
				break;
			}
		}		
		if (currentModule == null){throw new RuntimeException("This module does not exist!");}
		return currentModule;
	}

	public void setModuleName(long moduleId, String newName) {
		Module module = getModuleById(moduleId);
		module.setName(newName);
	}

	public void setAppliedRuleIsEnabled(long appliedRuleId, boolean enabled) {
		AppliedRule rule = getAppliedRuleById(appliedRuleId);
		rule.setEnabled(enabled);
	}

	public void removeAppliedRuleException(long parentRuleId, long appliedRuleId) {
			AppliedRule parentRule = getAppliedRuleById(parentRuleId);
			AppliedRule appliedRule = getAppliedRuleById(appliedRuleId);
			parentRule.getExceptions().remove(appliedRule);
	}
	
	public void removeAppliedRuleExceptions(long appliedRuleId) {
		AppliedRule parentRule = getAppliedRuleById(appliedRuleId);
		parentRule.getExceptions().clear();
}

	public void addExceptionToAppliedRule(long parentRuleId, AppliedRule rule) {
		AppliedRule parentRule = getAppliedRuleById(parentRuleId);
		parentRule.getExceptions().add(rule);
	}

	public void moveUpDown(long layerId) {
		Layer layer = (Layer)getModuleById(layerId);
		Layer layerAboveLayer = getTheFirstLayerAbove(layer.getHierarchicalLevel());
		if (layerAboveLayer != null){
			switchHierarchicalLayerLevels(layer, layerAboveLayer);
		}
	}
	
	private Layer getTheFirstLayerAbove(int currentHierarchicalLevel){
		Layer layer = null;
		for (Module mod : modules){
			if (mod instanceof Layer) {
				Layer l = (Layer)mod;
				if (l.getHierarchicalLevel() < currentHierarchicalLevel &&
						(layer == null || l.getHierarchicalLevel() > layer.getHierarchicalLevel())){
					layer = l;
				}
			}
		}
		return layer;
	}

	public void moveLayerDown(long layerId) {
		Layer layer = (Layer)getModuleById(layerId);
		Layer layerBelowLayer = getTheFirstLayerBelow(layer.getHierarchicalLevel());
		if (layerBelowLayer != null){
			switchHierarchicalLayerLevels(layer, layerBelowLayer);
		}
	}
	
	private Layer getTheFirstLayerBelow(int currentHierarchicalLevel){
		Layer layer = null;
		for (Module mod : modules){
			if (mod instanceof Layer) {
				Layer l = (Layer)mod;
				if (l.getHierarchicalLevel() > currentHierarchicalLevel &&
						(layer == null || l.getHierarchicalLevel() < layer.getHierarchicalLevel())){
					layer = l;
				}
			}
		}
		return layer;
	}
	
	private void switchHierarchicalLayerLevels(Layer layerOne, Layer layerTwo){
		int hierarchicalLevelLayerOne = layerOne.getHierarchicalLevel();
		layerOne.setHierarchicalLevel(layerTwo.getHierarchicalLevel());
		layerTwo.setHierarchicalLevel(hierarchicalLevelLayerOne);
	}

	public ArrayList<Long> getAppliedRulesIdsByModule(long moduleId) {
		ArrayList<Long> appliedRuleIds = new ArrayList<Long>();
		for (AppliedRule rule : appliedRules){
			if (rule.getUsedModule().getId() == moduleId){
				appliedRuleIds.add(rule.getId());
			}
		}
		return appliedRuleIds;
	}

	public SoftwareUnitDefinition getSoftwareUnitByName(String softwareUnitName) {
		SoftwareUnitDefinition softwareUnit = null;
		for (Module mod : modules){
			if (mod.hasSoftwareUnit(softwareUnitName)){
				softwareUnit = mod.getSoftwareUnitByName(softwareUnitName);
				break;
			}
		}
		if (softwareUnit == null){ throw new RuntimeException("This Software Unit does not exist!");}
		return softwareUnit;
	}

	public Module getModuleByLogicalPath(String logicalPath) {
		
		//TODO bugfix if path is not correct
		String[] moduleNames = logicalPath.split("\\.");
		int i = 0;
		Module currentModule = null;
		for (Module module : modules){
			if (module.getName().equals(moduleNames[i])){
				currentModule = module;
				
				
				for (int j = i;j<moduleNames.length;j++){
					for (Module subModule : currentModule.getSubModules()){
						if (subModule.getName().equals(moduleNames[j])){
							currentModule = subModule;							
						}
					}
				}
			}
		}
		if (currentModule == null){ throw new RuntimeException("This module does not exist!");}
		return currentModule;
	}

	public String getLogicalPath(long moduleId) {
		String logicalPath = "";
		Module wantedModule =  getModuleById(moduleId);
		Module currentModule = null;
		
		for (Module mod : modules){
			if (mod.getName().equals(wantedModule.getName()) || 
					mod.hasSubModule(wantedModule.getName())){
				logicalPath += mod.getName();
				currentModule = wantedModule;
				
				while (!currentModule.getName().equals(wantedModule.getName())){
					for (Module subModule : currentModule.getSubModules()){
						if (subModule.getName().equals(wantedModule.getName()) ||
								subModule.hasSubModule(wantedModule.getName())){
							logicalPath += "." + mod.getName();
							currentModule = wantedModule;
						}
					}
				}
				break;
			}
		}
		return logicalPath;
	}
	
}
