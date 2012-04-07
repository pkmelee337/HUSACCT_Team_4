package husacct.define.domain;

import java.util.ArrayList;
import java.util.Collections;

import husacct.define.domain.module.*;

public class DefineDomainService {
	private static DefineDomainService instance = null;
	public static DefineDomainService getInstance() {
		return instance == null ? (instance = new DefineDomainService()) : instance;
	}
	
	public DefineDomainService() {
		
	}
	
	//GENERIC
	public void createNewArchitectureDefinition(String name) {
		SoftwareArchitecture.getInstance().setName(name);
	}
	
	//MODULES
	//MODULES
	public String getModuleNameById(long moduleId) {
		Module module = SoftwareArchitecture.getInstance().getModuleById(moduleId);
		String moduleName = module.getName();
		return moduleName;
	}

	public void removeModuleById(long moduleId) {
		Module module = SoftwareArchitecture.getInstance().getModuleById(moduleId);
		SoftwareArchitecture.getInstance().removeModule(module);
	}
	
	//LAYERS
	//LAYERS
	//LAYERS
	public long addLayer(String name, int level) {
		Module layer = new Layer(name, level);
		((Layer) layer).setHierarchicalLevel(level);
		long moduleId = SoftwareArchitecture.getInstance().addModule(layer);
		return moduleId;
	}
	
	public void setLayerName(long moduleId, String newName) {
		SoftwareArchitecture.getInstance().setModuleName(moduleId, newName);
	}

	public void moveLayerUp(long layerId){
		SoftwareArchitecture.getInstance().moveUpDown(layerId);
	}
	
	public void moveLayerDown(long layerId){
		SoftwareArchitecture.getInstance().moveLayerDown(layerId);
	}
	
	public ArrayList<Long> getLayerIdsSorted() {
		ArrayList<Module> rootModules = SoftwareArchitecture.getInstance().getRootModules();
		ArrayList<Layer> layers = new ArrayList<Layer>();
		for (Module m : rootModules){
			if (m instanceof Layer){
				layers.add((Layer)m);
			}
		}
		Collections.sort(layers);
		ArrayList<Long> sortedLayerIds = new ArrayList<Long>();
		for (Layer l : layers){
			sortedLayerIds.add(l.getId());
		}
		return sortedLayerIds;
	}
	
	//APPLIED RULES	
	//APPLIED RULES
	//APPLIED RULES
	public long addAppliedRule(String ruleType, String description, long moduleFromId, long moduleToId) {
		Module moduleFrom = SoftwareArchitecture.getInstance().getModuleById(moduleFromId);
		Module moduleTo = SoftwareArchitecture.getInstance().getModuleById(moduleToId);

		AppliedRule rule = new AppliedRule(ruleType,description, moduleFrom, moduleTo);
		SoftwareArchitecture.getInstance().addAppliedRule(rule);
		return rule.getId();
	}
	
	public void removeAppliedRule(int appliedrule_id) {
		SoftwareArchitecture.getInstance().removeAppliedRule(appliedrule_id);
	}

	public String getRuleTypeByAppliedRule(long appliedruleId) {
		return SoftwareArchitecture.getInstance().getRuleTypeByAppliedRule(appliedruleId);
	}

	public void setAppliedRuleIsEnabled(long appliedRuleId, boolean enabled) {
		SoftwareArchitecture.getInstance().setAppliedRuleIsEnabled(appliedRuleId, enabled);
	}
	
	public ArrayList<Long> getAppliedRulesIdsByModule(long moduleId) {
		return SoftwareArchitecture.getInstance().getAppliedRulesIdsByModule(moduleId);
	}

	public long getModuleToIdOfAppliedRule(long appliedRuleId) {
		AppliedRule rule = SoftwareArchitecture.getInstance().getAppliedRuleById(appliedRuleId);
		Long moduleToId = rule.getRestrictedModule().getId();
		return moduleToId;
	}
	
	public boolean getAppliedRuleIsEnabled(long appliedRuleId) {
		AppliedRule rule = SoftwareArchitecture.getInstance().getAppliedRuleById(appliedRuleId);
		boolean isEnabled = rule.isEnabled();
		return isEnabled;
	}

	//APPLIED RULE EXCEPTIONS
	//APPLIED RULE EXCEPTIONS
	//APPLIED RULE EXCEPTIONS
	public void addExceptionToAppliedRule(long parentRuleId, String ruleType, String description, long moduleFromId, long moduleToId) {
		Module moduleFrom = SoftwareArchitecture.getInstance().getModuleById(moduleFromId);
		Module moduleTo = SoftwareArchitecture.getInstance().getModuleById(moduleToId);

		AppliedRule rule = new AppliedRule(ruleType,description, moduleFrom, moduleTo);
		SoftwareArchitecture.getInstance().addExceptionToAppliedRule(parentRuleId, rule);
		
	}
	
	public void removeAppliedRuleException(long parentRuleId, long appliedRuleId) {
		SoftwareArchitecture.getInstance().removeAppliedRuleException(parentRuleId, appliedRuleId);
	}

	public void removeAppliedRuleExceptions(long appliedRuleId) {
		SoftwareArchitecture.getInstance().removeAppliedRuleExceptions(appliedRuleId);
	}

	public ArrayList<Long> getAppliedRuleExceptions(long l, long appliedrule_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//SOFTWARE UNIT DEFINITION
	//SOFTWARE UNIT DEFINITION
	//SOFTWARE UNIT DEFINITION
	public String getSoftwareUnitName(long layerId, long softwareUnit_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Long> getSoftwareUnitExceptions(long layerId,
			long softwareUnit_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getSoftwareUnitType(long layerId, long softwareUnit_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Long> getSoftwareUnits(long layerId) {
		// TODO Auto-generated method stub
		return null;
	}
}
