package business;

import java.io.Serializable;

public enum UserRole implements Serializable {
	Admin(1), Cashier(2), Both(3);

	int intValue;

	UserRole(int value) {
		this.intValue = value;
	}

	public boolean IsA(UserRole checkRole) {
		if (checkRole == Both) {
			return true;
		}
		return (this.intValue & checkRole.intValue) == checkRole.intValue;
	}
}
