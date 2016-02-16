package xivvic.model.ri;


import java.util.HashMap;
import java.util.Map;

import xivvic.model.api.EModel;
import xivvic.model.api.EType;
import xivvic.model.api.RModel;
import xivvic.model.api.RType;

public class SchemaManagerBasic
//	implements ModelManager
{
	private Map<RType, RModel> edges = new HashMap<>();
	private Map<EType, EModel> entities = new HashMap<>();
	private static SchemaManagerBasic INSTANCE = new SchemaManagerBasic();
	
/*
	private SchemaManagerBasic()
	{
		init();
	}
	
	public static ModelManager getInstance()
	{
		return INSTANCE;
	}

	@Override
	public List<EType> entityTypes()
	{
		return new ArrayList<EType>(entities.keySet());
	}

	@Override
	public List<EModel> entityDescriptors()
	{
		return new ArrayList<EModel>(entities.values());
	}

	@Override
	public RModel getEdgeSchema(RType type)
	{
		if (type == null)
			return null;
		
		return edges.get(type);
	}

	@Override
	public EModel getEntitySchema(EType type)
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
		List<PModel> p_list = new ArrayList<>();

		// Don't have any edge properties defined at the moment
		// Except for ModelElementType on address
		//
		EType[] edge_types = EType.values();
		
		for (EType e_type : edge_types)
		{
			if (e_type == EType.PERSON_ADDRESS)
			{
				PModel a = PModelStatic.builder()
						.type(String.class)
						.name("type")
						.key("address_type")
						.unique(false)
						.required(true)
						.build();

				List<PModel> a_list = new ArrayList<>();
				a_list.add(a);
				RModel es = new RModelStatic(e_type, a_list);
				edges.put(e_type, es);
				continue;
			}

			RModel schema = new RModelStatic(e_type, p_list);
			
			edges.put(e_type, schema);
		}
	}
	
	private void buildAddress()
	{
		List<PModel> p_list = new ArrayList<>();
		
		PModel a = PModelStatic.builder()
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

	private void buildEvent()
	{
		List<PModel> p_list = new ArrayList<>();
		
		PModel a = PModelStatic.builder()
				.type(String.class)
				.name("id")
				.key("event_id")
				.unique(true)
				.required(true)
				.build();
		PModel b = PModelStatic.builder()
				.type(String.class)
				.name("type")
				.key("event_type")
				.unique(false)
				.required(true)
				.build();
		PModel c = PModelStatic.builder()
				.type(String.class)
				.name("text")
				.key("event_text")
				.unique(false)
				.required(true)
				.build();
		PModel d = PModelStatic.builder()
				.type(String.class)
				.name("date")
				.key("event_date")
				.unique(false)
				.required(false)
				.build();
		PModel e = PModelStatic.builder()
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
		
		EModel schema = new EModelStatic(EType.EVENT, p_list);
		
		entities.put(Event.class, schema);
	}

//	private void buildGroup()
//	{
//		List<PModel> p_list = new ArrayList<>();
//		
//		PModel a = PModelStatic.builder()
//				.type(String.class)
//				.name("id")
//				.key("group_id")
//				.unique(true)
//				.required(true)
//				.build();
//		PModel b = PModelStatic.builder()
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
		List<PModel> p_list = new ArrayList<>();
		
		PModel a = PModelStatic.builder()
				.type(String.class)
				.name("id")
				.key("subs_id")
				.unique(true)
				.required(true)
				.build();
		PModel b = PModelStatic.builder()
				.type(String.class)
				.name("expiry")
				.key("subs_expiry")
				.unique(true)
				.required(true)
				.build();
			
		p_list.add(a);
		p_list.add(b);
		
		EModel schema = new EModelStatic(EType.SUBSCRIPTION, p_list);
		
		entities.put(Subscription.class, schema);
	}

	private void buildUser()
	{
		List<PModel> p_list = new ArrayList<>();
		
		PModel a = PModelStatic.builder()
				.type(String.class)
				.name("id")
				.key("user_id")
				.unique(true)
				.required(true)
				.build();
		PModel b = PModelStatic.builder()
				.type(String.class)
				.name("email")
				.key("user_email")
				.unique(true)
				.required(true)
				.build();
		PModel c = PModelStatic.builder()
				.type(String.class)
				.name("user name")
				.key("user_username")
				.unique(true)
				.required(true)
				.build();
		PModel d = PModelStatic.builder()
				.type(String.class)
				.name("password hash")
				.key("user_passhash")
				.unique(false)
				.required(true)
				.build();
		PModel e = PModelStatic.builder()
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
		
		EModel schema = new EModelStatic(EType.USER, p_list);
		
		entities.put(User.class, schema);
	}


	private void buildPerson()
	{
		List<PModel> p_list = new ArrayList<>();
		
		PModel a = PModelStatic.builder()
				.type(String.class)
				.name("id")
				.key("person_id")
				.unique(true)
				.required(true)
				.build();

		PModel b = PModelStatic.builder()
				.type(String.class)
				.name("first name")
				.key("person_name_first")
				.unique(false)
				.required(true)
				.build();
		PModel c = PModelStatic.builder()
				.type(String.class)
				.name("last name")
				.key("person_name_last")
				.unique(false)
				.required(true)
				.build();
		PModel d = PModelStatic.builder()
				.type(String.class)
				.name("middle name")
				.key("person_name_middle")
				.unique(false)
				.required(false)
				.build();
		PModel e = PModelStatic.builder()
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
		
		EModel schema = new EModelStatic(EType.PERSON, p_list);
		
		entities.put(Person.class, schema);
	}
*/
}
