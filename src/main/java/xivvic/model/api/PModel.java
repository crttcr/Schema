package xivvic.model.api;

import java.util.function.Function;

/*
 * This class represents metadata about a single property.
 * 
 * It does not extend the ModelElement interface because other model elements
 * are 
 * 
 */

public interface PModel
	extends ModelElement
{
	public String name();
	public String key();
	public PType modelElementType();
	public boolean unique();
	public boolean required();
	
	public Function<Object, String> object2String();
	public Function<String, Object> string2Object();
}
