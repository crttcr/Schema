package xivvic.model.api;

/**
 * This interface represents all the model objects that are Documents and described
 * by a DModel.
 * 
 * This is a marker interface, intended to be implemented via an enumeration within a
 * specific example of the model. For example, if one wanted to model different types
 * of documents related to format:
 * 
 * public enum ApplicationDocumentType
 *   implements DType
 * {
 *    PDF_DOCUMENT,
 *    PNG_DOCUMENT,
 *    WORD_DOCUMENT,
 * }
 * 
 */
public interface DType
	extends ModelElementType
{

}
