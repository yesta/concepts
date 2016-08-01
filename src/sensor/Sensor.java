package sensor;

public interface Sensor {
	String getType();

	void setType(final String the_type);

	String getName();

	void setName(final String the_name);

	int getId();

	void setId(final int the_id);

	/**
	 * get the index of the location concept where the sensor is installed
	 * 
	 * @return
	 */
	int getLocation();

	void setLocation(final int the_location_id);
	
	int getObject();
	
	void setObject(final int the_object_id);
	
	String print();
}
