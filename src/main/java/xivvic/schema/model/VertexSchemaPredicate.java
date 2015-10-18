package xivvic.schema.model;


import java.util.function.Predicate;
/**
 * This interface represents predicates that can be applied to a VertexSchema.
 * 
 * @author reid.dev
 *
 */
public interface VertexSchemaPredicate
	extends Predicate<VertexSchema>
{
//	static VertexSchemaPredicate predicateUnique()
//	{
//		VertexSchemaPredicate p = new VertexSchemaPredicate()
//		{
//			@Override
//			public boolean test(VertexSchema t)
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
