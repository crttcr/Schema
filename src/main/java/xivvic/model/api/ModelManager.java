package xivvic.model.api;


import java.util.List;

/**
 * This interface represents the functions performed by a
 * managing object which maintains the state of the model.
 * 
 * This manager would be the access mechanism for the code to acquire
 * information about the domain model.
 * 
 * @author Reid
 *
 */
public interface ModelManager
{
// Shouldn't this be available from the enum implementing EType?
//
// public List<EType> eTypes();

	public List<EModel> entityModels();
	public DModel getDModel(DType type);
	public EModel getEModel(EType type);
	public RModel getRModel(RType type);
	
	// This shouln't be part of the interface.
	//
	//public boolean register(ModelElement modelElement);
}
