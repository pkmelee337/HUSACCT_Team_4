package husacct.define.domain.module;

import java.util.ArrayList;
import husacct.define.domain.SoftwareUnitDefinition;

public class Module {
	
	protected String name;
	protected String description;
	protected Type type;
	protected ArrayList<SoftwareUnitDefinition> units;

	public enum Type
	{
		//TODO insert types
	}
	
	public Module()
	{
		
	}
	
	public Module(String name, String description, Type type)
	{
		this.name = name;
		this.description = description;
		this.type = type;
		this.units = new ArrayList<SoftwareUnitDefinition>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String nm)
	{
		this.name = nm;
	}
	
	public ArrayList<SoftwareUnitDefinition> getSwUnit() {
		return units;
	}

	public void setSwUnit(ArrayList<SoftwareUnitDefinition> swUnit) {
		this.units = swUnit;
	}
	
	public void addSwuDefinition(SoftwareUnitDefinition unit)
	{
		units.add(unit);
	}
	
	public void removeSwuDefintion(SoftwareUnitDefinition unit)
	{
		units.remove(unit);
	}
	
	private boolean hasSwuDefinition(String name) 
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

}
