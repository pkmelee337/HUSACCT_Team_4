package husacct.define.domain;

import java.util.ArrayList;

import husacct.define.domain.domainOLD.ArchitectureDefinition;
import husacct.define.domain.module.*;

public class DefineDomainService {
	private static DefineDomainService instance = null;
	public static DefineDomainService getInstance() {
		return instance == null ? (instance = new DefineDomainService()) : instance;
	}
	
	public DefineDomainService() {
		
	}
	
	public void addLayer(String name, int level) {
		Module layer = new Layer(name, level);
		((Layer) layer).setHierarchicalLevel(level);
		SoftwareArchitecture.getInstance().addModule(layer);
	}
	
	public void removeLayerByLevel(int layerLevel) {
		SoftwareArchitecture.getInstance().removeLayerByLevel(layerLevel);
	}
	
	public void moveLayerUp(int moduleId){
		SoftwareArchitecture.getInstance().moveUpDown(moduleId);
	}
	
	public void moveLayerDown(int moduleId){
		SoftwareArchitecture.getInstance().moveLayerDown(moduleId);
	}
	
	public void createNewArchitectureDefinition(String name) {
		SoftwareArchitecture.getInstance().setName(name);
	}

	public void setLayerName(long moduleId, String newName) {
		SoftwareArchitecture.getInstance().setModuleName(moduleId, newName);
	}

	public ArrayList<Integer> getLayerLevels() {
		return SoftwareArchitecture.getInstance().getLevelFromLayers();
	}

	public String getLayerNameByLevel(int layerLevel) {
		return SoftwareArchitecture.getInstance().getLayerNameByLevel(layerLevel);
	}

	public void removeAppliedRule(int appliedrule_id) {
		SoftwareArchitecture.getInstance().removeAppliedRule(appliedrule_id);
		
	}

	public String getRuleTypeByAppliedRule(int appliedrule_id) {
		return SoftwareArchitecture.getInstance().getRuleTypeByAppliedRule(appliedrule_id);
	}

	public ArrayList<Long> getAppliedRuleExceptions(int layerID, long appliedrule_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAppliedruleExceptionName(int layerID, long appliedrule_id,long exception_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAppliedruleExceptionType(int layerID, long appliedrule_id,
			long exception_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public long addAppliedRule(String ruleType, String description, long moduleFromId, long moduleToId) {
		Module moduleFrom = SoftwareArchitecture.getInstance().getModuleById(moduleFromId);
		Module moduleTo = SoftwareArchitecture.getInstance().getModuleById(moduleToId);

		AppliedRule rule = new AppliedRule(ruleType,description, moduleFrom, moduleTo);
		SoftwareArchitecture.getInstance().addAppliedRule(rule);
		return rule.getId();
	}

	public void setAppliedRuleIsEnabled(long appliedRuleId, boolean enabled) {
		SoftwareArchitecture.getInstance().setAppliedRuleIsEnabled(appliedRuleId, enabled);
	}

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
}
