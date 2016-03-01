package xivvic.model.ri;


import java.util.Objects;
import java.util.function.Function;

import xivvic.model.api.PModel;
import xivvic.model.api.PType;

/**
 * Immutable class providing the model of a property. Normally I would use AutoValue
 * for a class such as this, however, AutoValue throws an exception when I try to use
 * it. Presumably it's because PModel specializes the return type in ModelBase.getElementType()
 * and that isn't covered by AutoValue.
 * 
 * @author reid.dev
 */
public class PModelStatic
	extends ModelBase
	implements PModel
{
	private String name;
	private String key;
	private boolean unique;
	private boolean required;
	private Integer ordinal;
	
	private Function<Object, String> o2s;
	private Function<String, Object> s2o;
	
	public PModelStatic(PType type)
	{
		super(type);
	}

	public String        name() { return name; }
	public String         key() { return key.toUpperCase(); }  // Safe from NPE b/c of builder's logic
	public boolean     unique() { return unique; }
	public boolean   required() { return required; }
	public Integer    ordinal() { return ordinal; }

	public Function<Object, String> object2String() { return o2s; }
	public Function<String, Object> string2Object() { return s2o; }

	public PType modelElementType() { return (PType) super.modelElementType(); }
	
	public static Builder builder(PType type)
	{
		PModelStatic.Builder builder = new PModelStatic.Builder(type);
		
		// Set reasonable, default values
		//
		builder.required(false);
		builder.unique(false);
		
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
			
			// Ensure that there is always a key value. Not providing a name will blow up the attempt to
			// build, but not providing a key causes the name to be used as the key. It is assumed that this
			// will be unique, but there is no mechanism to check across different properties.
			//
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

	@Override
	public String toString()
	{
		StringBuilder  sb = new StringBuilder();

		sb.append("name:").append(name);
		sb.append(" key:").append(key);
		sb.append(" unique:").append( unique);
		sb.append(" required:").append( required);
		sb.append(" ordinal:");
		if (ordinal == null)
			sb.append(" N/A");
		else
			sb.append(ordinal);
		
		if (o2s != null)
			sb.append("+o2s");
		if (s2o != null)
			sb.append("+s2o");
		
		return sb.toString();
	}

}
