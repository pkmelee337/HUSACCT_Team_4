package husacct.define.domain.module;

public class Layer extends Module implements Comparable<Layer>{
	
	private int hierarchicalLevel;
	
	public Layer()
	{
		this("", "", -1);
	}

	public Layer(String name, int level)
	{
		this(name, "", level);
	}
	
	public Layer(String name, String description, int level)
	{
		super.name = name;
		super.description = description;
		super.type = "Layer";
		this.hierarchicalLevel = level;
	}
	
	public void setHierarchicalLevel(int hierarchicalLevel) {
		this.hierarchicalLevel = hierarchicalLevel;
	}

	public int getHierarchicalLevel() {
		return hierarchicalLevel;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (obj instanceof Layer){
	    	Layer l = (Layer)obj;
	    	if (l.hierarchicalLevel != this.hierarchicalLevel){
	    		return false;
	    	}
	    	return true;
	    }
	    return false;
	}

	@Override
	public int compareTo(Layer o) {
		int compareResult = 0;
		if (this.hierarchicalLevel > o.hierarchicalLevel){
			compareResult = 1;
		}else if (this.hierarchicalLevel < o.hierarchicalLevel){
			compareResult = -1;
		}else {
			compareResult = 0;
		}
		return compareResult;
	}
}
