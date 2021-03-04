package ui;

import java.net.URL;
import java.util.ResourceBundle;

import business.AppManager;
import business.User;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ChangePasswordController implements Initializable {

	@FXML
	private PasswordField crrentPass;
	@FXML
	private PasswordField newPass;
	@FXML
	private PasswordField confirmPass;

	@FXML
	private Button btnChange;

	@FXML
	private Button btnCancel;

	@FXML
	private Label lblError;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChangeListener<Boolean> resetError = (ov, oldV, newV) -> lblError.setVisible(false);
		crrentPass.focusedProperty().addListener(resetError);
		newPass.focusedProperty().addListener(resetError);
		confirmPass.focusedProperty().addListener(resetError);
	}

	public void changePassword(ActionEvent event) {
		User currentUser = AppManager.Instance.getCurrentUser();
		if (!crrentPass.getText().equals(currentUser.getPassword())) {
			lblError.setText("Invalid current password");
			lblError.setVisible(true);
			return;
		}

		if (newPass.getText().isBlank() || !newPass.getText().equals(confirmPass.getText())) {
			lblError.setText("Mismatch password");
			lblError.setVisible(true);
			return;
		}

		AppManager.Instance.chaneUserPassword(newPass.getText());
		Helper.ShowMessage(AlertType.CONFIRMATION, "Password Changed Successfully");
		cancelProcess(event);
	}

	public void cancelProcess(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

	public void fieldKeypressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			if (e.getSource().equals(crrentPass)) {
				newPass.requestFocus();
			} else if (e.getSource().equals(newPass)) {
				confirmPass.requestFocus();
			} else {
				changePassword(null);
			}
		}
	}
}
