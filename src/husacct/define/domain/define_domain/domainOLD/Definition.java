package husacct.define.domain.define_domain.domainOLD;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Definition 
{
	
	ArrayList<String> rules = new ArrayList<String>();;

	HashMap<String, ArrayList<RuleException>> exceptionMap = new HashMap<String, ArrayList<RuleException>>();;

	public Definition() 
	{
		
	}

	public abstract Definition[] getChildren() throws Exception;

	public void addLayer(String name, int level) throws Exception
	{
		ArchitectureDefinition.getInstance().addLayer(name, level);
	}
	
	public void addException(String rule, HashMap<String, String> properties) 
	{
		ArrayList<RuleException> ruleExceptions = exceptionMap.get(rule);
		
		if(ruleExceptions == null)
		{
			ruleExceptions = new ArrayList<RuleException>();
		}
		
		RuleException r = new RuleException(
				makeFamixCompatibleUniqueName(properties.get("invoked")), 
				makeFamixCompatibleUniqueName(properties.get("invoker")));
		
		ruleExceptions.add(r);
	}

	/**
	 * Create a unique name that is compatible with the FAMIX model.
	 * E.g. convert "foo.bar.SomeClass.someMethod()" to "foo::bar::SomeClass.someMethod()"
	 * @param str
	 * @return String the unique name.
	 */
	private String makeFamixCompatibleUniqueName(String str) 
	{
		String un = "";
		
		String[] parts = str.split("\\.");
		
		for(int i = 0; i < parts.length; i++)
		{
			String part = parts[i];
			
			if(un.isEmpty())
			{
				un = part;
			}
			else
			{
				un += (part.endsWith("()")) ? "." : "::";
				un += part;
			}
		}
		
		return un;
	}
}