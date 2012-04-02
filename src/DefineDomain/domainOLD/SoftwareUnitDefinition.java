package DefineDomain.domainOLD;

import java.util.ArrayList;

public class SoftwareUnitDefinition extends Definition
{

	public enum Type
	{
		PACKAGE, CLASS, METHOD
	}
	
	private ArrayList<SoftwareUnitDefinition> units;

	private String name;
	private Type type;
	
	private LayerDefinition layer;

	/**
	 * The complete FAMIX unique name of the definition, for easy lookup.
	 */
	private String uniqueName;

	public SoftwareUnitDefinition(String name, Type type)
	{
		super();
		
		units = new ArrayList<SoftwareUnitDefinition>();
		
		this.name = name;
		this.type = type;
	}
	
	@Override
	public Definition[] getChildren() throws Exception
	{
		return units.toArray(new Definition[units.size()]);
	}

	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	
	public Type getType()
	{
		return type;
	}
	public void setType(Type type)
	{
		this.type = type;
	}


	public String getUniqueName()
	{
		return uniqueName;
	}
	public void setUniqueName(String uniqueName)
	{
		this.uniqueName = uniqueName;
	}

	
	public LayerDefinition getLayer()
	{
		return layer;
	}	
	public void setLayer(LayerDefinition layer)
	{
		this.layer = layer;
	}
	
	
	public boolean addUnit(SoftwareUnitDefinition unit)
	{
		return units.add(unit);
	}
	public ArrayList<SoftwareUnitDefinition> getUnits()
	{
		return units;
	}
	public void setUnits(ArrayList<SoftwareUnitDefinition> units)
	{
		this.units = units;
	}

	
	//TODO: remove this?
	@Override
	public boolean equals(Object o)
	{
		SoftwareUnitDefinition u = (SoftwareUnitDefinition) o;
		
		return (u.type.equals(this.type) && u.name.equals(this.name));
	}

}