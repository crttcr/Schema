package xivvic.model.ri;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xivvic.model.api.EModel;
import xivvic.model.api.ModelElement;
import xivvic.model.api.PContainer;
import xivvic.model.api.PModel;
import xivvic.model.api.RModel;
import xivvic.model.api.RType;

public class RModelStaticTest
{
	private RModel rel_model;
	

	@Before
	public void setUp() throws Exception
	{
		List<PModel>            list = TestFixtureUtil.build3PropertyList();
		PContainer                pc = new PContainerStatic(list, null);
		RType                   type = TestFixtureUtil.RelationshipType.USER_2_GROUP;
		RModelStatic.Builder builder = RModelStatic.builder(type, pc);

		EModel entity_a = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.USER);
		EModel entity_b = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.GROUP);

		builder.from(entity_a);
		builder.to(entity_b);

		rel_model            = builder.build();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test(expected=NullPointerException.class)
	public void testNullToReferenceCausesBuildFailure()
	{
		// Arrange
		//
		List<PModel>            list = TestFixtureUtil.build3PropertyList();
		PContainer                pc = new PContainerStatic(list, null);
		RType                   type = TestFixtureUtil.RelationshipType.USER_2_GROUP;
		RModelStatic.Builder builder = RModelStatic.builder(type, pc);
		
		// Act
		//
		builder.to(null);
		
		// Assert
		//
		fail("Should have thrown exception. Instead execution continued to this point");
	}

	@Test(expected=NullPointerException.class)
	public void testMissingFromCausesBuildFailure()
	{
		// Arrange
		//
		List<PModel>            list = TestFixtureUtil.build3PropertyList();
		PContainer                pc = new PContainerStatic(list, null);
		RType                   type = TestFixtureUtil.RelationshipType.USER_2_GROUP;
		RModelStatic.Builder builder = RModelStatic.builder(type, pc);
		EModel                entity = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.EVENT);
		builder.to(entity);
		
		// Act
		//
		@SuppressWarnings("unused")
		RModel                 model = builder.build();
		
		// Assert
		//
		fail("Should have thrown exception. Instead execution continued to this point");
	}


	@Test(expected=NullPointerException.class)
	public void testMissingToCausesBuildFailure()
	{
		// Arrange
		//
		List<PModel>            list = TestFixtureUtil.build3PropertyList();
		PContainer                pc = new PContainerStatic(list, null);
		RType                   type = TestFixtureUtil.RelationshipType.USER_2_GROUP;
		RModelStatic.Builder builder = RModelStatic.builder(type, pc);
		EModel                entity = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.EVENT);
		builder.from(entity);
		
		// Act
		//
		@SuppressWarnings("unused")
		RModel                 model = builder.build();
		
		// Assert
		//
		fail("Should have thrown exception. Instead execution continued to this point");
	}

	@Test
	public void testFrom()
	{
		// Arrange
		//
		
		// Act
		// 
		ModelElement me = rel_model.from();
		
		// Assert
		// 
		assertNotNull(me);
		assertTrue(TestFixtureUtil.EntityType.USER == me.modelElementType());
	}

	@Test
	public void testTo()
	{
		// Arrange
		//
		
		// Act
		// 
		ModelElement me = rel_model.to();
		
		// Assert
		// 
		assertNotNull(me);
		assertTrue(TestFixtureUtil.EntityType.GROUP == me.modelElementType());
	}

	@Test
	public void testRequiredFalseByDefault()
	{
		// Arrange
		//
		
		// Act
		// 
		boolean required = rel_model.required();
		
		// Assert
		// 
		assertFalse(required);
	}

	@Test
	public void testMultipleOutboundTrueByDefault()
	{
		// Arrange
		//
		
		// Act
		// 
		boolean multi_out = rel_model.multipleOutbound();
		
		// Assert
		// 
		assertTrue(multi_out);
	}


	@Test
	public void testMultipleInboundTrueByDefault()
	{
		// Arrange
		//
		
		// Act
		// 
		boolean multi_in = rel_model.multipleInbound();
		
		// Assert
		// 
		assertTrue(multi_in);
	}

	@Test
	public void testMultipleInboundSetTrue()
	{
		// Arrange
		//
		List<PModel>            list = TestFixtureUtil.build3PropertyList();
		PContainer                pc = new PContainerStatic(list, null);
		RType                   type = TestFixtureUtil.RelationshipType.USER_2_GROUP;
		RModelStatic.Builder builder = RModelStatic.builder(type, pc);
		EModel                entity = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.EVENT);
		builder.from(entity);
		builder.to(entity);
		builder.multipleInbound(true);
		
		RModel model = builder.build();
		
		// Act
		// 
		boolean multi_in = model.multipleInbound();
		
		// Assert
		// 
		assertTrue(multi_in);
	}

	@Test
	public void testMultipleInboundSetFalse()
	{
		// Arrange
		//
		List<PModel>            list = TestFixtureUtil.build3PropertyList();
		PContainer                pc = new PContainerStatic(list, null);
		RType                   type = TestFixtureUtil.RelationshipType.USER_2_GROUP;
		RModelStatic.Builder builder = RModelStatic.builder(type, pc);
		EModel                entity = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.EVENT);
		builder.from(entity);
		builder.to(entity);
		builder.multipleInbound(false);
		
		RModel model = builder.build();
		
		// Act
		// 
		boolean multi_in = model.multipleInbound();
		
		// Assert
		// 
		assertFalse(multi_in);
	}

	@Test
	public void testSingleInstanceBetweenEndpointsSetTrue()
	{
		// Arrange
		//
		List<PModel>            list = TestFixtureUtil.build3PropertyList();
		PContainer                pc = new PContainerStatic(list, null);
		RType                   type = TestFixtureUtil.RelationshipType.USER_2_GROUP;
		RModelStatic.Builder builder = RModelStatic.builder(type, pc);
		EModel                entity = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.EVENT);
		builder.from(entity);
		builder.to(entity);
		builder.singleInstanceBetweenEndpoints(true);
		
		RModel model = builder.build();
		
		// Act
		// 
		boolean b = model.singleInstanceBetweenEndpoints();
		
		// Assert
		// 
		assertTrue(b);
	}

	@Test
	public void testSingleInstanceBetweenEndpointsSetFalse()
	{
		// Arrange
		//
		List<PModel>            list = TestFixtureUtil.build3PropertyList();
		PContainer                pc = new PContainerStatic(list, null);
		RType                   type = TestFixtureUtil.RelationshipType.USER_2_GROUP;
		RModelStatic.Builder builder = RModelStatic.builder(type, pc);
		EModel                entity = TestFixtureUtil.buildEmptyEntityModel(TestFixtureUtil.EntityType.EVENT);
		builder.from(entity);
		builder.to(entity);
		builder.singleInstanceBetweenEndpoints(false);
		
		RModel model = builder.build();
		
		// Act
		// 
		boolean b = model.singleInstanceBetweenEndpoints();
		
		// Assert
		// 
		assertFalse(b);
	}

	@Test
	public void testModelElementType()
	{
		// Arrange
		//
		
		// Act
		// 
		RType type = rel_model.modelElementType();
		
		// Assert
		// 
		assertTrue(TestFixtureUtil.RelationshipType.USER_2_GROUP == type);
	}

}
