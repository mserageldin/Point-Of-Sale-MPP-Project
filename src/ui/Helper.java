package ui;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Helper {

	public static void ShowMessage(AlertType atype, String message) {
		Alert alert = new Alert(atype);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void showStage(URL location, String title) {

		showStage(location, title, null);
	}

	public static void showStage(URL location, String title, Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(location);
			Scene scene = new Scene(fxmlLoader.load());// , 600, 400);
			if (stage == null) {
				stage = new Stage();
			}
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
		} catch (Exception ex) {
			ShowMessage(AlertType.ERROR, ex.getMessage());

		}
	}
}
