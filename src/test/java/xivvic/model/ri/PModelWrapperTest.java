package xivvic.model.ri;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import xivvic.model.api.PModel;
import xivvic.model.api.PType;
import xivvic.model.ri.TestFixtureUtil.PropertyType;

public class PModelWrapperTest
{
	PModel               delegate;
	PModelOrdinalWrapper  subject;

	@Before
	public void setUp() throws Exception
	{
		
		delegate = TestFixtureUtil.buildNameProperty();
		subject     = new PModelOrdinalWrapper(delegate, 1);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Cannot wrap a null PModel
	 * 
	 * Test that an attempt to construct one with a null delegate fails.
	 */
	@Test(expected=NullPointerException.class)
	public void testNullDelegateCausesFailure()
	{
		// Arrange
		//
		
		// Act
		//
		@SuppressWarnings("unused")
		PModel pm = new PModelOrdinalWrapper(null, 4);
		
		// Assert
		//
		fail("Should have thrown exception. Instead execution continued to this point");
	}


	@Test
	public void testOrdinalWorks()
	{
		// Arrange
		//
		PModel    wrapped = PModelStatic.builder(TestFixtureUtil.PropertyType.INTEGER).name("Sam Snead").build();
		PModel    wrapper = new PModelOrdinalWrapper(wrapped,  -32);
		
		// Act
		//
		int result = wrapper.ordinal();
		
		
		// Assert
		//
		assertEquals(-32, result);
	}

	
	@Test
	public void testUniqueWorks()
	{
		// Arrange
		//
		PModel    is_true = PModelStatic.builder(TestFixtureUtil.PropertyType.DATE).name("true") .unique(true) .build();
		PModel   is_false = PModelStatic.builder(TestFixtureUtil.PropertyType.DATE).name("false").unique(false).build();
		PModel  wrap_true = new PModelOrdinalWrapper(is_true,  -4);
		PModel wrap_false = new PModelOrdinalWrapper(is_false,  4);
		
		// Act
		//
		boolean  b_true = wrap_true .unique();
		boolean b_false = wrap_false.unique();
		
		// Assert
		//
		assertTrue ( b_true);
		assertFalse(b_false);
	}

	@Test
	public void testRequiredWorks()
	{
		// Arrange
		//
		PModel    is_true = PModelStatic.builder(TestFixtureUtil.PropertyType.STRING).name("true") .required(true) .build();
		PModel   is_false = PModelStatic.builder(TestFixtureUtil.PropertyType.STRING).name("false").required(false).build();
		PModel  wrap_true = new PModelOrdinalWrapper(is_true,  -4);
		PModel wrap_false = new PModelOrdinalWrapper(is_false,  4);
		
		// Act
		//
		boolean  b_true = wrap_true .required();
		boolean b_false = wrap_false.required();
		
		// Assert
		//
		assertTrue ( b_true);
		assertFalse(b_false);
	}

	@Test
	public void testTypeWorks()
	{
		// Arrange
		//
		
		// Act
		//
		PType type = subject.modelElementType();
		
		
		// Assert
		//
		assertEquals(delegate.modelElementType(), type);
	}

	@Test
	public void testNameWorks()
	{
		// Arrange
		//
		
		// Act
		//
		String name = subject.name();
		
		
		// Assert
		//
		assertEquals(delegate.name(), name);
	}

	@Test
	public void testKeyWorks()
	{
		// Arrange
		//
		
		// Act
		//
		String key = subject.key();
		
		
		// Assert
		//
		assertEquals(delegate.key(), key);
	}



	@Test
	public void testObject2StringNullWorks()
	{
		// Arrange
		//
		
		// Act
		//
		Function<Object, String> f = subject.object2String();
		
		
		// Assert
		//
		assertNull(f);
	}

	@Test
	public void testObject2StringNotNullWorks()
	{
		// Arrange
		//
		Function<Object, String> f = (o) -> { return "Hi"; };

		PModel    wrapped = PModelStatic.builder(TestFixtureUtil.PropertyType.DATE).name("true") .unique(true) .object2String(f).build();
		PModel    wrapper = new PModelOrdinalWrapper(wrapped,  -4);
		
		// Act
		//
		Function<Object, String> result = wrapper.object2String();
		
		
		// Assert
		//
		assertEquals("Hi", result.apply(null));
	}

	@Test
	public void testString2ObjectNullWorks()
	{
		// Arrange
		//
		
		// Act
		//
		Function<String, Object> f = subject.string2Object();
		
		
		// Assert
		//
		assertNull(f);
	}

	@Test
	public void testString2ObjectNotNullWorks()
	{
		// Arrange
		//
		Function<String, Object> f = (s) -> { return s.length(); };

		PModel    wrapped = PModelStatic.builder(TestFixtureUtil.PropertyType.DATE).name("true") .unique(true).string2Object(f).build();
		PModel    wrapper = new PModelOrdinalWrapper(wrapped,  93);
		String          s = "This is some randomly long string.";
		
		// Act
		//
		Function<String, Object> result = wrapper.string2Object();

		
		
		// Assert
		//
		assertEquals(s.length(), result.apply(s));
	}

}
