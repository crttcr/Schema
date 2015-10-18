package xivvic.schema.model;


import java.util.function.Predicate;

public interface PropertySchemaPredicate
	extends Predicate<PropertySchema>
{
	/**
	 * Returns a function that will produce a predicate that returns true when a property is required.
	 * 
	 * @return a function that tests a property definition to determine if it is required.
	 */
	static PropertySchemaPredicate predicateRequired()
	{
		PropertySchemaPredicate p = new PropertySchemaPredicate()
		{
			@Override
			public boolean test(PropertySchema t)
			{
				if (t == null)
					return false;
				
				return t.required();
			}
			
		};
		
		return p;
		
	}

	/**
	 * Returns a function that will produce a predicate that returns true when a property must 
	 * have a unique value.
	 * 
	 * @return a function that tests a property definition to determine if each value must be unique.
	 */
	static PropertySchemaPredicate predicateUnique()
	{
		PropertySchemaPredicate p = new PropertySchemaPredicate()
		{
			@Override
			public boolean test(PropertySchema t)
			{
				if (t == null)
					return false;
				
				return t.unique();
			}
			
		};
		
		return p;
		
	}

}
