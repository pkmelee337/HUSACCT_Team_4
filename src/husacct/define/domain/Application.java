package husacct.define.domain;

public class Application {
	
	private String name;
	private String[] paths;
	private String programmingLanguage;
	
	public Application()
	{
		this.setName("");
		this.setPaths(new String[1]);
		this.setLanguage("");
	}
	
	public Application(String name, String lang)
	{
		this.setName(name);
		this.setLanguage(lang);
	}
	
	public Application(String name, String[] paths, String lang)
	{
		this.setName(name);
		this.setPaths(paths);
		this.setLanguage(lang);
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
