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
	private Map<String, EModel>       entities = new HashMap<>();
	private Map<String, DModel>      documents = new HashMap<>();
	private Map<String, RModel>  relationships = new HashMap<>();
	
	public ModelManagerBasic()
	{
	}
	

	public void registerModel(ModelElement me)
	{
		Objects.requireNonNull(me, "Registering a null value as a model element is not permitted; it makes no sense.");
		
		ModelElementType type = me.modelElementType();
		
		if (type instanceof EType)
		{
			EModel cast_model = (EModel) me;

			entities.put(type.name(), cast_model);
		}
		else if (type instanceof DType)
		{
			DModel cast_model = (DModel) me;

			documents.put(type.name(), cast_model);
		}
		else if (type instanceof RType)
		{
			RModel cast_model = (RModel) me;

			relationships.put(type.name(), cast_model);
		}
		else
		{
			String msg = String.format("Unable to handle element of type: [%s]", me);
			throw new IllegalArgumentException(msg);
		}
	}
		
		
	@Override
	public DModel getDModel(String type)
	{
		Objects.requireNonNull(type, "Cannot retrieve model for null type");
		
		return documents.get(type);
	}

	@Override
	public EModel getEModel(String type)
	{
		Objects.requireNonNull(type, "Cannot retrieve model for null type");
		
		return entities.get(type);
	}

	@Override
	public RModel getRModel(String type)
	{
		Objects.requireNonNull(type, "Cannot retrieve model for null type");
		
		return relationships.get(type);
	}


	@Override
	public List<DModel> documentModels()
	{
		return new ArrayList<DModel>(documents.values());
	}


	@Override
	public List<EModel> entityModels()
	{
		return new ArrayList<EModel>(entities.values());
	}


	@Override
	public List<RModel> relationshipModels()
	{
		return new ArrayList<RModel>(relationships.values());
	}
	
}
