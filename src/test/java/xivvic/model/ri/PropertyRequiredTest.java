package xivvic.model.ri;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xivvic.model.api.PModel;
import xivvic.model.api.PType;

public class PropertyRequiredTest
{
	Predicate<PModel> predicate;

	@Before
	public void setUp() throws Exception
	{
		predicate = new PropertyRequired();
	}

	@After
	public void tearDown() throws Exception
	{
		predicate = null;
	}

	@Test
	public void testWhenTrue()
	{
		// Arrange
		//
		PModel model = TestFixtureUtil.buildNameProperty();
		
		// Act
		//
		boolean is_required = predicate.test(model);
		
		// Assert
		//
		assertTrue(model.required());  // Test is based on "name" property from Util being true. Confirm.
		assertTrue(is_required);
	}
	
	@Test
	public void testWhenFalse()
	{
		// Arrange
		//
		PType                   type = TestFixtureUtil.PropertyType.DATE;
		PModelStatic.Builder builder = PModelStatic.builder(type);
		builder.name("optional_prop");
		builder.required(false);
		
		PModel model = builder.build();
		
		// Act
		//
		boolean is_required = predicate.test(model);
		
		// Assert
		//
		assertFalse(is_required);
	}

	@Test
	public void testWhenNull()
	{
		// Arrange
		//
		
		// Act
		//
		boolean is_required = predicate.test(null);
		
		// Assert
		//
		assertFalse(is_required);
	}
	

}
