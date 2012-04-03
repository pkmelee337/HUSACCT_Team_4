package husacct.define.domain;

import java.util.ArrayList;

import husacct.define.domain.module.Module;

public class AppliedRule {
	
	private int id;
	private String description;
	private String[] dependencies;
	private String prefix;
	private String suffix;
	private Module usedModule;
	private Module restrictedModule;
	private ArrayList<AppliedRule> exceptions;	

	public AppliedRule(int id, String description, String[] dependencies,
			String prefix, String suffix, Module usedModule,
			Module restrictedModule) {
		this.id = id;
		this.description = description;
		this.dependencies = dependencies;
		this.prefix = prefix;
		this.suffix = suffix;
		this.usedModule = usedModule;
		this.restrictedModule = restrictedModule;
		this.exceptions = new ArrayList<AppliedRule>();
	}

	public AppliedRule() {
		
		this.id = 0;
		this.description = "";
		this.dependencies = new String[1000];
		this.prefix = "";
		this.suffix = "";
		this.usedModule = null;
		this.restrictedModule = null;
		this.exceptions = new ArrayList<AppliedRule>();
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getDescription() {
		return description;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public void setExceptions(ArrayList<AppliedRule> exceptions) {
		this.exceptions = exceptions;
	}


	public ArrayList<AppliedRule> getExceptions() {
		return exceptions;
	}

	public void setUsedModule(Module usedModule) {
		this.usedModule = usedModule;
	}


	public Module getUsedModule() {
		return usedModule;
	}


	public void setRestrictedModule(Module restrictedModule) {
		this.restrictedModule = restrictedModule;
	}


	public Module getRestrictedModule() {
		return restrictedModule;
	}

	public void setDependencies(String[] dependencies) {
		this.dependencies = dependencies;
	}

	public String[] getDependencies() {
		return dependencies;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}
	
	public void addException(AppliedRule exception)
	{
		exceptions.add(exception);
	}
	
	public void removeException(AppliedRule exception)
	{
		exceptions.remove(exception);
	}
	
	private boolean hasException(int id) 
	{
		for(AppliedRule exception : exceptions) 
		{
			if(exception.getId() == id)
			{
				return true;
			}
		}
		
		return false;
	}

}
