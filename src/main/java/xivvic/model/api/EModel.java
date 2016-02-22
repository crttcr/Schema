package xivvic.model.api;

/**
 * This interface defines the Entities in the model.
 * 
 * represents an type of entity. The Entity represents the entity so this
 * EModel is analogous to a class definition for the entity.
 * 
 * The schema consists of a ModelElementType and a Set of properties
 * 
 * @author Reid
 */
public interface EModel
	extends ModelElement, PContainer
{

	public EType modelElementType();


}
