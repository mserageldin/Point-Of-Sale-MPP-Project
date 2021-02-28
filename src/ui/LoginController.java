package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.LoginException;
import business.Settings;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	private SystemController controller = new SystemController();
	private Stage dialogStage;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPWD;

	@FXML
	private Button butLogin;

	@FXML
	private ImageView imagev;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		Image image = new Image("images/user.jpg", 400, 300, false, false);
		imagev.setImage(image);

	}

	@FXML
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void Login_ClickEvent(ActionEvent event) {
		if (txtUser.getText() == "" || txtPWD.getText() == "") {
			Settings.ShowMessage(AlertType.WARNING, "Please salmli 3ala 7amada");
			return;
		}

		try {
			ControllerInterface c = new SystemController();
			c.login(txtUser.getText().trim(), txtPWD.getText().trim());
//			messageBar.setFill(Start.Colors.green);
//			messageBar.setText("Login successful");

			try {
				dialogStage.close();
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("LandingPage.fxml"));

				Scene scene = new Scene(fxmlLoader.load());// , 600, 400);
				Stage stage = new Stage();
				stage.setTitle("Landing Page");
				stage.setScene(scene);
				// INSTANCE.hide();

				LandingPageController landing = (LandingPageController) fxmlLoader.getController();
				landing.GetRole();
				stage.show();
				

			} catch (IOException ex) {
				Settings.ShowMessage(AlertType.ERROR, "Error! " + ex.getMessage());
			}
		} catch (LoginException ex) {

			Settings.ShowMessage(AlertType.ERROR, "Error! " + ex.getMessage());
		}

	}

}
