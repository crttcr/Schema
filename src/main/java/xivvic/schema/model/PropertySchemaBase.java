package xivvic.schema.model;


import java.util.function.Function;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class PropertySchemaBase
	implements PropertySchema
{
	public abstract String name();
	public abstract String key();
	public abstract Class<?> type();
	public abstract boolean unique();
	public abstract boolean required();

	public abstract Function<Object, String> object2String();

	public abstract Function<String, Object> string2Object();

	
	public static Builder builder()
	{
		return new AutoValue_PropertySchemaBase.Builder();
	}
	
	@AutoValue.Builder
	public abstract static class Builder
	{
		public abstract PropertySchemaBase build();
		public abstract Builder key(String key);
		public abstract Builder name(String name);
		public abstract Builder type(Class<?> cls);
		public abstract Builder unique(boolean unique);
		public abstract Builder required(boolean required);
		public abstract Builder string2Object(Function<String, Object> converter);
		public abstract Builder object2String(Function<Object, String> converter);
	}
}
