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
		
		list.add(TestFixtureUtil.buildIdProperty());
		list.add(TestFixtureUtil.buildNameProperty());
		list.add(TestFixtureUtil.buildColorProperty());
		
		container = new PContainerStatic(list);
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
		assertEquals(3, names.size());
		assertTrue(names.contains(TestFixtureUtil.PROP_ID_NAME));
		assertTrue(names.contains(TestFixtureUtil.PROP_NAME_NAME));
		assertTrue(names.contains(TestFixtureUtil.PROP_COLOR_NAME));
		assertFalse(names.contains("This_name_should_not_exist"));
	}

	@Test
	public void testPropertyNamesEmptyContainer()
	{
		// Arrange
		//
		List<PModel> list = new ArrayList<>();
		
		container = new PContainerStatic(list);
		
		
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
		
		list.add( PModelStatic.builder(type).name("n.1").key("k.1").build());
		list.add( PModelStatic.builder(type).name("n.2").key("k.2").build());
		list.add( PModelStatic.builder(type).name("n.3").key("k.3").build());
			
		container = new PContainerStatic(list);
		
		
		// Act
		//
		List<String> keys = container.propertyKeys();
		
		// Assert
		//
		assertNotNull(keys);
		assertEquals(3, keys.size());
		assertTrue(keys.contains("k.1"));
		assertTrue(keys.contains("k.2"));
		assertTrue(keys.contains("k.3"));
		assertFalse(keys.contains("This_key_should_not_exist"));
	}

	@Test
	public void testPropertyKeysEmptyContainer()
	{
		// Arrange
		//
		List<PModel> list = new ArrayList<>();
		
		container = new PContainerStatic(list);
		
		
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
		
		container = new PContainerStatic(list);
		
		
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
		
		list.add( PModelStatic.builder(type).name("n.1").key("k.1").required(false).build());
		list.add( PModelStatic.builder(type).name("n.2").key("k.2").required(true).build());
		list.add( PModelStatic.builder(type).name("n.3").key("k.3").required(false).build());
		
		container = new PContainerStatic(list);
		
		
		// Act
		//
		List<PModel> props = container.properties(p);
		PModel        prop = props.get(0);
		
		// Assert
		//
		assertNotNull(props);
		assertNotNull(prop);
		assertEquals(1, props.size());
		assertTrue("k.2".equals(prop.key()));
	}
	
	@Test
	public void testPropertiesContainerNoPredicate()
	{
		// Arrange
		//
		List<PModel>   list = new ArrayList<>();
		PType          type = TestFixtureUtil.PropertyType.STRING;
		
		list.add( PModelStatic.builder(type).name("n.1").key("k.1").required(false).build());
		list.add( PModelStatic.builder(type).name("n.2").key("k.2").required(true).build());
		list.add( PModelStatic.builder(type).name("n.3").key("k.3").required(false).build());
		
		container = new PContainerStatic(list);
		
		
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
		
		container = new PContainerStatic(list);
		
		
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
		
		list.add( PModelStatic.builder(type).name("n.1").key("k.1").build());
		list.add( PModelStatic.builder(type).name("n.2").key("k.2").build());
		list.add( PModelStatic.builder(type).name("n.3").key("k.3").build());
			
		container = new PContainerStatic(list);
		
		
		// Act
		//
		PModel m1 = container.property("k.1");
		PModel m2 = container.property("k.2");
		PModel mx = container.property("k.x");
		
		// Assert
		//
		assertNotNull(m1);
		assertNotNull(m2);
		assertNull(mx);

		assertTrue(m1.key().equals("k.1"));
		assertTrue(m2.key().equals("k.2"));
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
