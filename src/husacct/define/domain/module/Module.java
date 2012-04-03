package husacct.define.domain.module;

import java.util.ArrayList;

import husacct.define.domain.SoftwareUnitDefinition;

public class Module {
	
	protected String name;
	protected String description;
	protected String type;
	protected ArrayList<SoftwareUnitDefinition> units;
	protected ArrayList<Module> subModules;
	
	public Module()
	{
		
	}

	public Module(String description) {
		this.description = description;
		this.type = "Module";
	}

	public Module(String name, String description)
	{
		this.name = name;
		this.description = description;
		this.type = "Module";
		this.units = new ArrayList<SoftwareUnitDefinition>();
		this.subModules = new ArrayList<Module>();
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<SoftwareUnitDefinition> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<SoftwareUnitDefinition> units) {
		this.units = units;
	}

	public ArrayList<Module> getSubModules() {
		return subModules;
	}

	public void setSubModules(ArrayList<Module> subModules) {
		this.subModules = subModules;
	}
	
	//SoftwareUnitDefinition
	public void addSwuDefinition(SoftwareUnitDefinition unit)
	{
		units.add(unit);
	}
	
	public void removeSwuDefintion(SoftwareUnitDefinition unit)
	{
		units.remove(unit);
	}
	
	private boolean hasSUDefinition(String name) 
	{
		for(SoftwareUnitDefinition unit : units) 
		{
			if(unit.getName().equals(name))
			{
				return true;
			}
		}
		
		return false;
	}
	
	//Module
	public void addSubModule(Module subModule)
	{
		subModules.add(subModule);
	}
	
	public void removeSubModule(Module subModule)
	{
		subModules.remove(subModule);
	}
	
	private boolean hasSubModule(String name) 
	{
		for(Module subModule : subModules) 
		{
			if(subModule.getName().equals(name))
			{
				return true;
			}
		}
		
		return false;
	}

}
