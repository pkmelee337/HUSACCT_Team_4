package husacct.define.domain.define_domain.domain;

import java.util.ArrayList;

import husacct.define.domain.define_domain.domain.module.Module;

public class SoftwareArchitecture {
	
	private String name;
	private String description;
	private ArrayList<Module> modules;
	private ArrayList<Application> applications;
	private ArrayList<AppliedRule> appliedRules;
	
	public SoftwareArchitecture() {
		
		setName("");
		setDescription("");
		setModules(new ArrayList<Module>());
		setApplications(new ArrayList<Application>());
	}
	
	public SoftwareArchitecture(String name, String description) {
		
		this.setName(name);
		this.setDescription(description);
		setModules(new ArrayList<Module>());
		setApplications(new ArrayList<Application>());
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}

	public ArrayList<Module> getModules() {
		return modules;
	}
	
	public void setApplications(ArrayList<Application> applications) {
		this.applications = applications;
	}

	public ArrayList<Application> getApplications() {
		return applications;
	}
	
	public void setAppliedRules(ArrayList<AppliedRule> appliedRules) {
		this.appliedRules = appliedRules;
	}

	public ArrayList<AppliedRule> getAppliedRules() {
		return appliedRules;
	}

	public void addModule(Module module)
	{
		modules.add(module);
	}
	
	public void removeModule(Module module)
	{
		modules.remove(module);
	}
	
	private boolean hasModule(String name) 
	{
		for(Module module : modules) 
		{
			if(module.getName().equals(name))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void addApplication(Application app)
	{
		applications.add(app);
	}
	
	public void removeApplication(Application app)
	{
		applications.remove(app);
	}
	
	private boolean hasApplication(String name) 
	{
		for(Application app : applications) 
		{
			if(app.getName().equals(name))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void addAppliedRule(AppliedRule rule)
	{
		appliedRules.add(rule);
	}
	
	public void removeAppliedRule(AppliedRule rule)
	{
		appliedRules.remove(rule);
	}
	
	private boolean hasAppliedRule(int id) 
	{
		for(AppliedRule rule : appliedRules) 
		{
			if(rule.getId() == id)
			{
				return true;
			}
		}
		
		return false;
	}
}
