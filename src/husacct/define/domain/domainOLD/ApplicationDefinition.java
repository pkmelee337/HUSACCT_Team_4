package husacct.define.domain.domainOLD;

import java.util.ArrayList;

public class ApplicationDefinition
{
	private static ApplicationDefinition instance;
	public static ApplicationDefinition getInstance()
	{
		return instance == null ? (instance = new ApplicationDefinition()) : instance;
	}
	
	
	//TODO: hmm waarom zat units ook alweer hier.. we gebruiken het volgens mij niet?
	private ArrayList<SoftwareUnitDefinition> units;
	
	private ApplicationDefinition()
	{
		super();
		
		units = new ArrayList<SoftwareUnitDefinition>();
	}
	
	
	public void addSoftwareUnit(String uniqueName, String type)
	{
		if(type.equals("PACKAGE"))
		{
			ApplicationDefinition.getInstance().addPackage(uniqueName);
		}
		else if(type.equals("CLASS"))
		{
			ApplicationDefinition.getInstance().addClass(uniqueName);
		}
		else
		{
			ApplicationDefinition.getInstance().addMethod(uniqueName);
		}
	}
	
	
	
	
	public boolean addPackage(String name)
	{
		//Logger.debug("ApplicationDefinition.addPackage(" + name + ")");
		
		String[] packages = name.split("::");
		
		String parentName = "";
		
		for(int i = 0; i < (packages.length - 1); i++)
		{
			parentName += packages[i];
		}

		SoftwareUnitDefinition parentPackage = null;
		
		if(!parentName.isEmpty())
		{
			parentPackage = findParentPackage(parentName);
		}
		
		String packageName = packages[(packages.length -1)];
		
		SoftwareUnitDefinition unit = new SoftwareUnitDefinition(packageName, SoftwareUnitDefinition.Type.PACKAGE);
		unit.setUniqueName(name);
		
		//TODO: bepalen waar dit moet:
		ArchitectureDefinition.getInstance().addUniqueName(name, unit);
		
		if(parentPackage != null)
		{
			//Logger.debug("\tAdding package to parent package \""+ parentPackage.getName() + "\"");
			
			parentPackage.addUnit(unit);
		
			return true;
		}
		else
		{
			return units.add(unit);
		}
	}
	
	public boolean addClass(String name)
	{
		SoftwareUnitDefinition unit = new SoftwareUnitDefinition(name, SoftwareUnitDefinition.Type.CLASS);
		
		return units.add(unit);
	}

	public boolean addMethod(String name)
	{
		SoftwareUnitDefinition unit = new SoftwareUnitDefinition(name, SoftwareUnitDefinition.Type.METHOD);
		
		return units.add(unit);
	}

	private SoftwareUnitDefinition findParentPackage(String packageName)
	{
		for(SoftwareUnitDefinition unit : units)
		{
			String uniqueName = unit.getUniqueName(); 
			
			if(uniqueName != null && uniqueName.equals(packageName))
			{
				return unit;
			}
		}
		
		return null;
	}
	
}