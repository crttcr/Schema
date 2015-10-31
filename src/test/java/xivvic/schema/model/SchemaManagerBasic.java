package xivvic.schema.model;


import java.util.HashMap;
import java.util.Map;

public class SchemaManagerBasic
//	implements SchemaManager
{
	private Map<EdgeType, EdgeSchema> edges = new HashMap<>();
	private Map<VertexType, VertexSchema> entities = new HashMap<>();
	private static SchemaManagerBasic INSTANCE = new SchemaManagerBasic();
	
/*
	private SchemaManagerBasic()
	{
		init();
	}
	
	public static SchemaManager getInstance()
	{
		return INSTANCE;
	}

	@Override
	public List<VertexType> entityTypes()
	{
		return new ArrayList<VertexType>(entities.keySet());
	}

	@Override
	public List<VertexSchema> entityDescriptors()
	{
		return new ArrayList<VertexSchema>(entities.values());
	}

	@Override
	public EdgeSchema getEdgeSchema(EdgeType type)
	{
		if (type == null)
			return null;
		
		return edges.get(type);
	}

	@Override
	public VertexSchema getEntitySchema(VertexType type)
	{
		if (type == null)
			return null;
		
		return entities.get(type);
	}

	private void init()
	{
		buildAddress();
		buildEvent();
		buildGroup();
		buildPerson();
		buildSubscription();
		buildUser();
		
		buildEdges();
	}

	private void buildEdges()
	{
		List<PropertySchema> p_list = new ArrayList<>();

		// Don't have any edge properties defined at the moment
		// Except for Type on address
		//
		VertexType[] edge_types = VertexType.values();
		
		for (VertexType e_type : edge_types)
		{
			if (e_type == VertexType.PERSON_ADDRESS)
			{
				PropertySchema a = PropertySchemaBase.builder()
						.type(String.class)
						.name("type")
						.key("address_type")
						.unique(false)
						.required(true)
						.build();

				List<PropertySchema> a_list = new ArrayList<>();
				a_list.add(a);
				EdgeSchema es = new EdgeSchemaImpl(e_type, a_list);
				edges.put(e_type, es);
				continue;
			}

			EdgeSchema schema = new EdgeSchemaImpl(e_type, p_list);
			
			edges.put(e_type, schema);
		}
	}
	
	private void buildAddress()
	{
		List<PropertySchema> p_list = new ArrayList<>();
		
		PropertySchema a = PropertySchemaBase.builder()
				.type(String.class)
				.name("id")
				.key("address_id")
				.unique(true)
				.required(true)
				.build();
		PropertySchema b = PropertySchemaBase.builder()
				.type(String.class)
				.name("line one")
				.key("address_line_one")
				.unique(false)
				.required(true)
				.build();
		PropertySchema c = PropertySchemaBase.builder()
				.type(String.class)
				.name("line two")
				.key("address_line_two")
				.unique(false)
				.required(false)
				.build();
		PropertySchema d = PropertySchemaBase.builder()
				.type(String.class)
				.name("city")
				.key("address_city")
				.unique(false)
				.required(false)
				.build();
		PropertySchema e = PropertySchemaBase.builder()
				.type(String.class)
				.name("state")
				.key("address_state")
				.unique(false)
				.required(false)
				.build();
		PropertySchema f = PropertySchemaBase.builder()
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
		
		VertexSchema schema = new VertexSchemaImmutable(VertexType.ADDRESS, p_list);
		
		entities.put(Address.class, schema);
	}

	private void buildEvent()
	{
		List<PropertySchema> p_list = new ArrayList<>();
		
		PropertySchema a = PropertySchemaBase.builder()
				.type(String.class)
				.name("id")
				.key("event_id")
				.unique(true)
				.required(true)
				.build();
		PropertySchema b = PropertySchemaBase.builder()
				.type(String.class)
				.name("type")
				.key("event_type")
				.unique(false)
				.required(true)
				.build();
		PropertySchema c = PropertySchemaBase.builder()
				.type(String.class)
				.name("text")
				.key("event_text")
				.unique(false)
				.required(true)
				.build();
		PropertySchema d = PropertySchemaBase.builder()
				.type(String.class)
				.name("date")
				.key("event_date")
				.unique(false)
				.required(false)
				.build();
		PropertySchema e = PropertySchemaBase.builder()
				.type(String.class)
				.name("time")
				.key("event_time")
				.unique(false)
				.required(false)
				.build();
			
		p_list.add(a);
		p_list.add(b);
		p_list.add(c);
		p_list.add(d);
		p_list.add(e);
		
		VertexSchema schema = new VertexSchemaImmutable(VertexType.EVENT, p_list);
		
		entities.put(Event.class, schema);
	}

//	private void buildGroup()
//	{
//		List<PropertySchema> p_list = new ArrayList<>();
//		
//		PropertySchema a = PropertySchemaBase.builder()
//				.type(String.class)
//				.name("id")
//				.key("group_id")
//				.unique(true)
//				.required(true)
//				.build();
//		PropertySchema b = PropertySchemaBase.builder()
//				.type(String.class)
//				.name("name")
//				.key("group_name")
//				.unique(true)
//				.required(true)
//				.build();
//			
//		p_list.add(a);
//		p_list.add(b);
//		
//		EntitySchema schema = new EntitySchemaImmutable(EntityType.GROUP, p_list);
//		
//		entities.put(Group.class, schema);
//	}

	private void buildSubscription()
	{
		List<PropertySchema> p_list = new ArrayList<>();
		
		PropertySchema a = PropertySchemaBase.builder()
				.type(String.class)
				.name("id")
				.key("subs_id")
				.unique(true)
				.required(true)
				.build();
		PropertySchema b = PropertySchemaBase.builder()
				.type(String.class)
				.name("expiry")
				.key("subs_expiry")
				.unique(true)
				.required(true)
				.build();
			
		p_list.add(a);
		p_list.add(b);
		
		VertexSchema schema = new VertexSchemaImmutable(VertexType.SUBSCRIPTION, p_list);
		
		entities.put(Subscription.class, schema);
	}

	private void buildUser()
	{
		List<PropertySchema> p_list = new ArrayList<>();
		
		PropertySchema a = PropertySchemaBase.builder()
				.type(String.class)
				.name("id")
				.key("user_id")
				.unique(true)
				.required(true)
				.build();
		PropertySchema b = PropertySchemaBase.builder()
				.type(String.class)
				.name("email")
				.key("user_email")
				.unique(true)
				.required(true)
				.build();
		PropertySchema c = PropertySchemaBase.builder()
				.type(String.class)
				.name("user name")
				.key("user_username")
				.unique(true)
				.required(true)
				.build();
		PropertySchema d = PropertySchemaBase.builder()
				.type(String.class)
				.name("password hash")
				.key("user_passhash")
				.unique(false)
				.required(true)
				.build();
		PropertySchema e = PropertySchemaBase.builder()
				.type(String.class)
				.name("salt")
				.key("user_salt")
				.unique(false)
				.required(false)
				.build();
			
		p_list.add(a);
		p_list.add(b);
		p_list.add(c);
		p_list.add(d);
		p_list.add(e);
		
		VertexSchema schema = new VertexSchemaImmutable(VertexType.USER, p_list);
		
		entities.put(User.class, schema);
	}


	private void buildPerson()
	{
		List<PropertySchema> p_list = new ArrayList<>();
		
		PropertySchema a = PropertySchemaBase.builder()
				.type(String.class)
				.name("id")
				.key("person_id")
				.unique(true)
				.required(true)
				.build();

		PropertySchema b = PropertySchemaBase.builder()
				.type(String.class)
				.name("first name")
				.key("person_name_first")
				.unique(false)
				.required(true)
				.build();
		PropertySchema c = PropertySchemaBase.builder()
				.type(String.class)
				.name("last name")
				.key("person_name_last")
				.unique(false)
				.required(true)
				.build();
		PropertySchema d = PropertySchemaBase.builder()
				.type(String.class)
				.name("middle name")
				.key("person_name_middle")
				.unique(false)
				.required(false)
				.build();
		PropertySchema e = PropertySchemaBase.builder()
				.type(String.class)
				.name("nickname")
				.key("person_nickname")
				.unique(false)
				.required(false)
				.build();
			
		p_list.add(a);
		p_list.add(b);
		p_list.add(c);
		p_list.add(d);
		p_list.add(e);
		
		VertexSchema schema = new VertexSchemaImmutable(VertexType.PERSON, p_list);
		
		entities.put(Person.class, schema);
	}
*/
}
