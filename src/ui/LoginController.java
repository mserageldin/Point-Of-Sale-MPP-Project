package ui;

import java.net.URL;
import java.util.ResourceBundle;

import business.AppManager;
import business.LoginException;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
// import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController implements Initializable {

	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPWD;

	@FXML
	private Button butLogin;

	@FXML
	private ImageView imagev;

	@FXML
	private Label lblError;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image = new Image("images/login1.png", 400, 300, false, false);
		imagev.setImage(image);
		ChangeListener<Boolean> resetError = (ov, oldV, newV) -> lblError.setVisible(false);
		txtPWD.focusedProperty().addListener(resetError);
		txtUser.focusedProperty().addListener(resetError);
	}

	public void Login_ClickEvent(ActionEvent event) {
		try {
			AppManager.Instance.login(txtUser.getText(), txtPWD.getText());
			Helper.showStage(getClass().getResource("MainWindow.fxml"), "Point Of Sale System",
					Start.getPrimaryStage());
		} catch (LoginException ex) {
			// Helper.ShowMessage(AlertType.ERROR, "Error! " + ex.getMessage());
			lblError.setText("Error! " + ex.getMessage());
			lblError.setVisible(true);
		}
	}

	public void Login_Keypressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			if (e.getSource().equals(txtUser)) {
				txtPWD.requestFocus();
			} else {
				Login_ClickEvent(null);
			}
		}
	}
}
