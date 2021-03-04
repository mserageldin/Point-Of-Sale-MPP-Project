package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private static Stage primStage = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Start.primStage = primaryStage;
		Helper.showStage(getClass().getResource("Login.fxml"), "Login", Start.primStage);
	}

	
	public static Stage getPrimaryStage() {
		return Start.primStage;
	}
}
