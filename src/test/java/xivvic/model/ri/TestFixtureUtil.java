package xivvic.model.ri;

import xivvic.model.api.EType;
import xivvic.model.api.PModel;
import xivvic.model.api.PType;
import xivvic.model.api.RType;

public class TestFixtureUtil
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
		STRING,
		INTEGER,
		DATE,
	}
	
	public static PModel buildNameProperty()
	{
		PType   type = PropertyType.STRING;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type);
		builder.name(PROP_NAME_NAME);
		builder.required(true);
		
		PModel model = builder.build();
		return model;
	}

	public static PModel buildIdProperty()
	{
		PType   type = PropertyType.STRING;
		
		PModelStatic.Builder builder =  PModelStatic.builder(type);
		builder.name(PROP_ID_NAME);
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
		builder.required(false);
		builder.unique(false);
		
		PModel model = builder.build();
		return model;
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
