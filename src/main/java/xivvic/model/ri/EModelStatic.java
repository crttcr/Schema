package xivvic.model.ri;

import xivvic.model.api.EModel;
import xivvic.model.api.EType;
import xivvic.model.api.PContainer;
import xivvic.model.api.PModel;

/**
 * Immutable implementation of the EModel interface
 * 
 * 
 * @author Reid
 *
 */
public class EModelStatic
	extends ModelWithProperties
	implements EModel
{
	private final PModel identityProperty;
	
   public EModelStatic(EType type, PContainer pc, PModel identity)
   {
   	super(type, pc);

   	if (identity != null)
   	{
   		if (identity.unique() == false)
   			throw new IllegalArgumentException("Identity property must be unique");
   	}

   	identityProperty = identity;
   }

	public EType modelElementType()
	{
		return (EType) super.modelElementType();
	};
	
	@Override
	public PModel identityProperty()
	{
		return identityProperty;
	}

}
