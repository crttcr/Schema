package xivvic.model.ri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xivvic.model.api.PModel;
import xivvic.model.api.PModelPredicate;
import xivvic.model.api.PType;

public class PContainerStaticTest
{
	PContainerStatic container;

	@Before
	public void setUp() throws Exception
	{
		List<PModel> list = new ArrayList<>();
		
		PModel p_id = TestFixtureUtil.buildIdProperty();
		
		list.add(p_id);
		list.add(TestFixtureUtil.buildNameProperty());
		list.add(TestFixtureUtil.buildColorProperty());
		
		container = new PContainerStatic(list, p_id);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testPropertyNames()
	{
		// Arrange
		//
		
		// Act
		//
		List<String> names = container.propertyNames();
		
		// Assert
		//
		// (Relies on ordering provided by PContainer
		//
		assertEquals(3, names.size());
		assertTrue(names.get(0).equals(TestFixtureUtil.PROP_ID_NAME));
		assertTrue(names.get(1).equals(TestFixtureUtil.PROP_NAME_NAME));
		assertTrue(names.get(2).equals(TestFixtureUtil.PROP_COLOR_NAME));
		assertFalse(names.contains("This_name_should_not_exist"));
	}

	@Test
	public void testPropertyNamesEmptyContainer()
	{
		// Arrange
		//
		List<PModel> list = new ArrayList<>();
		
		container = new PContainerStatic(list, null);
		
		
		// Act
		//
		List<String> names = container.propertyNames();
		
		// Assert
		//
		assertNotNull(names);
		assertEquals(0, names.size());
	}


	@Test
	public void testPropertyKeys()
	{
		// Arrange
		//
		List<PModel> list = new ArrayList<>();
		PType        type = TestFixtureUtil.PropertyType.STRING;
		
		list.add( PModelStatic.builder(type).name("n.1").key("K.1").build());
		list.add( PModelStatic.builder(type).name("n.2").key("K.2").build());
		list.add( PModelStatic.builder(type).name("n.3").key("K.3").build());
			
		container = new PContainerStatic(list, null);
		
		
		// Act
		//
		List<String> keys = container.propertyKeys();
		
		// Assert
		//
		// (Relies on ordering from PContainer
		//
		assertNotNull(keys);
		assertEquals(3, keys.size());
		assertTrue(keys.get(0).equals("K.1"));
		assertTrue(keys.get(1).equals("K.2"));
		assertTrue(keys.get(2).equals("K.3"));
		assertFalse(keys.contains("This_key_should_not_exist".toUpperCase()));
	}

	@Test
	public void testPropertyKeysEmptyContainer()
	{
		// Arrange
		//
		List<PModel> list = new ArrayList<>();
		
		container = new PContainerStatic(list, null);
		
		
		// Act
		//
		List<String> keys = container.propertyKeys();
		
		// Assert
		//
		assertNotNull(keys);
		assertEquals(0, keys.size());
	}

	
	@Test
	public void testPropertiesEmptyContainerNoPredicate()
	{
		// Arrange
		//
		List<PModel> list = new ArrayList<>();
		
		container = new PContainerStatic(list, null);
		
		
		// Act
		//
		List<PModel> props = container.properties(null);
		
		// Assert
		//
		assertNotNull(props);
		assertEquals(0, props.size());
	}
	@Test
	public void testPropertiesContainerWithPredicate()
	{
		// Arrange
		//
		List<PModel>   list = new ArrayList<>();
		PModelPredicate   p = new PropertyRequired();
		PType        type = TestFixtureUtil.PropertyType.STRING;
		
		list.add( PModelStatic.builder(type).name("n.1").key("K.1").required(false).build());
		list.add( PModelStatic.builder(type).name("n.2").key("K.2").required(true).build());
		list.add( PModelStatic.builder(type).name("n.3").key("K.3").required(false).build());
		
		container = new PContainerStatic(list, null);
		
		
		// Act
		//
		List<PModel> props = container.properties(p);
		PModel        prop = props.get(0);
		
		// Assert
		//
		assertNotNull(props);
		assertNotNull(prop);
		assertEquals(1, props.size());
		assertTrue("K.2".equals(prop.key()));
	}
	
	@Test
	public void testPropertiesContainerNoPredicate()
	{
		// Arrange
		//
		List<PModel>   list = new ArrayList<>();
		PType          type = TestFixtureUtil.PropertyType.STRING;
		
		list.add( PModelStatic.builder(type).name("n.1").key("K.1").required(false).build());
		list.add( PModelStatic.builder(type).name("n.2").key("K.2").required(true).build());
		list.add( PModelStatic.builder(type).name("n.3").key("K.3").required(false).build());
		
		container = new PContainerStatic(list, null);
		
		
		// Act
		//
		List<PModel> props = container.properties(null);
		
		// Assert
		//
		assertNotNull(props);
		assertEquals(3, props.size());
	}
	
	@Test
	public void testPropertiesEmptyContainerWithPredicate()
	{
		// Arrange
		//
		List<PModel>   list = new ArrayList<>();
		PModelPredicate   p = new PropertyRequired();
		
		container = new PContainerStatic(list, null);
		
		
		// Act
		//
		List<PModel> props = container.properties(p);
		
		// Assert
		//
		assertNotNull(props);
		assertEquals(0, props.size());
	}

	@Test
	public void testPropertyLookup()
	{
		// Arrange
		//
		List<PModel> list = new ArrayList<>();
		PType        type = TestFixtureUtil.PropertyType.STRING;
		
		list.add( PModelStatic.builder(type).name("n.1").key("K.1").build());
		list.add( PModelStatic.builder(type).name("n.2").key("K.2").build());
		list.add( PModelStatic.builder(type).name("n.3").key("K.3").build());
			
		container = new PContainerStatic(list, null);
		
		
		// Act
		//
		PModel m1 = container.property("K.1");
		PModel m2 = container.property("K.2");
		PModel mx = container.property("K.X");
		
		// Assert
		//
		assertNotNull(m1);
		assertNotNull(m2);
		assertNull(mx);

		assertTrue(m1.key().equals("K.1"));
		assertTrue(m2.key().equals("K.2"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullKeyIsCausesExceptionInPropertyLookup()
	{
		// Arrange
		//
		
		// Act
		//
		@SuppressWarnings("unused")
		PModel model = container.property(null);
		
		// Assert
		//
		fail("Should have thrown exception. Instead execution continued to this point");
	}


}
