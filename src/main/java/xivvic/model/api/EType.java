package xivvic.model.api;
/**
 * This interface represents the different types of Entities that
 * are in the domain model. 
 * 
 * This is a marker interface, intended to be implemented via an enumeration within a
 * specific example of the model. For example, if one wanted to model different types
 * of entities in a domain:
 * 
 * public enum ApplicationEntityType
 *   implements EType
 * {
 *    USER,
 *    GROUP,
 *    RESOURCE,
 * }
 * 
 * @author reid.dev
 *
 */
public interface EType
	extends ModelElementType
{
}
