package xivvic.model.ri;


import java.util.Objects;

import xivvic.model.api.ModelElement;
import xivvic.model.api.ModelElementType;
import xivvic.model.api.PModel;

public abstract class ModelBase
	implements ModelElement
{

	private final ModelElementType type;
	
   public ModelBase(ModelElementType type)
   {
   	Objects.requireNonNull(type, "Non-null modelElementType required for model instances.");
   	
   	this.type   = type;
   }

	@Override
	public ModelElementType modelElementType()
	{
		return type;
	}
	
	public abstract PModel identityProperty();
	
}
