package define.applogic;

import java.util.HashMap;

import define.domainOLD.ApplicationDefinition;
import define.domainOLD.ArchitectureDefinition;

public class DefinitionService
{
	public DefinitionService(){}
	
	public void addLayer(String name, int level) throws Exception
	{
		ArchitectureDefinition.getInstance().addLayer(name, level);
	}
	
	public int getLevelForLayer(String layerName) throws Exception
	{
		return ArchitectureDefinition.getInstance().getLevelForLayer(layerName);
	}
	
	public void addSoftwareUnit(String uniqueName, String type)
	{		
		ApplicationDefinition.getInstance().addSoftwareUnit(uniqueName, type);
	}
	
	public void setSoftwareUnitLayer(String uniqueName, String layerName) throws Exception
	{
		ArchitectureDefinition.getInstance().setSoftwareUnitLayer(uniqueName, layerName);
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
	
}

