package xivvic.model.ri;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xivvic.model.api.PModel;
import xivvic.model.api.PType;
import xivvic.model.ri.TestFixtureUtil.PropertyType;

public class PModelStaticTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * All Properties must have a name
	 * 
	 * Test that an attempt to construct one with a null name fails.
	 */
	@Test(expected=NullPointerException.class)
	public void testNullTypeCausesFailure()
	{
		PType   type = null;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type);
		builder.name(null);
		
		
		@SuppressWarnings("unused")
		PModel model = builder.build();
		fail("Should have thrown exception. Instead execution continued to this point");
	}

	/**
	 * All Properties must have a name
	 * 
	 * Test that an attempt to construct one with a null name fails.
	 */
//	@Test(expected=AssertionError.class)
	@Test(expected=NullPointerException.class)
	public void testNullNameIsVerbotten()
	{
		PType   type = PropertyType.STRING;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type);
		builder.name(null);
		
		@SuppressWarnings("unused")
		PModel model = builder.build();
		fail("Should have thrown exception. Instead execution continued to this point");
	}

	@Test(expected=NullPointerException.class)
	public void testOmittedNameCausesFailure()
	{
		PType   type = PropertyType.STRING;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type);
		
		@SuppressWarnings("unused")
		PModel model = builder.build();
		fail("Should have thrown exception. Instead execution continued to this point");
	}

	/**
	 * All Properties must have a name
	 * 
	 * Test that an attempt to construct one with a null name fails.
	 */
	@Test(expected=NullPointerException.class)
	public void testNullKeyIsVerbotten()
	{
		PType   type = PropertyType.STRING;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type).name("example_name");
		builder.key(null);
		
		@SuppressWarnings("unused")
		PModel model = builder.build();
		fail("Should have thrown exception. Instead execution continued to this point");
	}

	public void testOmittedKeyDefaultsToName()
	{
		PType   type = PropertyType.STRING;
		String  name = "example_name";
		
		PModelStatic.Builder builder =  PModelStatic.builder(type).name(name);
		
		PModel model = builder.build();
		
		String   key = model.key();
		
		assertTrue(name.equals(key));
	}

	@Test
	public void testUniqueIsFalseByDefault()
	{
		PType   type = PropertyType.INTEGER;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type).name("example_name");
		PModel                 model = builder.build();
		
		assertFalse(model.unique());
	}

	@Test
	public void testUniqueWorks()
	{
		PType   type = PropertyType.INTEGER;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type).name("example_name");
		builder.unique(true);
		
		PModel                 model = builder.build();
		
		assertTrue(model.unique());
	}

	@Test
	public void testRequiredIsFalseByDefault()
	{
		PType   type = PropertyType.INTEGER;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type).name("example_name");
		PModel                 model = builder.build();
		
		assertFalse(model.required());
	}

	@Test
	public void testRequiredWorks()
	{
		PType   type = PropertyType.INTEGER;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type).name("example_name");
		builder.required(true);
		
		PModel                 model = builder.build();
		
		assertTrue(model.required());
	}


	@Test
	public void testObject2String()
	{
		// Arrange
		//
		PType                 type = PropertyType.STRING;
		final String          name = "example_name";
		Function<Object, String> f = (o) -> { return name;  };
		
		// Act
		//
		PModelStatic.Builder builder =  PModelStatic.builder(type).name(name);
		
		builder.object2String(f);
		
		PModel               model = builder.build();
		Function<Object, String> g = model.object2String();
		String                   s = g.apply(null);
		
		// Assert
		//
		assertTrue(name.equals(s));
	}

	@Test
	public void testString2Object()
	{
		// Arrange
		//
		PType                 type = PropertyType.STRING;
		final String          name = "example_name";
		Function<String, Object> f = (o) -> { return name;  };
		
		// Act
		//
		PModelStatic.Builder builder =  PModelStatic.builder(type).name(name);
		
		builder.string2Object(f);
		
		PModel               model = builder.build();
		Function<String, Object> g = model.string2Object();
		Object                   o = g.apply("some string");
		
		// Assert
		//
		assertTrue(name.equals(o));
	}

	@Test
	public void testModelElementType()
	{
		PType   type = PropertyType.INTEGER;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type).name("example_name");
		
		PModel                 model = builder.build();
		
		assertTrue(PropertyType.INTEGER == model.modelElementType());
	}

}
