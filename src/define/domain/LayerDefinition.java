package define.domain;

import java.util.ArrayList;

public class LayerDefinition extends Definition
{
	
	private ArrayList<SoftwareUnitDefinition> units = new ArrayList<SoftwareUnitDefinition>();;
	private ArrayList<LayerDefinition> callableLayers = new ArrayList<LayerDefinition>();;
	
	private String name;
	private int level;

	public LayerDefinition(String name, int level)
	{
		super();
		
		this.name = name;
		this.level = level;
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
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level) throws Exception 
	{
		if(level < 0)
		{
			throw new Exception("Level cannot be lower than zero");
		}
		
		this.level = level;
	}
	
	public void addUnit(SoftwareUnitDefinition unit)
	{
		units.add(unit);
	}
	
	public boolean containsUnit(String unit) 
	{
		for(SoftwareUnitDefinition un : units)
		{
			if(un.getName().equals(unit))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean containsUnit(SoftwareUnitDefinition unit)
	{
		return units.contains(unit);
	}
	
	public SoftwareUnitDefinition findUnit(String searchPackage)
	{
		for(SoftwareUnitDefinition un : units)
		{
			if(un.getName().equals(searchPackage))
			{
				return un;
			}
		}
		return null;
	}
	
	public void addCallableLayer(LayerDefinition l)
	{
		callableLayers.add(l);
	}

	public boolean canCallLayer(LayerDefinition l)
	{
		return callableLayers.contains(l);
	}
	
	public ArrayList<LayerDefinition> getCallableLayers()
	{
		return callableLayers;
	}
	
	public void setCallableLayers(ArrayList<LayerDefinition> callableLayers)
	{
		this.callableLayers = callableLayers;
	}

	@Override
	public String toString()
	{
		return name;
	}
}