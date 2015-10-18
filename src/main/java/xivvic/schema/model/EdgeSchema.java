package xivvic.schema.model;


import java.util.List;

/**
 * This is used to define an characteristics of an edge connecting two vertices.
 * 
 * @author Reid
 *
 */
public interface EdgeSchema
{
	EdgeType type();

	List<String> propertyNames();
	List<String> propertyKeys();
	List<PropertySchema> properties(PropertySchemaPredicate test);
	PropertySchema property(String key);

}
