package xivvic.model.ri;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import xivvic.model.api.DModel;
import xivvic.model.api.DType;
import xivvic.model.api.EModel;
import xivvic.model.api.EType;
import xivvic.model.api.ModelElement;
import xivvic.model.api.ModelElementType;
import xivvic.model.api.ModelManager;
import xivvic.model.api.RModel;
import xivvic.model.api.RType;

public class ModelManagerBasic
	implements ModelManager
{
	private Map<EType, EModel>       entities = new HashMap<>();
	private Map<DType, DModel>      documents = new HashMap<>();
	private Map<RType, RModel>  relationships = new HashMap<>();
	
	public ModelManagerBasic()
	{
	}
	

	public void registerModel(ModelElement me)
	{
		Objects.requireNonNull(me, "Registering a null value as a model element is not permitted; it makes no sense.");
		
		ModelElementType type = me.modelElementType();
		
		if (type instanceof EType)
		{
			EType   cast_type = (EType) type;
			EModel cast_model = (EModel) me;

			entities.put(cast_type, cast_model);
		}
		else if (type instanceof DType)
		{
			DType   cast_type = (DType) type;
			DModel cast_model = (DModel) me;

			documents.put(cast_type, cast_model);
		}
		else if (type instanceof RType)
		{
			RType   cast_type = (RType) type;
			RModel cast_model = (RModel) me;

			relationships.put(cast_type, cast_model);
		}
		else
		{
			String msg = String.format("Unable to handle element of type: [%s]", me);
			throw new IllegalArgumentException(msg);
		}
	}
		
		
	@Override
	public List<EModel> entityModels()
	{
		return new ArrayList<EModel>(entities.values());
	}

	@Override
	public DModel getDModel(DType type)
	{
		if (type == null)
			return null;
		
		return documents.get(type);
	}

	@Override
	public EModel getEModel(EType type)
	{
		if (type == null)
			return null;
		
		return entities.get(type);
	}

	@Override
	public RModel getRModel(RType type)
	{
		if (type == null)
			return null;
		
		return relationships.get(type);
	}
}
