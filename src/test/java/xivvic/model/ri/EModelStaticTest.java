package xivvic.model.ri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xivvic.model.api.EModel;
import xivvic.model.api.EType;
import xivvic.model.api.PContainer;
import xivvic.model.api.PModel;

public class EModelStaticTest
{
	private EModel entity_model;

	@Before
	public void setUp() throws Exception
	{
		List<PModel> list = new ArrayList<>();
		
		PModel id = TestFixtureUtil.buildIdProperty();
		list.add(id);
		list.add(TestFixtureUtil.buildNameProperty());
		list.add(TestFixtureUtil.buildColorProperty());
		
		PContainer container = new PContainerStatic(list);
		EType           type = TestFixtureUtil.EntityType.GROUP;
		entity_model         = new EModelStatic(type, container, id);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testModelElementType()
	{
		// Arrange
		//
		
		// Act
		//
		EType type = entity_model.modelElementType();
		
		// Assert
		//
		assertNotNull(type);
		assertEquals(TestFixtureUtil.EntityType.GROUP, type);
	}

	@Test
	public void testIdentityPropertyWhichExists()
	{
		// Arrange
		//
		
		// Act
		//
		PModel prop = entity_model.identityProperty();
		
		// Assert
		//
		assertNotNull(prop);
		assertEquals(TestFixtureUtil.PROP_ID_NAME, prop.name());
	}

	@Test
	public void testIdentityPropertyWhichDoesNotExist()
	{
		// Arrange
		//
		EModel model = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.USER);
		
		// Act
		//
		PModel prop = model.identityProperty();
		
		// Assert
		//
		assertNull(prop);
	}

	@Test
	public void testPropertyNames()
	{
		// Arrange
		//
		
		
		// Act
		//
		List<String> names = entity_model.propertyNames();
		
		// Assert
		//
		assertNotNull(names);
		assertEquals(3, names.size());
		assertTrue(names.contains(TestFixtureUtil.PROP_ID_NAME));
		assertTrue(names.contains(TestFixtureUtil.PROP_NAME_NAME));
		assertTrue(names.contains(TestFixtureUtil.PROP_COLOR_NAME));
		assertFalse(names.contains("This_key_should_not_exist"));
	}

	@Test
	public void testPropertyKeys()
	{
		// Arrange
		//
		
		
		// Act
		//
		List<String> keys = entity_model.propertyKeys();
		
		// Assert
		//
		assertNotNull(keys);
		assertEquals(3, keys.size());
		assertTrue(keys.contains(TestFixtureUtil.PROP_ID_NAME));
		assertTrue(keys.contains(TestFixtureUtil.PROP_NAME_NAME));
		assertTrue(keys.contains(TestFixtureUtil.PROP_COLOR_NAME));
		assertFalse(keys.contains("This_key_should_not_exist"));
	}

	@Test
	public void testPropertiesEmptyModelNullPredicate()
	{
		// Arrange
		//
		EModel model = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.USER);
		
		// Act
		//
		List<PModel> list = model.properties(null);
		
		// Assert
		//
		assertNotNull(list);
		assertTrue(list.isEmpty());
	}

	@Test
	public void testPropertiesNullPredicate()
	{
		// Arrange
		//
		
		// Act
		//
		List<PModel> list = entity_model.properties(null);
		
		// Assert
		//
		assertNotNull(list);
		assertEquals(3, list.size());
	}

	@Test
	public void testPropertyLookup()
	{
		// Arrange
		//
		
		// Act
		//
		PModel m1 = entity_model.property(TestFixtureUtil.PROP_ID_NAME);
		PModel m2 = entity_model.property(TestFixtureUtil.PROP_COLOR_NAME);
		PModel mx = entity_model.property("k.x");
		
		// Assert
		//
		assertNotNull(m1);
		assertNotNull(m2);
		assertNull(mx);

		assertTrue(m1.key().equals(TestFixtureUtil.PROP_ID_NAME));
		assertTrue(m2.key().equals(TestFixtureUtil.PROP_COLOR_NAME));
	}


}
