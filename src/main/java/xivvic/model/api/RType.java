package xivvic.model.api;

/**
 * This interface represents all the model objects that are Relationships and described
 * by a RModel.
 * 
 * This is a marker interface, intended to be implemented via an enumeration within a
 * specific example of the model. For example, if one wanted to model different types
 * of entities in a domain:
 * 
 * public enum ApplicationRelationshipType
 *   implements RType
 * {
 *    USER_2_GROUP,
 *    GROUP_2_ORGANIZATION,
 *    EVENT_2_RESOURCE,
 * }
 */

public interface RType
	extends ModelElementType
{
}
