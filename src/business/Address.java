package business;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 5264046647570888372L;
	private String street, city, state, zip;

	public Address(String s, String c, String state, String zip) {
		this.street = s;
		this.city = c;
		this.state = state;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	@Override
	public String toString() {
		return String.format("[State: %s, City: %s, Street: %s, Zip Code: %s]", state, city, street, zip);
	}
}
