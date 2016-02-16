package xivvic.model.api;

/**
 * A DModel represents a Document in the DModel.
 * 
 * Documents are opaque objects with an internal structure that is not
 * captured in the model. An example would be a PDF file, bitmap image,
 * or any other bespoke binary (i.e. non-text) data element.
 * 
 * 
 * @author reid.dev
 *
 */
public interface DModel
	extends ModelElement, PContainer
{
	public DType modelElementType();

}
