package business;

import dataaccess.IDataAccessable;

public class Supplier implements IDataAccessable<Integer> {
	private static final long serialVersionUID = 6656781949606485284L;
	private String name;
	private Integer id;
	private Address address;

	public Supplier(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Integer getPrimaryKeyValue() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("[Id: %s, Name: %s, Address: %s]", id, name, address);
	}
}