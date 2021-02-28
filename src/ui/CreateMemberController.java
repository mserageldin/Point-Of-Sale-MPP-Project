package ui;

import javafx.scene.control.Button;

import business.Address;
import business.LibraryMember;
import business.LibrarySystemException;
import business.Settings;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateMemberController {

	@FXML
	private TextField memberIdField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField stateField;
	@FXML
	private TextField zipField;
	@FXML
	private TextField phoneField;
	SystemController controler = new SystemController();
	@FXML
	private Button btnCancel;

	private Stage dialogStage;
	private LibraryMember libraryMember;
	private boolean submitClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// dialogStage=dialogStage;
	}

	/**
	 * Sets the stage of this dialog.
	 *
	 * @param dialogStage
	 */
	@FXML
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the library member to be edited in the dialog.
	 *
	 * @param person
	 */
	public void setLibraryMember(LibraryMember libraryMember) {
		this.libraryMember = libraryMember;
		Address address = this.libraryMember.getAddress();
		firstNameField.setText(libraryMember.getFirstName());
		lastNameField.setText(libraryMember.getLastName());
		streetField.setText(address.getStreet());
		cityField.setText(address.getCity());
		stateField.setText(address.getState());
		zipField.setText(address.getZip());
		phoneField.setText(libraryMember.getTelephone());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isSubmitClicked() {
		return submitClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleSubmit() {
		if (isInputValid()) {
			try {
				controler.addMember(memberIdField.getText(), firstNameField.getText(), lastNameField.getText(),
						streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText(),
						phoneField.getText());

				//DataAccess da = new DataAccessFacade();
				//System.out.println(da.readMemberMap());
				
				Settings.ShowMessage(AlertType.INFORMATION, "Save Complete.");

			} catch (LibrarySystemException ex) {
				Settings.ShowMessage(AlertType.ERROR, "New Memeber Error");
			}
//			libraryMember.setFirstName(firstNameField.getText());
//			libraryMember.setLastName(lastNameField.getText());
//			libraryMember.setAddress(
//					new Address(streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText()));
//			libraryMember.setTelephone(phoneField.getText());

			submitClicked = true;
			dialogStage.close();
		}

	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();

	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		if (memberIdField.getText() == null || memberIdField.getText().length() == 0) {
			errorMessage += "No valid member id!\n";
		}
		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}
		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}
		if (stateField.getText() == null || stateField.getText().length() == 0) {
			errorMessage += "No valid state!\n";
		}
		if (zipField.getText() == null || zipField.getText().length() == 0) {
			errorMessage += "No valid zip!\n";
		}
		if (phoneField.getText() == null || phoneField.getText().length() == 0) {
			errorMessage += "No valid phone!\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
