package xivvic.model.api;


import java.util.function.Predicate;

public interface PModelPredicate
	extends Predicate<PModel>
{
	// Predicate which always returns true
	//
	PModelPredicate TRUE = new PModelPredicate()
	{
		@Override
		public boolean test(PModel t)
		{
			return true;
		}
	};

	/**
	 * Returns a function that will produce a predicate that returns true when a property is required.
	 * 
	 * @return a function that tests a property definition to determine if it is required.
	 */
	static PModelPredicate predicateRequired()
	{
		PModelPredicate p = new PModelPredicate()
		{
			@Override
			public boolean test(PModel t)
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
	static PModelPredicate predicateUnique()
	{
		PModelPredicate p = new PModelPredicate()
		{
			@Override
			public boolean test(PModel t)
			{
				if (t == null)
					return false;
				
				return t.unique();
			}
			
		};
		
		return p;
		
	}

}
