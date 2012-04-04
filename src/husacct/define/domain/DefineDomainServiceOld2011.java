package husacct.define.domain;

import java.util.ArrayList;
import java.util.HashMap;

import husacct.define.domain.domainOLD.ApplicationDefinition;
import husacct.define.domain.domainOLD.ArchitectureDefinition;
import husacct.define.domain.domainOLD.SoftwareUnitDefinition;

public class DefineDomainServiceOld2011 {
	private static DefineDomainServiceOld2011 instance = null;
	public static DefineDomainServiceOld2011 getInstance() {
		return instance == null ? (instance = new DefineDomainServiceOld2011()) : instance;
	}
	
	public DefineDomainServiceOld2011() {
		
	}
	
//	public void addLayer(String name, int level) throws Exception
//	{
//		ArchitectureDefinition.getInstance().addLayer(name, level);
//	}
	
//	public void removeLayerByLevel(int layerLevel) throws Exception {
//		ArchitectureDefinition.getInstance().removeLayerByLevel(layerLevel);
//	}
	
	public int getLevelForLayer(String layerName) throws Exception
	{
		return ArchitectureDefinition.getInstance().getLevelForLayer(layerName);
	}
	
	public String getLayerNameByLevel(int layerLevel) throws Exception
	{
		return ArchitectureDefinition.getInstance().getLayerNameByLevel(layerLevel);
	}
	
	public ArrayList<Integer> getLayerLevels() {
		return ArchitectureDefinition.getInstance().getLayerLevels();
	}
	
	public void setLayerName(int layer_level, String newName) throws Exception {
		ArchitectureDefinition.getInstance().changeLayerName(layer_level, newName);
	}
	
	public void addSoftwareUnit(String uniqueName, String type)
	{		
		ApplicationDefinition.getInstance().addSoftwareUnit(uniqueName, type);
	}
	
	public void addUniqueName(String name, SoftwareUnitDefinition unit) {
		ArchitectureDefinition.getInstance().addUniqueName(name, unit);
	}
	
	public void setSoftwareUnitLayer(String uniqueName, int layerLevel) throws Exception
	{
		ArchitectureDefinition.getInstance().setSoftwareUnitLayer(uniqueName, layerLevel);
	}
	
	public String getLayerForSoftwareUnit(String uniqueName) throws Exception
	{
		return ArchitectureDefinition.getInstance().getLayerNameForUnitByUniqueName(uniqueName);
	}

	public void addRule(String ruleName)
	{
		ArchitectureDefinition.getInstance().addRule(ruleName);
	}
	
	public String[] getRules()
	{
		return ArchitectureDefinition.getInstance().getRules();
	}
	
	/**
	 * Check if an unit has an exception.
	 * @param String ruleName 
	 * @param String invokedBy, the uniqueName of the unit that is invoking the call.
	 * @param String invokes,  
	 * @return
	 */
	public boolean hasException(String ruleName, String invokedBy, String invokes) {
		return ArchitectureDefinition.getInstance().hasException(ruleName, invokedBy, invokes);
	}

	/* 
	 * TODO: Define whether you want to add the exception to the architecture or application. Some future 
	 * exceptions might be application related and not architecture.
	 */ 
	/**
	 * Add an exception to one of the validation rules.
	 */
	public void addException(String rule, HashMap<String, String> properties) {
		ArchitectureDefinition.getInstance().addException(rule, properties);
	}
	
	public String getArchitectureDefinitionName() {
		return ArchitectureDefinition.getInstance().getName();
	}
	
	public void createNewArchitectureDefinition(String name) {
		ArchitectureDefinition architectureDefinition = ArchitectureDefinition.getInstance();
		architectureDefinition.createNewInstance();
		architectureDefinition.setName(name);
	}

	public Object getAppliedRuleRuleType(int layerID, long appliedrule_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAppliedRuleToLayer(int layerID, long appliedrule_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getLayerName(Object appliedRuleToLayer) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getAppliedRuleIsEnabled(int layerID, long appliedrule_id) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Long> getAppliedRuleExceptions(int layerID,
			long appliedrule_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAppliedruleExceptionName(int layerID, long appliedrule_id,
			long exception_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAppliedruleExceptionType(int layerID, long appliedrule_id,
			long exception_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Integer> getLayers() {
		// TODO Auto-generated method stub
		return null;
	}

	public long newAppliedRule(int layerID, int intId, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setAppliedRuleIsEnabled(int layerID, long appliedrule_id,
			boolean selected) {
		// TODO Auto-generated method stub
		
	}

	public void newAppliedRuleException(int layerID, long appliedrule_id,
			String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public void setAppliedRuleToLayer(int layerID, long appliedrule_id,
			int intId) {
		// TODO Auto-generated method stub
		
	}

	public void setAppliedRuleRuleType(int layerID, long appliedrule_id,
			String string) {
		// TODO Auto-generated method stub
		
	}

	public void removeAppliedRuleExceptions(int layerID, long appliedrule_id) {
		// TODO Auto-generated method stub
		
	}
}

