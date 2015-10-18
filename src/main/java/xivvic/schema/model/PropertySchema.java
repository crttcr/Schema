package xivvic.schema.model;

import java.util.function.Function;

/*
 * This class represents metadata about a single property.
 * 
 */

public interface PropertySchema
{
	public String name();
	public String key();
	public Class<?> type();
	public boolean unique();
	public boolean required();
	
	public Function<Object, String> object2String();
	public Function<String, Object> string2Object();
}
