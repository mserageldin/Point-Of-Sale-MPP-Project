package business;

import dataaccess.IDataAccessable;

final public class User implements IDataAccessable<String> {

	private static final long serialVersionUID = 5147265048973262104L;

	private final String id;

	private final String password;
	private final UserRole role;

	public User(String id, String pass, UserRole role) {
		this.id = id;
		this.password = pass;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public UserRole getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + role.toString() + "]";
	}

	@Override
	public String getPrimaryKeyValue() {
		return id;
	}

}
