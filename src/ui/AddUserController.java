package ui;

import java.net.URL;
import java.util.ResourceBundle;

import business.LibrarySystemException;
import business.Settings;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class AddUserController implements Initializable {
	SystemController controller = new SystemController();

	@FXML
	private TextField txtusername;

	@FXML
	private PasswordField txtpew;

	@FXML
	private ComboBox<String> combo;

	@FXML
	private Button btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		combo.getItems().addAll(Auth.ADMIN.toString(), Auth.LIBRARIAN.toString(), Auth.BOTH.toString());

	}

	public void save_ClickEvent(ActionEvent event) {
		if (txtusername.getText().equals("")) {
			Settings.ShowMessage(AlertType.WARNING, "Please Enter User Name ");
			return;
		}

		if (txtpew.getText().equals("")) {
			Settings.ShowMessage(AlertType.WARNING, "Please Enter password");
			return;
		}

		if (combo.getValue() == null) {
			Settings.ShowMessage(AlertType.WARNING, "Please choose role ");
			return;
		}

		try {

			Auth aa = Auth.ADMIN;
			if (combo.getValue() == Auth.LIBRARIAN.toString())
				aa = Auth.LIBRARIAN;
			if (combo.getValue() == Auth.BOTH.toString())
				aa = Auth.BOTH;

			controller.addUser(txtusername.getText(), txtpew.getText(), aa);

			Settings.ShowMessage(AlertType.INFORMATION, "Save Complete.");

		} catch (LibrarySystemException ex) {
			Settings.ShowMessage(AlertType.ERROR, ex.getMessage());
		}

	}
}
