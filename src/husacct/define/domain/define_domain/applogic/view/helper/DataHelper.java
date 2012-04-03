package husacct.define.domain.define_domain.applogic.view.helper;

public class DataHelper {
	private int intid;
	private long longid;
	private String value;

	public void setId(int id) {
		this.intid = id;
	}

	public void setId(long id) {
		this.longid = id;
	}

	public int getIntId() {		
		return intid;
	}
	
	public long getLongId(){
		return longid;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return getValue();
	}

}
