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
	 * Returns the property keys for all required properties. This is a convenience
	 * method that calls properties(test) with an IsRequired predicate and extracts keys.
	 * 
	 * @return all required property keys or an empty set if there are none.
	 */
	public Set<String> requiredPropertyKeys();
	
	/**
	 * If this PContainer has a property which provides the identity of each element in the
	 * model, this method will provide the model for that property. If not, this method should
	 * return null.
	 * 
	 * @return the PModel of the property providing identity or null if one does not exist.
	 */
	public PModel identityProperty();
}
