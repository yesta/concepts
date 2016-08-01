package sensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import concept.ConceptUtil;

public class SensorUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String LOCATION = "L";
	private ConceptUtil locations;
	private ConceptUtil objects;
	private List<Sensor> all_sensors;

	public SensorUtil(final ConceptUtil the_locations) {
		locations = the_locations;
		all_sensors = new ArrayList<Sensor>();
	}

	public SensorUtil(final ConceptUtil the_locations,
			final ConceptUtil the_objectTypes) {
		this(the_locations);
		objects = the_objectTypes;
	}

	public ConceptUtil getLocation() {
		return locations;
	}

	public ConceptUtil getObjects() {
		return objects;
	}

	public void addSensor(final String a_name, final String the_type,
			final int the_locId) {
		int index = all_sensors.size();
		all_sensors.add(new SensorImp(index, a_name, the_type, the_locId));
	}

	public void addSensorWithTypeLocation(final String a_name,
			final String the_type, final String the_loc) {
		int index = all_sensors.size();
		all_sensors.add(new SensorImp(index, a_name, the_type, locations
				.findConcept(the_loc).getId()));
	}

	public void addSensorWithLocationObj(final String a_name,
			final String the_locId, final String the_obj) {
		int index = all_sensors.size();
		all_sensors.add(new SensorImp(index, a_name, locations.findConcept(
				the_locId).getId(), objects.findConcept(the_obj).getId()));
	}

	// public void addSensor(final String a_name, final String the_type, final
	// String a_location) {
	// int index = all_sensors.size();
	// try{
	// int the_loc_id = locations.findConcept(a_location).getId();
	// all_sensors.add(new SensorImp(index, a_name, the_type, the_loc_id));
	// } catch (NullPointerException e) {
	// System.err.println(a_location);
	// }
	//
	// }

	public void addSensor(final Sensor a_sensor) {
		all_sensors.add(a_sensor);
	}

	/**
	 * find the sensor whose sensor name is the given name
	 * 
	 * @param sensors
	 * @param sensorName
	 * @return
	 */
	public Sensor findSensor(String sensorName) {
		Sensor s = null;
		for (Sensor sensor : all_sensors) {
			if (sensor.getName().equals(sensorName)) {
				s = sensor;
				break;
			}
		}
		if (s == null) {
			s = findSensorWithPartialName(sensorName);
			// if (s == null)
			// System.out.println("SensorUtil not found: " + sensorName);
		}
		return s;
	}

	private Sensor findSensorWithPartialName(String sensorName) {
		for (Sensor sensor : all_sensors) {
			if (sensor.getName().contains(sensorName)) {
				return sensor;
			}
		}
		return null;
	}

	public Sensor findSensor(int sensorId) {
		if (sensorId < 0 || sensorId >= all_sensors.size()) {
			System.out.println(sensorId + " is invalid");
			return null;
		} else {
			return all_sensors.get(sensorId);
		}
	}

	public List<Sensor> getSensors() {
		return all_sensors;
	}

	/**
	 * check the similarity between two sensors. if they have different types,
	 * then similarity is 1; otherwise compare their locations.
	 * 
	 * @param one_sensor
	 * @param another_sensor
	 * @return
	 */
	public double similarity(Sensor one_sensor, Sensor another_sensor) {
		double sim = 1;
		if (one_sensor.getType() != null && another_sensor.getType() != null
				&& one_sensor.getType() != another_sensor.getType()) {
			sim = 1;
		} else if (one_sensor.getId() != another_sensor.getId()) {
			sim = locations.similarity(one_sensor.getLocation(),
					another_sensor.getLocation());
//			System.out.println("location: "+sim);
			if (objects != null) {
				// sim = (sim + objects.similarity(one_sensor.getObject(),
				// another_sensor.getObject())) / 2;
				sim = sim
						* 0.7
						+ objects.similarity(one_sensor.getObject(),
								another_sensor.getObject()) * 0.3;
			}
//			System.out.println("sim of "+one_sensor.getId()+" and "+another_sensor.getId()+" : "+sim);
		}
		return sim;
	}

}
