package husacct.define.domain.define_domain.domain;

public class Application {
	
	private String name;
	private String[] paths;
	private String language;
	
	public Application()
	{
		this.setName("");
		this.setPaths(new String[1]);
		this.setLanguage("");
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
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}
	
	
	
	

}
