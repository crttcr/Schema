package xivvic.model.ri;

import xivvic.model.api.EModel;
import xivvic.model.api.EType;
import xivvic.model.api.PContainer;

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
   public EModelStatic(EType type, PContainer pc)
   {
   	super(type, pc);
   }

	public EType modelElementType()
	{
		return (EType) super.modelElementType();
	};
	
}
