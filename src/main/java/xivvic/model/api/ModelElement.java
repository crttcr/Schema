package xivvic.model.api;

/**
 * ModelElement is the base interface for all of the different element types
 * within a model.
 * 
 * @author reid.dev
 */

public interface ModelElement
{
	/**
	 * Returns the element type for a specific element.
	 * 
	 * This should never return null.
	 * 
	 * @return a sub-interface of ModelElementType representing the actual type of the model element
	 */
	ModelElementType modelElementType();
	
	/**
	 * If this ModelElement has a property which represents its identity,
	 * then this method will return PModel describing the property.
	 * If there is no identity property, this method returns null.
	 * 
	 * [Open question, what if identity is represented by a composite property?]
	 * 
	 * @return null if there is not identity concept or the PModel representing the identity property
	 */
	public PModel identityProperty();
	
}
