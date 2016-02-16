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
	
}
