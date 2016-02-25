package xivvic.model.ri;

import java.util.ArrayList;
import java.util.List;

import xivvic.model.api.DType;
import xivvic.model.api.EModel;
import xivvic.model.api.EType;
import xivvic.model.api.PContainer;
import xivvic.model.api.PModel;
import xivvic.model.api.PType;
import xivvic.model.api.RModel;
import xivvic.model.api.RType;

/**
 * Purposefully package private class visibility because the Enums defined
 * here are only for testing purposes. An application using this domain model
 * will have its own definitions for Entities / Documents / Relationships / and Properties
 * and these definitions would potentially conflict.
 * 
 * @author reid.dev
 *
 */
class TestFixtureUtil
{
	public static final String PROP_ID_NAME        = "id";
	public static final String PROP_NAME_NAME      = "name";
	public static final String PROP_COLOR_NAME     = "color";
	
	public enum EntityType
		implements EType
		{
			GROUP,
			USER,
			ORGANIZATION,
			EVENT,
		}

	public enum RelationshipType
	implements RType
	{
		USER_2_GROUP,
		GROUP_2_ORG,
	}

	public enum PropertyType
	implements PType
	{
		STRING(true),
		INTEGER(false),
		DATE(true),
		;

		/* 
	    * This constructor is private.
	    * Legal to declare a non-private constructor, but not legal
	    * to use such a constructor outside the enum.
	    * Can never use "new" with any enum, even inside the enum 
	    * class itself.
	    */
	    private PropertyType(boolean quoted)
	    {
	      //cannot call super ctor here
	      //calls to "this" ctors allowed
	      this.isQuoted = quoted;
	    }

	    public final boolean isQuotedType() 
	    {
	      return isQuoted;
	    }

	    private final boolean isQuoted;	
	}

	public enum DocumentType
	implements DType
	{
		IMAGE,
		ARCHIVE,
	}

	public static List<PModel> build3PropertyList()
	{
		List<PModel> list = new ArrayList<>();
		
		list.add(TestFixtureUtil.buildIdProperty());
		list.add(TestFixtureUtil.buildNameProperty());
		list.add(TestFixtureUtil.buildColorProperty());
		
		return list;
	}

	
	public static PModel buildNameProperty()
	{
		PType   type = PropertyType.STRING;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type);
		builder.name(PROP_NAME_NAME);
		builder.key(PROP_NAME_NAME);
		builder.required(true);
		
		PModel model = builder.build();
		return model;
	}

	public static PModel buildIdProperty()
	{
		PType   type = PropertyType.STRING;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type);
		builder.name(PROP_ID_NAME);
		builder.key(PROP_ID_NAME);
		builder.required(true);
		builder.unique(true);
		
		PModel model = builder.build();
		return model;
	}

	public static PModel buildColorProperty()
	{
		PType   type = PropertyType.INTEGER;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type);
		builder.name(PROP_COLOR_NAME);
		builder.key(PROP_COLOR_NAME);
		builder.required(false);
		builder.unique(false);
		
		PModel model = builder.build();
		return model;
	}
	
	public static EModel buildEmptyEntityModel(EType type)
	{
		List<PModel>    list = new ArrayList<>();
		PContainer container = new PContainerStatic(list, null);
		EModel         model = new EModelStatic(type, container);
		
		return model;
	}
	
	
	public static EModel buildUserEntity()
	{
		List<PModel> list = new ArrayList<>();
		
		PModel a = PModelStatic.builder(PropertyType.STRING)
				.name("id")
				.key("user_id")
				.unique(true)
				.required(true)
				.build();
		PModel b = PModelStatic.builder(PropertyType.STRING)
				.name("email")
				.key("user_email")
				.unique(true)
				.required(true)
				.build();
		PModel c = PModelStatic.builder(PropertyType.STRING)
				.name("user name")
				.key("user_username")
				.unique(true)
				.required(true)
				.build();
		PModel d = PModelStatic.builder(PropertyType.STRING)
				.name("password hash")
				.key("user_passhash")
				.unique(false)
				.required(true)
				.build();
		PModel e = PModelStatic.builder(PropertyType.STRING)
				.name("salt")
				.key("user_salt")
				.unique(false)
				.required(false)
				.build();
			
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		
		PContainer pc = new PContainerStatic(list, a);
		EModel  model = new EModelStatic(EntityType.USER, pc);
		
		return model;
	}

	public static EModel buildGroupEntity()
	{
		
		PModel a = PModelStatic.builder(PropertyType.STRING)
				.name("id")
				.key("group_id")
				.unique(true)
				.required(true)
				.build();

		PModel b = PModelStatic.builder(PropertyType.STRING)
				.name("name")
				.key("group_name")
				.unique(true)
				.required(true)
				.build();
			
		List<PModel> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		
		PContainer pc = new PContainerStatic(list, a);
		EModel  model = new EModelStatic(EntityType.GROUP, pc);
		
		return model;
	}


	public static RModel buildUser2GroupRel()
	{
		EModel u = buildUserEntity();
		EModel g = buildGroupEntity();
		
		PModel color_prop = buildColorProperty();
		List<PModel> list = new ArrayList<>();
		list.add(color_prop);
		
		PContainer pc = new PContainerStatic(list, null);
		RType  r_type = RelationshipType.USER_2_GROUP;

		RModelStatic.Builder builder = RModelStatic.builder(r_type, pc);

		builder.from(u);
		builder.to(g);

		RModel rel = builder.build();
		
		return rel;
	}


	

/*	
	public static  List<EModel> buildGroups()
	{
		List<PModel> p_list = new ArrayList<>();
		
		
		PModel a = PModelStatic.builder(null)
				.type(String.class)
				.name("id")
				.key("address_id")
				.unique(true)
				.required(true)
				.build();
		PModel b = PModelStatic.builder()
				.type(String.class)
				.name("line one")
				.key("address_line_one")
				.unique(false)
				.required(true)
				.build();
		PModel c = PModelStatic.builder()
				.type(String.class)
				.name("line two")
				.key("address_line_two")
				.unique(false)
				.required(false)
				.build();
		PModel d = PModelStatic.builder()
				.type(String.class)
				.name("city")
				.key("address_city")
				.unique(false)
				.required(false)
				.build();
		PModel e = PModelStatic.builder()
				.type(String.class)
				.name("state")
				.key("address_state")
				.unique(false)
				.required(false)
				.build();
		PModel f = PModelStatic.builder()
				.type(String.class)
				.name("zip")
				.key("address_zip")
				.unique(false)
				.required(false)
				.build();
			
		p_list.add(a);
		p_list.add(b);
		p_list.add(c);
		p_list.add(d);
		p_list.add(e);
		p_list.add(f);
		
		EModel schema = new EModelStatic(EType.ADDRESS, p_list);
		
		entities.put(Address.class, schema);
	}
*/

}
