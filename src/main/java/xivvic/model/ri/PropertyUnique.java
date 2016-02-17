package xivvic.model.ri;

import xivvic.model.api.PModel;
import xivvic.model.api.PModelPredicate;

/**
 * Simple predicate to determine if a property is unique.
 * 
 * Note that if the property model being tested is null, then
 * this predicate will report that the property is not unique.
 * 
 * @author reid.dev
 */

public class PropertyUnique
	implements PModelPredicate
{
	@Override
	public boolean test(PModel pm)
	{
		if (pm == null)
			return false;
		
		return pm.unique();
	}
}
