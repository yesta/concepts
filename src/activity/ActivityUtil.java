package activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sensor.Sensor;
import sensor.SensorUtil;

public class ActivityUtil implements Serializable{
	private List<Activity> all_activities;

	public ActivityUtil() {
		all_activities = new ArrayList<Activity>();
	}

	public List<Activity> getActivities() {
		return all_activities;
	}

	public void addActivity(final Activity the_act) {
		all_activities.add(the_act);
	}

	public void addActivity(final String an_actName, final List<Integer> the_loc) {
		final int index = all_activities.size();
		Activity the_act = new Activity(index, an_actName);
		the_act.setLocation(the_loc);
		all_activities.add(the_act);
	}

	public void addActivity(final String the_actName, final int min_time,
			final int max_time, final int min_stime, final int max_stime,
			final List<Integer> the_locations) {
		final int index = all_activities.size();
		Activity the_act = new Activity(index, the_actName);
		the_act.setLocation(the_locations);
		the_act.setPeriod(min_time, max_time);
		the_act.setStartTime(min_stime, max_stime);
		all_activities.add(the_act);
	}

	public Activity findActivity(final int the_actId) {
		if (the_actId < 0 || the_actId >= all_activities.size()) {
			return null;
		} else{
			return all_activities.get(the_actId);
		}
	}

	/**
	 * find an activity whose name matches the given activity name
	 * 
	 * @param the_actName
	 * @return
	 */
	public Activity findActivity(final String the_actName) {
		Activity result = null;
		for (Activity act : all_activities) {
			if (the_actName.toLowerCase().trim()
					.equals(act.getActName().toLowerCase().trim())) {
				result = act;
				break;
			}
		}
		if (result == null) {
			result = findActivityWithPartialName(the_actName);
			// if(result == null)
			// System.out.println("ACTIVITY UTIL cannot find: " + the_actName);
		}
		return result;
	}

	private Activity findActivityWithPartialName(final String the_actName) {
		Activity result = null;
		for (Activity act : all_activities) {
			if (the_actName.toLowerCase().contains(
					act.getActName().toLowerCase().trim())) {
				result = act;
				break;
			}
		}
		return result;
	}

	/**
	 * find the relevant sensors based on the description of activity
	 * 
	 * @param the_act
	 * @return
	 */
	public Set<Integer> findSensors(final SensorUtil the_sensors,
			final Activity the_act) {
		Set<Integer> relevant_sensors = new HashSet<Integer>();
		for (Sensor s : the_sensors.getSensors()) {
			if (the_act.getLocation().contains(s.getLocation())) {
				relevant_sensors.add(s.getId());
			} else {
				for (int actLoc : the_act.getLocation()) {
					if (the_sensors.getLocation().isSuper(
							the_sensors.getLocation().findConcept(actLoc),
							the_sensors.getLocation().findConcept(
									s.getLocation()))) {
						relevant_sensors.add(s.getId());
						break;
					}
				}
			}
		}
		return relevant_sensors;
	}

	/**
	 * relate activities to sensors
	 * 
	 * @param sensors
	 *            a sensor util
	 * @param activities
	 *            an activity
	 * @return map<activity id, set of <sensor ids>>
	 */
	public Map<Integer, Set<Integer>> associate2Sensors(
			final SensorUtil the_sensors) {
		Map<Integer, Set<Integer>> result = new HashMap<Integer, Set<Integer>>();
		// System.out.println(all_activities.size());
		for (Activity act : all_activities) {
			Set<Integer> relevant = findSensors(the_sensors, act);
			result.put(act.getActId(), relevant);
			// System.out.println(act.getActId()+": "+relevant);
		}
		// System.out.println("associate activity to sensors:\n" + result);
		return result;
	}
}
