package xivvic.model.api;

/**
 * This is used to define an characteristics of an edge connecting two vertices.
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
