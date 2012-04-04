package husacct.define.domain;

public class Application {
	
	private String name;
	private String[] paths;
	private String programmingLanguage;
	private SoftwareArchitecture architecture;
	
	public Application()
	{
		this.setName("");
		this.setPaths(new String[1]);
		this.setLanguage("");
		this.architecture = new SoftwareArchitecture();
	}
	
	public Application(String name, String lang)
	{
		this.setName(name);
		this.setLanguage(lang);
		this.architecture = new SoftwareArchitecture();
	}
	
	public Application(String name, String[] paths, String lang)
	{
		this.setName(name);
		this.setPaths(paths);
		this.setLanguage(lang);
		this.architecture = new SoftwareArchitecture();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPaths(String[] paths) {
		this.paths = paths;
	}

	public String[] getPaths() {
		return paths;
	}

	public void setLanguage(String language) {
		this.programmingLanguage = language;
	}

	public String getLanguage() {
		return programmingLanguage;
	}
	
	
	
	

}
