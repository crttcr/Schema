package xivvic.schema.model;

import java.util.List;
/**
 * This class is used to define a the type of a Vertex which 
 * represents an entity. The Vertex represents the entity so this
 * VertexSchema is analogous to a class definition for the entity.
 * 
 * The schema consists of a Type and a Set of properties
 * 
 * @author Reid
 *
 */
public interface VertexSchema
{
	public VertexType type();
	public List<String> propertyNames();
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
	public PropertySchema property(String key);
	
	/**
	 * Returns the property definitions for which the given predicate evaluates to true.
	 * 
	 * Returns all properties if the predicate is null.
	 * 
	 * @param predicate the test to apply which will serve to filter the returned results
	 * 
	 * @return all the predicates that evaluate to true, or all properties if predicate is null
	 */
	public List<PropertySchema> properties(PropertySchemaPredicate predicate);

}
