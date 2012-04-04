package husacct.define.domain.module;

public class Layer extends Module {
	
	private int hierarchicalLevel;
	
	public Layer(String name)
	{
		super.name = name;
		super.type = "Layer";
	}
	
	public Layer()
	{
		super();
		super.type = "Layer";
	}

	public Layer(String name, String description, int level)
	{
		super.name = name;
		super.description = description;
		super.type = "Layer";
		this.hierarchicalLevel = level;
	}
	
	public Layer(String name, int level)
	{
		super.name = name;
		this.hierarchicalLevel = level;
		super.type = "Layer";
	}

	public void setHierarchicalLevel(int hierarchicalLevel) {
		this.hierarchicalLevel = hierarchicalLevel;
	}

	public int getHierarchicalLevel() {
		return hierarchicalLevel;
	}

}
