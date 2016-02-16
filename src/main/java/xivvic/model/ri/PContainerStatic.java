package xivvic.model.ri;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import xivvic.model.api.PContainer;
import xivvic.model.api.PModel;
import xivvic.model.api.PModelPredicate;

public class PContainerStatic
	implements PContainer
{
	private final Map<String, PModel> properties;
	
   public PContainerStatic(List<PModel> properties)
   {
   	if (properties == null)
   		throw new IllegalArgumentException("Property list required in constructor for schema");
   	
   	this.properties = new HashMap<String, PModel>();
   	
   	for (PModel p : properties)
   	{
   		this.properties.put(p.key(), p);
   	}
   }

	public List<String> propertyNames()
	{
		return properties.values()
				.stream()
				.map(p -> p.name())
				.collect(Collectors.toList());
	}

	public List<String> propertyKeys()
	{
		return properties.values()
				.stream()
				.map(p -> p.key())
				.collect(Collectors.toList());
	}

	/**
	 * Returns all the schemas which pass the test. If the test parameter
	 * is null, this method will return all schemas.
	 * 
	 * @param test optional filter predicate
	 * @return schemas which pass the filter or all schemas if filter is null
	 */
	public List<PModel> properties(PModelPredicate test)
	{
		if (test == null)
			test = PModelPredicate.TRUE;
			
		return properties.values()
				.stream()
				.filter(test)
				.collect(Collectors.toList());
	}

	public PModel property(String key)
	{
		if (key ==null)
		{
			throw new IllegalArgumentException("Asking for a null property makes no sense.");
		}
		
		PModel p = properties.get(key);
		if (p == null)
		{
			String msg = String.format("Unable to locate property definition for key [%s].", key);
			throw new IllegalArgumentException(msg);
		}
		
		return p;
	}
}
