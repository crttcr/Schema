package xivvic.model.ri;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xivvic.model.api.PModel;
import xivvic.model.api.PType;

public class PropertyUniqueTest
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
		PModel model = TestFixtureUtil.buildIdProperty();
		
		// Act
		//
		boolean is_unique = predicate.test(model);
		
		// Assert
		//
		assertTrue(model.unique());  // Test is based on "id" property from Util being unique. Confirm.
		assertTrue(is_unique);
	}
	
	@Test
	public void testWhenFalse()
	{
		// Arrange
		//
		PType                   type = TestFixtureUtil.PropertyType.DATE;
		PModelStatic.Builder builder = PModelStatic.builder(type);
		builder.name("optional_prop");
		builder.unique(false);
		
		PModel model = builder.build();
		
		// Act
		//
		boolean is_unique = predicate.test(model);
		
		// Assert
		//
		assertFalse(is_unique);
	}

	@Test
	public void testWhenNull()
	{
		// Arrange
		//
		
		// Act
		//
		boolean is_unique = predicate.test(null);
		
		// Assert
		//
		assertFalse(is_unique);
	}
	

}
