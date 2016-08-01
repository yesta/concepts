package sensor;

import java.io.Serializable;

public class SensorImp implements Sensor, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int location;
	private String type;
	private int object;

	public SensorImp(final int an_id, final String a_name,
			final String the_type, final int a_locId) {
		id = an_id;
		name = a_name;
		location = a_locId;
		type = the_type;
	}
	
	public SensorImp(final int an_id, final String a_name,
			final int a_locId, final int an_objId) {
		id = an_id;
		name = a_name;
		location = a_locId;
		object = an_objId;
	}
	
	public SensorImp(final int an_id, final String a_name,
			final String the_type, final int a_locId, final int an_objId) {
		id = an_id;
		name = a_name;
		location = a_locId;
		object = an_objId;
		type = the_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String print() {
		return (id + " " + name + " @" + location);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public void setType(String the_type) {
		// TODO Auto-generated method stub
		type = the_type;
	}

	@Override
	public int getObject() {
		return object;
	}

	@Override
	public void setObject(int the_object_id) {
		object = the_object_id;
	}

}
