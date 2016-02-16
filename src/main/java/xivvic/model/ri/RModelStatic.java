package xivvic.model.ri;


import xivvic.model.api.ModelElement;
import xivvic.model.api.PContainer;
import xivvic.model.api.RModel;
import xivvic.model.api.RType;

/** 
 * Immutable class modeling Relationships.
 * 
 * Uses the builder pattern, with a couple of changes to deal with forced parameters in the
 * constructor call of the parent and creating an instance of the surrounding class to 
 * manipulate to eliminate having to redefine the fields in the builder.
 * 
 * Note: Not much liking this class, but AutoValue threw an exception, related to implementing
 * RModel. I would guess it's because the sub-interface specializes the return type of its parent
 * interface.
 * 
 * @author reid.dev
 *
 */
public class RModelStatic
	extends ModelWithProperties
	implements RModel
{
	private ModelElement from;
	private ModelElement   to;
	private boolean required;
	private boolean multipleOutbound;
	private boolean multipleInbound;
	private boolean singleInstanceBetweenEndpoints;

   public RModelStatic(RType type, PContainer pc)
   {
   	super(type, pc);
   }

	public ModelElement from() { return from; };
	public ModelElement   to() { return   to; };
	public boolean  required() { return required; }

	public boolean               multipleOutbound() { return multipleOutbound; }
	public boolean               multipleInbound() { return multipleInbound; }
	public boolean singleInstanceBetweenEndpoints() { return singleInstanceBetweenEndpoints; }
	
	public RType modelElementType() { return (RType) super.modelElementType(); }
	
	public static class Builder
	{
		private final RModelStatic instance;

		public Builder(RType type, PContainer pc)
		{
			instance = new RModelStatic(type, pc);
		}
		
      public Builder     from(ModelElement e)
      {
      	this.instance.from = e;
      	return this;
      }

      public Builder     to(ModelElement e)
      {
      	this.instance.to = e;
      	return this;
      }

      public Builder                       required(boolean b)
      {
      	this.instance.required = b;
      	return this;
      }

      public Builder               multipleOutbound(boolean b)
      {
      	this.instance.multipleOutbound = b;
      	return this;
      }

      public Builder                multipleInbound(boolean b)
      {
      	this.instance.multipleInbound = b;
      	return this;
      }

      public Builder singleInstanceBetweenEndpoints(boolean b)
      {
      	this.instance.singleInstanceBetweenEndpoints = b;
      	return this;
      }
		
		public RModelStatic build()
		{
			return instance;
		}
	}
	
	public static Builder builder(RType type, PContainer pc)
	{
		Builder builder = new RModelStatic.Builder(type, pc);

		// Set reasonable default values
		//
		builder.multipleOutbound(true);
		builder.multipleInbound(true);
		builder.required(false);
		builder.singleInstanceBetweenEndpoints(true);

		return builder;
	}
	
}
