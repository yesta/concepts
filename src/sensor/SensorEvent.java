package sensor;

public interface SensorEvent {
	
	int getSensorId();
	
	void setSensorId(final int an_id);
	
//	String getSensorName();
//	
//	void setSensorName(final String a_name);
	
	long getStartTime();
	
	void setStartTime(final long a_time);
	
	long getEndTime();
	
	void setEndTime(final long a_time);
	
	double getSensorValue();
	
	void setSensorvalue(final double a_value);
	
	String print();

}
