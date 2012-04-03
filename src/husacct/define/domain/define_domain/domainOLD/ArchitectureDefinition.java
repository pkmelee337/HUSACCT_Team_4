package husacct.define.domain.define_domain.domainOLD;

import java.util.ArrayList;
import java.util.HashMap;

public class ArchitectureDefinition extends Definition
{
	private String name = "Architecture Definitie";
	private static ArchitectureDefinition instance = null;
	private ArrayList<LayerDefinition> layers;
	private HashMap<String, SoftwareUnitDefinition> uniqueNames; //cache that provides easy lookup for softwareunits by its uniquename
	
	public static ArchitectureDefinition getInstance() {
		return instance == null ? (instance = new ArchitectureDefinition()) : instance;
	}
	
	public void createNewInstance() {
		instance = new ArchitectureDefinition();
	}
	
	private ArchitectureDefinition()
	{
		super();
		
		layers = new ArrayList<LayerDefinition>();
		uniqueNames = new HashMap<String, SoftwareUnitDefinition>();
		
		// TODO: Find a nicer place to do this stuff: (all down to the java::* and javax::* !)
		ArrayList<RuleException> exceptions = new ArrayList<RuleException>();
		RuleException e;

		// Adding some default exceptions to ignore java or javax calls.
		e = new RuleException("java::*", "*");
		exceptions.add(e);
		e = new RuleException("javax::*", "*");
		exceptions.add(e);
		exceptionMap.put("backcall", exceptions);
		exceptionMap.put("skipcall", exceptions);
	}
	
	@Override
	public Definition[] getChildren() throws Exception
	{
		return layers.toArray(new Definition[layers.size()]);
	}
	
	// Layer related methods
	public void addLayer(String name, int level) throws Exception
	{
		if(hasLayer(name) || hasLayerAtLevel(level))
		{
			throw new Exception("Layer already exists or level already taken");
		}
		
		LayerDefinition layer = new LayerDefinition(name, level);
		layers.add(layer);
	}
	
	public void removeLayerByLevel(int layerLevel) throws Exception {
		LayerDefinition layer = this.getLayerByLevel(layerLevel);
		this.layers.remove(layer);
	}
	
	public LayerDefinition getLayerByName(String layerName) throws Exception {
		for(LayerDefinition layer : layers)
		{
			if(layer.getName().equals(layerName))
			{
				return layer;
			}
		}
		
		throw new Exception(String.format("Layer '%s' not found", layerName));
	}
	
	public LayerDefinition getLayerByLevel(int layerLevel) throws Exception {
		for(LayerDefinition layer : layers) {
			if(layer.getLevel() == (layerLevel)) {
				return layer;
			}
		}
		
		throw new Exception(String.format("No layer on level '%s'", layerLevel));
	}
	
	public String getLayerNameByLevel(int layerLevel) throws Exception {
		LayerDefinition layer = this.getLayerByLevel(layerLevel);
		return layer.getName();
	}
	
	public int getLevelForLayer(String layerName) throws Exception
	{		
		return getLayerByName(layerName).getLevel();
	}
	
	private boolean hasLayer(String name) 
	{
		for(LayerDefinition layer : layers) 
		{
			if(layer.getName().equals(name))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean hasLayerAtLevel(int level) 
	{
		for(LayerDefinition layer : layers) 
		{
			if(layer.getLevel() == level)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Integer> getLayerLevels() {
		ArrayList<Integer> levels = new ArrayList<Integer>();
		for(LayerDefinition layer : layers) {
			levels.add(layer.getLevel());
		}
		return levels;
	}

	// Unit related methods
	public void addUniqueName(String uniqueName, SoftwareUnitDefinition unit)
	{
		if (!uniqueNames.containsKey(uniqueName))
		{
			uniqueNames.put(uniqueName, unit);
		}
	}

	/***
	 * Searches for units by a given uniqueName (e.g.
	 * 'package::subPackage::class.method(param1. param2)')
	 * 
	 * @param uniqueName
	 * @return
	 * @throws Exception
	 */
	public SoftwareUnitDefinition getUnitByUniqueName(String uniqueName) throws Exception
	{
		String partialUniqueName = uniqueName;

		String[] splitted = partialUniqueName.split("::|\\.");

		for (int i = splitted.length; i > 1; i--)
		{
			if (uniqueNames.containsKey(partialUniqueName))
			{
				return uniqueNames.get(partialUniqueName);
			}

			int lastIndexOfA = partialUniqueName.lastIndexOf("::");
			int lastIndexOfB = partialUniqueName.lastIndexOf(".");

			int lastIndex = lastIndexOfA > lastIndexOfB ? lastIndexOfA : lastIndexOfB;

			partialUniqueName = partialUniqueName.substring(0, lastIndex);
		}

		throw new Exception("The unit (or a part of it) '" + uniqueName + "' is not found or defined.");
	}

	public LayerDefinition getLayerForUnit(SoftwareUnitDefinition unit) throws Exception
	{
		return getLayerForUnit(unit.getName());
	}

	public LayerDefinition getLayerForUnit(String name) throws Exception
	{
		for (LayerDefinition layer : layers)
		{
			if (layer.containsUnit(name))
			{
				return layer;
			}
		}

		throw new Exception("The layer for unit '" + name + "' could not be found.");
	}
	
	public String getLayerNameForUnitByUniqueName(String name) throws Exception 
	{
		SoftwareUnitDefinition unit = getUnitByUniqueName(name);
		
		LayerDefinition layer = unit.getLayer();
		
		return layer.getName();
	}
	
	public void setSoftwareUnitLayer(String uniqueName, String layerName) throws Exception
	{
		SoftwareUnitDefinition unit = getUnitByUniqueName(uniqueName);
		LayerDefinition layer = getLayerByName(layerName);
		
		unit.setLayer(layer);
	}

	// Rule and exception related methods
	public void addRule(String ruleName)
	{
		rules.add(ruleName);
	}
	
	public String[] getRules()
	{
		return rules.toArray(new String[rules.size()]);
	}
	
	public boolean hasException(String ruleName, String invokedBy, String invokes) 
	{
		ArrayList<RuleException> exceptions = exceptionMap.get(ruleName);

		if(exceptions == null)
		{
			return false;
		}
		
		for(RuleException e : exceptions)
		{			
			if(e.meetsException(invokes, invokedBy))
			{
				return true;
			}
		}
		
		return false;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}