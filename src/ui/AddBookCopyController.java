package ui;

import business.LibrarySystemException;
import business.Settings;
import business.SystemController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddBookCopyController {
	SystemController controller = new SystemController();
	
	@FXML
	private TextField tf;
	@FXML
	private TextField numberOfCopies;
	@FXML
	private Button btn;

	private Stage dialogStage;
	@FXML
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public void AddCopy() {
		String s1 = tf.getText();
		String s2 = numberOfCopies.getText();

		try {
			controller.addBookCopy(s1, Integer.parseInt(s2)); 

			Settings.ShowMessage(AlertType.INFORMATION, "Save Complete.");
			
			dialogStage.hide();

		} catch (LibrarySystemException ex) {
			Settings.ShowMessage(AlertType.ERROR, ex.getMessage());
		} 

	}

}
