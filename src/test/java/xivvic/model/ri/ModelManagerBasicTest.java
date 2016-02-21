package xivvic.model.ri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import xivvic.model.api.DModel;
import xivvic.model.api.EModel;
import xivvic.model.api.ModelManager;
import xivvic.model.api.RModel;

public class ModelManagerBasicTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testEmptyManager()
	{
		// Arrange
		//
		ModelManager mm = new ModelManagerBasic();
		
		// Act
		//
		List<EModel>  e_list = mm.entityModels();
		RModel       r_model = mm.getRModel(TestFixtureUtil.RelationshipType.USER_2_GROUP);
		EModel       e_model = mm.getEModel(TestFixtureUtil.EntityType.EVENT);
		DModel       d_model = mm.getDModel(TestFixtureUtil.DocumentType.IMAGE);
		
		// Assert
		//
		assertNotNull(e_list);
		assertTrue(e_list.isEmpty());
		
		assertNull(r_model);
		assertNull(e_model);
		assertNull(d_model);
	}

	@Test(expected=NullPointerException.class)
	public void testRegisterNullModel()
	{
		// Arrange
		//
		ModelManagerBasic mm = new ModelManagerBasic();
		
		// Act
		//
		mm.registerModel(null);
		
		// Assert
		//
		fail("Should have thrown exception. Instead execution continued to this point");
	}

	@Test
	public void testRegisterEntityModel()
	{
		// Arrange
		//
		ModelManagerBasic mm = new ModelManagerBasic();
		EModel         model = TestFixtureUtil.buildGroupEntity();
		RModel       r_model = TestFixtureUtil.buildUser2GroupRel();
		
		// Act
		//
		mm.registerModel(model);
		mm.registerModel(r_model);
		EModel returned = mm.getEModel(model.modelElementType());
		
		// Assert
		//
		assertNotNull(returned);
		assertEquals(model.modelElementType(), returned.modelElementType());
	}

	@Test
	public void testEntityModels()
	{
		// Arrange
		//
		ModelManagerBasic mm = new ModelManagerBasic();
		EModel       g_model = TestFixtureUtil.buildGroupEntity();
		EModel       u_model = TestFixtureUtil.buildUserEntity();
		RModel       r_model = TestFixtureUtil.buildUser2GroupRel();
		
		// Act
		//
		mm.registerModel(g_model);
		mm.registerModel(u_model);
		mm.registerModel(u_model);
		mm.registerModel(r_model);
		
		List<EModel> list = mm.entityModels();
		
		
		// Assert
		//
		assertNotNull(list);
		assertEquals(2, list.size());
	}

	@Ignore
	@Test
	public void testRegisterDModel()
	{
		// Arrange
		//
		// Act
		//
		// Assert
		//
		fail("Not yet implemented. There is no implementation for DModel, until a use case shows up.");
	}

	@Test
	public void testRegisterRModel()
	{
		// Arrange
		//
		ModelManagerBasic mm = new ModelManagerBasic();
		RModel         model = TestFixtureUtil.buildUser2GroupRel();
		
		// Act
		//
		mm.registerModel(model);
		RModel returned = mm.getRModel(model.modelElementType());
		
		// Assert
		//
		assertNotNull(returned);
		assertEquals(model.modelElementType(), returned.modelElementType());
	}

}
