package xivvic.model.ri;


import java.util.Objects;
import java.util.function.Function;

import xivvic.model.api.PModel;
import xivvic.model.api.PType;

public class PModelStatic
	extends ModelBase
	implements PModel
{
	private String name;
	private String key;
	private boolean unique;
	private boolean required;
	private Function<Object, String> o2s;
	private Function<String, Object> s2o;
	
	public PModelStatic(PType type)
	{
		super(type);
		
	}

	public String name() { return name; }
	public String key() { return key; }
	public boolean unique() {return unique; }
	public boolean required() {return required; }

	public Function<Object, String> object2String() { return o2s; }

	public Function<String, Object> string2Object() { return s2o; }
	public PType modelElementType() { return (PType) super.modelElementType(); }

	
	public static Builder builder(PType type)
	{
		PModelStatic.Builder builder = new PModelStatic.Builder(type);
		
		// Set reasonable default values
		//
		// TODO:
		
		return builder;
	}
	
	public static class Builder
	{
		private final PModelStatic instance;
		
		private Builder(PType type)
		{
			instance = new PModelStatic(type);
		}

		public Builder unique(boolean unique)
		{
			this.instance.unique = unique;

			return this;
		}
		
		public Builder string2Object(Function<String, Object> converter)
		{
			this.instance.s2o = converter;
			return this;
		}
		
		public Builder required(boolean required)
		{
			this.instance.required = required;
			return this;
		}
		
		public Builder object2String(Function<Object, String> converter)
		{
			this.instance.o2s = converter;
			return this;
		}
		
		public Builder name(String name)
		{
			Objects.requireNonNull(name, "PModel.name cannot not be null");
			this.instance.name = name;
			return this;
		}
		
		public Builder key(String key)
		{
			Objects.requireNonNull(key, "PModel.key cannot not be null");
			this.instance.key = key;
			return this;
		}
		
		public PModelStatic build()
		{
			Objects.requireNonNull(instance.name, "PModel.name, a required field, was not provided during construction.");
			
			if (instance.key == null)
				instance.key = instance.name;
			
			return instance;
		}
	}

	/** 
	 * There is no identityProperty for a Property.
	 * This method always returns null.
	 */
	@Override
	public PModel identityProperty()
	{
		return null;
	}

}
