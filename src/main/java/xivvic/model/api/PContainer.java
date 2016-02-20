package xivvic.model.api;

import java.util.List;
import java.util.Set;

/**
 * This interface represents model elements that act as a property container.
 * A property container is a model element that can be associated with properties.
 * 
 * @author reid.dev
 */

public interface PContainer
{
	/**
	 * Lists all the names of all the properties for a model element.
	 * 
	 * @return a list containing the names of all the properties for this element.
	 */
	public List<String> propertyNames();
	
	/**
	 * Lists all the names of all the keys for a model element.
	 * 
	 * @return a list containing the names of all the properties for this element.
	 */
	public List<String> propertyKeys();
	
	/**
	 * Returns the property definition associated with the provided key.
	 * 
	 * Throws IllegalArgumentExceptin if the key is null or if there is 
	 * no property associated with the given key.
	 * 
	 * @param key
	 * @return the definition for the property.
	 */
	public PModel property(String key);
	
	/**
	 * Returns the property definitions for which the given predicate evaluates to true.
	 * 
	 * Returns all properties if the predicate is null.
	 * 
	 * @param predicate the test to apply which will serve to filter the returned results
	 * 
	 * @return all the predicates that evaluate to true, or all properties if predicate is null
	 */
	public List<PModel> properties(PModelPredicate predicate);
	
	/**
	 * Returns the property definitions for all required properties. This is a convenience
	 * method that calls properties(test) with an IsRequired predicate.
	 * 
	 * @return all required property models or an empty set if there are none.
	 */
	public Set<PModel> requiredProperties();
}
