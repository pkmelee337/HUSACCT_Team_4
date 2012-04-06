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
	public void addModule(Module module)
	{
		if(!modules.contains(module) && !this.hasModule(module.getName())) {
			modules.add(module);
		}else{
			throw new RuntimeException("This module has already been addded!");
		}
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
		if(modules.contains(module) && this.hasModule(module.getName())) {
			modules.remove(module);
		}
		else{
			throw new RuntimeException("This module does not exist!");
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
	
	public ArrayList<Integer> getLevelFromLayers()
	{
		ArrayList<Integer> integerList = new ArrayList<Integer>();
		for(Module layer : modules)
		{
			if(layer instanceof Layer)
			{
				integerList.add(((Layer) layer).getHierarchicalLevel());
			}
		}
		return integerList;
	}
	
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

	public String getRuleTypeByAppliedRule(int appliedRuleId) {
		AppliedRule rule = getAppliedRuleById(appliedRuleId);
		return rule.getRuleType();
	}
	
	private AppliedRule getAppliedRuleById(long appliedRuleId){
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
		Module module = null;
		if(this.hasModule(moduleId))
		{
			for(Module mod : modules) 
			{
				if(mod.getId() == moduleId)
				{
					module = mod;
					break;
				}
			}		
		}else{
			throw new RuntimeException("This module does not exist!");
		}
		return module;
	}

	private boolean hasModule(long moduleFromId) {
		for(Module module : modules) 
		{
			if(module.getId() == moduleFromId)
			{
				return true;
			}
		}
		return false;
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

	public void moveUpDown(int moduleId) {
		Layer layer = (Layer)getModuleById(moduleId);
		Layer layerAboveLayer = getTheFirstLayerAbove(layer.getHierarchicalLevel());
		switchHierarchicalLayerLevels(layer, layerAboveLayer);
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

	public void moveLayerDown(int moduleId) {
		Layer layer = (Layer)getModuleById(moduleId);
		Layer layerBelowLayer = getTheFirstLayerBelow(layer.getHierarchicalLevel());
		switchHierarchicalLayerLevels(layer, layerBelowLayer);
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
	
}
