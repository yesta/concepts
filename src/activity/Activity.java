package activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Activity implements Serializable{
	/**
	 * define the context to prescribe the occurring of this activity
	 */
	private Context context;

	/**
	 * possibly location ids where the activity is performed
	 */
	private List<Integer> location;
	/**
	 * key types of objects that the activities possibly involve
	 */
	private List<Integer> objects;
	/**
	 * the [min, max] time periods that an activity could last. in seconds
	 */
	private int[] periodConstraints;
	/**
	 * in hour of day
	 */
	private int[] startTimeConstraints;

	private int actId;

	private String actName;

	public Activity(final int an_id, final String a_name) {
		actId = an_id;
		actName = a_name;
		location = new ArrayList<Integer>();
		objects = new ArrayList<Integer>();
		periodConstraints = null;
		startTimeConstraints = null;
	}

	public Activity(final int an_id, final String a_name,
			final Context a_context) {
		actId = an_id;
		actName = a_name;
		context = a_context;
		location = new ArrayList<Integer>();
		objects = new ArrayList<Integer>();
		periodConstraints = null;
		startTimeConstraints = null;
	}

	public String getActName() {
		return actName;
	}

	public void ListActName(final String the_name) {
		actName = the_name;
	}

	public int getActId() {
		return actId;
	}

	public void ListActId(int actId) {
		this.actId = actId;
	}

	public void addLocation(final int locId) {
		location.add(locId);
	}

	public void ListLocation(final List<Integer> locs) {
		location = locs;
	}

	public List<Integer> getLocation() {
		return location;
	}

	public void addObject(final int locId) {
		objects.add(locId);
	}

	public void setLocation(final List<Integer> locs) {
		location = locs;
	}

	public List<Integer> getObject() {
		if (objects.isEmpty()) {
			objects.add(0);
		}
		return objects;
	}

	public void setPeriod(final int min, final int max) {
		periodConstraints = new int[2];
		periodConstraints[0] = min;
		periodConstraints[1] = max;
	}

	public int[] getPeriod() {
		return periodConstraints;
	}

	public void setStartTime(final int min, final int max) {
		startTimeConstraints = new int[2];
		startTimeConstraints[0] = min;
		startTimeConstraints[1] = max;
	}

	public int[] getStartTime() {
		return startTimeConstraints;
	}

	public void print() {
		System.out.println(actId + ", " + actName + " location: " + location);
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

}
