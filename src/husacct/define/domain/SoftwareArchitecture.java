package husacct.define.domain;

import java.util.ArrayList;

import husacct.define.domain.module.Module;

public class SoftwareArchitecture {
	
	private String name;
	private String description;
	private ArrayList<Module> modules;
	private ArrayList<Application> applications;
	private ArrayList<AppliedRule> appliedRules;
	
	private static SoftwareArchitecture instance = null;
	public static SoftwareArchitecture getInstance()
	{
		return instance == null ? (instance = new SoftwareArchitecture()) : instance;
	}
	
	public SoftwareArchitecture() {
		
		setName("");
		setDescription("");
		setModules(new ArrayList<Module>());
		setApplications(new ArrayList<Application>());
		setAppliedRules(new ArrayList<AppliedRule>());
	}
	
	public SoftwareArchitecture(String name, String description) {
		
		this.setName(name);
		this.setDescription(description);
		setModules(new ArrayList<Module>());
		setApplications(new ArrayList<Application>());
		setAppliedRules(new ArrayList<AppliedRule>());
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

	
	//Module
	public void addModule(Module module)
	{
		if(!modules.contains(module) && !this.hasModule(module.getName())) {
			modules.add(module);
		}else{
			System.out.println("This module has already been addded!");
		}
	}
	
	//New method removeModule/LayerByLevel
	public void removeModule(Module module)
	{
		if(modules.contains(module) && this.hasModule(module.getName())) {
			modules.remove(module);
		}//else if(module.getClass().isInstance(arg0)) {
			
		//}
		else{
			System.out.println("This module does not exist!");
		}
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
	
	
	//Application
	public void addApplication(Application app)
	{
		if(!applications.contains(app) && !this.hasApplication(app.getName())) {
			applications.add(app);
		}
		else{
			System.out.println("This application has already been added!");
		}
	}
	
	public void removeApplication(Application app)
	{
		if(applications.contains(app) && this.hasApplication(app.getName())) {
			applications.remove(app);
		}
		else{
			System.out.println("This application does not exist!");
		}
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
	
	
	//AppliedRule
	public void addAppliedRule(AppliedRule rule)
	{
		if(!appliedRules.contains(rule) && !this.hasAppliedRule(rule.getId()))
		{
			appliedRules.add(rule);
		}else{
			System.out.println("This rule has already been added!");
		}
	}
	
	public void removeAppliedRule(AppliedRule rule)
	{
		if(appliedRules.contains(rule) && this.hasAppliedRule(rule.getId()))
		{
			appliedRules.remove(rule);
		}else{
			System.out.println("This rule does not exist!");
		}
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

	public void removeLayerByLevel(int layerLevel) {
		// TODO Auto-generated method stub
		
	}
}
