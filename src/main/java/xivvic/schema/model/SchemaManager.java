package xivvic.schema.model;


import java.util.List;

/**
 * This interface represents any implementation that provides 
 * schema information for an application domain.
 * 
 * @author Reid
 *
 */
public interface SchemaManager
{
	public List<VertexType> entityTypes();
	public List<VertexSchema> entityDescriptors();
	public VertexSchema getEntitySchema(VertexType type);
	public EdgeSchema getEdgeSchema(EdgeType type);

}
