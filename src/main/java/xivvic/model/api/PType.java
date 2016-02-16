package xivvic.model.api;
/**
 * This interface represents the different types of Properties that
 * are in the domain model. It is a marker interface that is designed
 * to be used with an enumerated type.
 * 
 * PTypes represent different classes of data types that are supported
 * by properties, e.g.
 * 
 * public enum PTypeStandard
 *   implements PType
 * {
 *    STRING,
 *    INTEGER,
 *    FLOAT,
 *    CHARACTER,
 *    BOOLEAN,
 *    DATE,
 *    TIME,
 *    DATETIME,
 *    BINARY,
 * }
 * 
 * @author reid.dev
 *
 */
public interface PType
	extends ModelElementType
{
}
