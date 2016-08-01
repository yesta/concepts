package activity;

public class Context {
	/**
	 * the domain of the context is about: temperature, or energy consumption
	 */
	private String my_domain;
	/**
	 * the location where the context is about
	 */
	private String my_location;
	/**
	 * the object which the context is about
	 */
	private String my_object;
	/**
	 * the value is required for the context. 
	 * This can be a numeric threshold value, "20" degree
	 * or a characterised value, "cold", "high"
	 */
	private String my_value;
	
	public Context(final String the_domain, final String the_value) {
		my_domain = the_domain;
		my_value = the_value;
	}

	public Context(final String the_domain, final String the_value, final String the_location) {
		my_domain = the_domain;
		my_value = the_value;
		my_location = the_location;
	}
	
	public Context(final String the_domain, final String the_value, final String the_location, final String the_object) {
		my_domain = the_domain;
		my_value = the_value;
		my_location = the_location;
		my_object = the_object;
	}
	
	public String getDomain() {
		return my_domain;
	}

	public void setDomain(String my_domain) {
		this.my_domain = my_domain;
	}

	public String getValue() {
		return my_value;
	}

	public void setValue(String my_value) {
		this.my_value = my_value;
	}

	public String getLocation() {
		return my_location;
	}

	public void setLocation(String my_location) {
		this.my_location = my_location;
	}

	public String getObject() {
		return my_object;
	}

	public void setObject(String my_object) {
		this.my_object = my_object;
	}
	
}
