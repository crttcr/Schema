package xivvic.model.ri;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import xivvic.model.api.PContainer;
import xivvic.model.api.PModel;
import xivvic.model.api.PModelPredicate;

public class PContainerStatic
	implements PContainer
{
	private final Map<String, PModel> properties;
	private final PModel id_property;
	
	// Used to keep track of ordinal values while defining properties
	//
	private int ordinal_counter = 0;
	
   public PContainerStatic(List<PModel> properties, PModel id_property)
   {
   	Objects.requireNonNull(properties, "Property list required in constructor for schema");
   	
   	this.properties = new HashMap<String, PModel>();
   	this.id_property = id_property;
   	
   	// Ensure that if there is an ID property, then it is contained in list of properties
   	//
   	if (id_property != null)
   	{
   		if (! properties.contains(id_property))
   			throw new IllegalArgumentException("ID Property must be contained in the property list provided to this container");

   		if (id_property.unique() == false)
   			throw new IllegalArgumentException("Identity property must be unique");
  	}
   	
   	for (PModel p : properties)
   	{
   		// Make sure every property has an ordinal value. Incrementing in entry order
   		// and resetting whenever an encountered property has its own ordinal value.
   		//
   		Integer ord = p.ordinal();
   		if (ord != null)
   		{
   			ordinal_counter = ord.intValue();
   		}
   		else
   		{
   			p = new PModelOrdinalWrapper(p, ++ordinal_counter);
   		}
   		this.properties.put(p.key(), p);
   	}
   }

	public List<String> propertyNames()
	{
		return properties.values()
				.stream()
				.sorted(PModel.COMPARE_BY_ORDINAL)
				.map(p -> p.name())
				.collect(Collectors.toList());
	}

	public List<String> propertyKeys()
	{
		return properties.values()
				.stream()
				.sorted(PModel.COMPARE_BY_ORDINAL)
				.map(p -> p.key())
				.collect(Collectors.toList());
	}

	/**
	 * Returns all the property models which pass the test. If the test parameter
	 * is null, this method will return all property models.
	 * 
	 * @param test optional filter predicate
	 * @return schemas which pass the filter or all property models if filter is null
	 */
	public List<PModel> properties(PModelPredicate test)
	{
		if (test == null)
			test = PModelPredicate.TRUE;
			
		return properties.values()
				.stream()
				.sorted(PModel.COMPARE_BY_ORDINAL)
				.filter(test)
				.collect(Collectors.toList());
	}

	@Override
	public Set<String> requiredPropertyKeys()
	{
		PModelPredicate filter = new PropertyRequired();
		Function<PModel, String> map = (m) -> { return m.key(); };
			
		return properties.values()
				.stream()
				.sorted(PModel.COMPARE_BY_ORDINAL)
				.filter(filter)
				.map(map)
				.collect(Collectors.toSet());
	}

	/**
	 * Returns the single property with a key matching the parameter, if it
	 * exists. If such a property doesn't exist, this method returns null.
	 * 
	 * @throws IllegalArgumentException if the requested key is null.
	 * 
	 * @return The model for the property with the given key or null if it's not found.
	 */
	public PModel property(String key)
	{
		if (key == null)
		{
			throw new IllegalArgumentException("Asking for a null property makes no sense.");
		}
		
		PModel p = properties.get(key);
		if (p == null)
		{
			// String msg = String.format("Unable to locate property definition for key [%s].", key);
			return null;
		}
		
		return p;
	}

	@Override
	public PModel identityProperty()
	{
		return id_property;
	}
}
