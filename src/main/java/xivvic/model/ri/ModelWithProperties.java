package xivvic.model.ri;


import java.util.List;
import java.util.Objects;
import java.util.Set;

import xivvic.model.api.ModelElementType;
import xivvic.model.api.PContainer;
import xivvic.model.api.PModel;
import xivvic.model.api.PModelPredicate;

public abstract class ModelWithProperties
	extends ModelBase 
	implements PContainer
{

	private final ModelElementType type;
	private final PContainer pc;
	
   public ModelWithProperties(ModelElementType type, PContainer pc)
   {
   	super(type);
   	
   	Objects.requireNonNull(type, "Non-null modelElementType required for schema");
   	Objects.requireNonNull(pc, "Property Container required in constructor");
   	
   	this.type   = type;
   	this.pc    = pc;
   }

	@Override
	public ModelElementType modelElementType()
	{
		return type;
	}

	
	public List<String> propertyNames()
	{
		return pc.propertyNames();
	}

	public List<String> propertyKeys()
	{
		return pc.propertyKeys();
	}

	public List<PModel> properties(PModelPredicate test)
	{
		return pc.properties(test);
	}

	public Set<String> requiredPropertyKeys()
	{
		return pc.requiredPropertyKeys();
	}

	@Override
	public PModel property(String key)
	{
		return pc.property(key);
	}
}
