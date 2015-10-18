package xivvic.schema.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Immutable implementation of the VertexSchema interface
 * 
 * 
 * @author Reid
 *
 */
public class VertexSchemaImmutable
	implements VertexSchema
{
	private final VertexType type;
	private final Map<String, PropertySchema> properties;
	
   public VertexSchemaImmutable(VertexType type, List<PropertySchema> properties)
   {
   	if (type == null)
   		throw new IllegalArgumentException("Type required in constructor for node schema");
   	
   	if (properties == null)
   		throw new IllegalArgumentException("Property list required in constructor for node schema");
   	
   	this.type       = type;
   	this.properties = new HashMap<String, PropertySchema>();
   	
   	for (PropertySchema p : properties)
   	{
   		if (p == null)
   			continue;
   		
   		this.properties.put(p.key(), p);
   	}
   }

	public VertexType type()
	{
		return type;
	};
	
	public List<String> propertyNames()
	{
		return properties.values().stream().map(p -> p.name()).collect(Collectors.toList());
	}

	public List<String> propertyKeys()
	{
		return properties.values().stream().map(p -> p.key()).collect(Collectors.toList());
	}

	public List<PropertySchema> properties(PropertySchemaPredicate test)
	{
		if (test == null)
			return new ArrayList<PropertySchema>(properties.values());
		
		return properties.values().stream().filter(test).collect(Collectors.toList());
	}


	@Override
	public PropertySchema property(String key)
	{
		if (key ==null)
		{
			throw new IllegalArgumentException("Asking for a null property makes no sense.");
		}
		
		PropertySchema p = properties.get(key);
		if (p == null)
		{
			String msg = String.format("Unable to locate property definition for key [%s].", key);
			throw new IllegalArgumentException(msg);
		}
		
		return p;
	}

}
