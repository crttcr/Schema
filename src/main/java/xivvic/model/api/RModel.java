package xivvic.model.api;

/**
 * This is used to define an characteristics of a relationship between two
 * model elements.
 * 
 * @author Reid
 *
 */
public interface RModel
	extends ModelElement, PContainer
{
	RType modelElementType();

	ModelElement from();
	ModelElement to();
	
	boolean required();
	boolean multipleOutbound();
	boolean multipleInbound();
	boolean singleInstanceBetweenEndpoints();
}
