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


	/**
	 * If this Entity has a property which represents its identity,
	 * then this method will return PModel information about that property.
	 * If there is no identity for this entity, this method returns null.
	 * 
	 * [Open question, what if identity is represented by a composite property?]
	 * 
	 * @return null if there is not identity concept or the PModel representing the entity's identity
	 */
	public PModel identityProperty();

}
