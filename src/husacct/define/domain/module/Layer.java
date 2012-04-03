package husacct.define.domain.module;

public class Layer extends Module {
	
	private int hierarchicalLevel;
	
	public Layer(int level)
	{
		super();
		this.setHierarchicalLevel(level);
	}
	
	public Layer()
	{
		super();
		setHierarchicalLevel(0);
	}
	
	public Layer(String name, String description)
	{
		super.name = name;
		super.description = description;
		super.type = "Layer";
	}

	public void setHierarchicalLevel(int hierarchicalLevel) {
		this.hierarchicalLevel = hierarchicalLevel;
	}

	public int getHierarchicalLevel() {
		return hierarchicalLevel;
	}

}
