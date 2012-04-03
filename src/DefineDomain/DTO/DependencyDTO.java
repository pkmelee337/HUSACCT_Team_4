package DefineDomain.DTO;

public class DependencyDTO {

	public String from;
	public String to;
	public String type;
	public int lineNumber;

	public DependencyDTO(String from, String to, String type, int lineNumber){
		this.from = from;
		this.to = to;
		this.type = type;
		this.lineNumber = lineNumber;
	}
}