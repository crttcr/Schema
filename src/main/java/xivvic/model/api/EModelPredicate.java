package xivvic.model.api;


import java.util.function.Predicate;
/**
 * This interface represents predicates that can be applied to a EModel.
 * 
 * @author reid.dev
 *
 */
public interface EModelPredicate
	extends Predicate<EModel>
{
//	static EModelPredicate predicateUnique()
//	{
//		EModelPredicate p = new EModelPredicate()
//		{
//			@Override
//			public boolean test(EModel t)
//			{
//				if (t == null)
//					return false;
//				
//				return t.unique();
//			}
//			
//		};
//		
//		return p;
//		
//	}

}
