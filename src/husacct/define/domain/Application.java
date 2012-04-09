package husacct.define.domain;

public class Application {
	
	private String name;
	private String[] paths;
	private String programmingLanguage;
	private SoftwareArchitecture architecture;
	
	private static Application instance = null;
	public static Application getInstance()
	{
		return instance == null ? (instance = new Application()) : instance;
	}
	
	public static void setInstance(Application app)
	{
		instance = app;
	}
	
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

	public SoftwareArchitecture getArchitecture() {
		return architecture;
	}

	public void setArchitecture(SoftwareArchitecture architecture) {
		this.architecture = architecture;
	}
	
	
	
	

}
