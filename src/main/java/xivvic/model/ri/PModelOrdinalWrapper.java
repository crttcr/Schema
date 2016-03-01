package xivvic.model.ri;

import java.util.Objects;
import java.util.function.Function;

import xivvic.model.api.PModel;
import xivvic.model.api.PType;

/**
 * Simple wrapper class that forces a PModel to have a non-null ordinal value.
 * 
 * If the wrapped PModel already has an ordinal value, it is ignored in favor
 * of the one provided to the constructor. It is assumed that if the delegate's
 * ordinal value is important, the caller can simple construct the wrapper like
 * this:
 * 
 * PModel pm = new PModelOrdinalWrapper(delegate, delegate.ordinal());
 * 
 * so no checking is done and no cascading from delegate's value provided during
 * construction.
 * 
 * @author reid.dev
 */
public class PModelOrdinalWrapper
	implements PModel
{
	private final PModel delegate;
	private final    int  ordinal;
	
	public PModelOrdinalWrapper(PModel delegate, int ordinal)
	{
		Objects.requireNonNull(delegate, "Delegate required");
		
		this.delegate = delegate;
		this.ordinal  = ordinal;
	}

	@Override
	public PModel identityProperty() { return delegate.identityProperty(); }

	@Override
	public String name()
	{
		return delegate.name();
	}

	@Override
	public String key()
	{
		return delegate.key();
	}

	@Override
	public PType modelElementType()
	{
		return delegate.modelElementType();
	}

	@Override
	public boolean unique()
	{
		return delegate.unique();
	}

	@Override
	public boolean required()
	{
		return delegate.required();
	}

	@Override
	public Integer ordinal()
	{
		return ordinal;
	}

	@Override
	public Function<Object, String> object2String()
	{
		return delegate.object2String();
	}

	@Override
	public Function<String, Object> string2Object()
	{
		return delegate.string2Object();
	}
}
