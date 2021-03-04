package business;

import java.util.HashMap;
import java.util.Optional;

import dataaccess.DataBasePOS;

public class AppManager {

	public static AppManager Instance = new AppManager();

	private User currentUser;
	private final HashMap<String, UserRole> screens;

	private AppManager() {
		screens = new HashMap<>();

		screens.put("ManageSuppliers", UserRole.Admin);
		screens.put("PurchaseOrder", UserRole.Admin);
		screens.put("Reporting", UserRole.Admin);
		screens.put("Users", UserRole.Admin);
		screens.put("SellOrder", UserRole.Cashier);
		screens.put("ChangePassword", UserRole.Both);
	}

	public void login(String id, String password) throws LoginException {
		if (id.isBlank() || password.isBlank()) {
			throw new LoginException("Please Enter User Name and password");
		}

		HashMap<String, User> map = DataBasePOS.getUsers();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		this.currentUser = map.get(id);
	}

	public User getCurrentUser() {
		return this.currentUser;
	}

	public boolean hasPermission(String key) {
		if (currentUser == null) {
			return false;
		}
		return Optional.ofNullable(screens.get(key)).map(r -> currentUser.getRole().IsA(r)).orElse(false);
	}

	public void chaneUserPassword(String newPassword) {
		currentUser = new User(currentUser.getId(), newPassword, currentUser.getRole());
		DataBasePOS.getDataAccess().saveRecord(currentUser);
	}
}
