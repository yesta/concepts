package sensor;

import java.io.Serializable;

import org.joda.time.DateTime;

public class SensorEventImp implements Serializable, SensorEvent {
	private static final long serialVersionUID = 1L;
//	private String sensorName;
	private int sensorId;
	private long startTime;
	private long endTime;
	private double value;

//	public SensorEventImp(final String a_sName, final long a_time) {
//		sensorName = a_sName;
//		startTime = a_time;
//	}

	public SensorEventImp(final int an_id, final long a_time) {
		sensorId = an_id;
		startTime = a_time;
	}

//	public SensorEventImp(final int an_id, final String a_name,
//			final long a_time) {
//		this(an_id, a_time);
//		sensorName = a_name;
//	}

	public SensorEventImp(final int an_id, final long a_time, final long a_end) {
		sensorId = an_id;
		startTime = a_time;
		endTime = a_end;
	}

//	public SensorEventImp(final int an_id, final String a_name,
//			final long a_time, final long a_end) {
//		this(an_id, a_time, a_end);
//		sensorName = a_name;
//	}

//	public SensorEventImp(final String a_sName, final long a_stime,
//			final long an_etime) {
//		this(a_sName, a_stime);
//		endTime = an_etime;
//	}

	public SensorEventImp(final int a_sId, final long a_stime,
			final int val) {
		this(a_sId, a_stime);
		value = val;
	}

	public SensorEventImp(final int a_sId, final long a_stime,
			final long an_etime, final int v) {
		this(a_sId, a_stime, an_etime);
		value = v;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(final int the_id) {
		sensorId = the_id;
	}

//	public String getSensorName() {
//		return sensorName;
//	}
//
//	public void setSensorName(String sensorId) {
//		this.sensorName = sensorId;
//	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String print() {
		return (":(" + sensorId + "," + value + "): ["
				+ new DateTime(startTime).toString() + ","
				+ new DateTime(endTime).toString() + "]");
	}

	@Override
	public double getSensorValue() {
		return value;
	}

	@Override
	public void setSensorvalue(double a_value) {
		this.value = a_value;
	}


}
