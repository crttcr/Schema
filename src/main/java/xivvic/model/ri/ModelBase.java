package xivvic.model.ri;


import java.util.Objects;

import xivvic.model.api.ModelElement;
import xivvic.model.api.ModelElementType;

public abstract class ModelBase
	implements ModelElement
{

	private final ModelElementType type;
	
   public ModelBase(ModelElementType type)
   {
   	Objects.requireNonNull(type, "Non-null modelElementType required for schema");
   	
   	this.type   = type;
   }

	@Override
	public ModelElementType modelElementType()
	{
		return type;
	}
	
}
