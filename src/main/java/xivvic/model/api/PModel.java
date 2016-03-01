package xivvic.model.api;

import java.util.Comparator;
import java.util.function.Function;

/*
 * This class represents details about a single property, such as it's name,
 * and a unique key for the property. It also defines the presence or absence
 * of constraints such as whether or not the values of this property are unique
 * (within some undefined aggregate) or are required to be populated. This 
 * class does not contain the value of its property, only property metadata.
 * 
 */

public interface PModel
	extends ModelElement
{
	Comparator<? super PModel> COMPARE_BY_KEY     = (p1, p2) -> p1. key().compareTo(p2. key());
	Comparator<? super PModel> COMPARE_BY_NAME    = (p1, p2) -> p1.name().compareTo(p2.name());
	Comparator<? super PModel> COMPARE_BY_ORDINAL = (p1, p2) -> Integer.compare(p1.ordinal().intValue(), p2.ordinal().intValue());
	
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
