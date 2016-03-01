package xivvic.model.api;

import java.util.Comparator;
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
	Comparator<? super PModel> COMPARE_BY_ORDINAL = (pm1, pm2) -> Integer.compare(pm1.ordinal().intValue(), pm2.ordinal().intValue());
	
	public String name();
	public String key();
	public PType modelElementType();
	public boolean unique();
	public boolean required();
	
	/**
	 * Optional integer used to define this property's ordinal value relative to other
	 * properties that are considered as a group. 
	 * 
	 * May return null if ordinal value is unimportant or if a default (e.g. arrival order)
	 * approach is being used.
	 * 
	 * @return an integer representing where in an ordering this property belongs, or null if unspecified
	 */
	public Integer ordinal();
	
	public Function<Object, String> object2String();
	public Function<String, Object> string2Object();
}
