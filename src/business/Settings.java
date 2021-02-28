package business;

import java.net.URL;

import dataaccess.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Settings {
	public static User user;

	public static void ShowMessage(AlertType atype, String message) {
		Alert alert = new Alert(atype);
// alert.setTitle("Confirmation");

		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void stageShow(URL loacion, String title) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(loacion);
			/*
			 * if "fx:controller" is not set in fxml
			 * fxmlLoader.setController(NewWindowController);
			 */
			Scene scene = new Scene(fxmlLoader.load());// , 600, 400);
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(scene);
			
			
			stage.show();
		} catch (Exception ex) {
			ShowMessage(AlertType.ERROR, ex.getMessage());

		}
	}
	
	 
	
}
