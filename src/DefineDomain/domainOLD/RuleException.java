package DefineDomain.domainOLD;

public class RuleException 
{

	private String invokes;

	private String invokedBy;

	public RuleException(String invokes, String invokedBy) 
	{
		this.invokes = invokes;

		this.invokedBy = invokedBy;
	}

	public String getInvokes() 
	{
		return invokes;
	}

	public String getInvokedBy() 
	{
		return invokedBy;
	}

	/***
	 * Checks if the given uniqueName applies for an exception, for example:
	 * 
	 * invocation: nl.torpedo.Main.main() --> java.lang.String 
	 * exception : * --> java.*
	 */
	private boolean meets(String uniqueName, String exception) 
	{
		return 
			exception.equals("*") || 
			uniqueName.equals(exception) || 
			(exception.contains("*") ? uniqueName.startsWith(exception.substring(0, exception.indexOf("*"))) : false);
	}

	public boolean meetsException(String invokes, String invokedBy) 
	{
		return meets(invokes, getInvokes()) && meets(invokedBy, getInvokedBy());
	}
}
