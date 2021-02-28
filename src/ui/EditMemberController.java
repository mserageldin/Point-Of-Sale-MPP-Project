package ui;

import java.util.ArrayList;
import java.util.List;

import business.Address;
import business.LibraryMember;
import business.LibrarySystemException;
import business.MemberAddress;
import business.Settings;
import business.SystemController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditMemberController {
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

	SystemController controller = new SystemController();

	List<LibraryMember> listOfMembers;
	
	@FXML
	private Button btnCancel;

	private Stage dialogStage;
	private MemberAddress libraryMember;
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
	public void setLibraryMember(MemberAddress libraryMember) {
		this.libraryMember = libraryMember;
		memberIdField.setText(libraryMember.getMemberId());
		firstNameField.setText(libraryMember.getFName());
		lastNameField.setText(libraryMember.getLName());
		streetField.setText(libraryMember.getStreet());
		cityField.setText(libraryMember.getCity());
		stateField.setText(libraryMember.getState());
		zipField.setText(libraryMember.getZip());
		phoneField.setText(libraryMember.getTel());
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
				Address oldAddress = new Address(libraryMember.getStreet(), libraryMember.getCity(),
						libraryMember.getState(), libraryMember.getZip());
				LibraryMember oldLibraryMember = new LibraryMember(libraryMember.getMemberId(),
						libraryMember.getFName(), libraryMember.getLName(), libraryMember.getTel(), oldAddress);
				listOfMembers = new ArrayList<LibraryMember>(controller.allMembers());
				int indexOfEditableMember = findIndex(listOfMembers, oldLibraryMember);
				Address newAddress = new Address(streetField.getText(), cityField.getText(), stateField.getText(),
						zipField.getText());

				LibraryMember newLibraryMember = new LibraryMember(memberIdField.getText(), firstNameField.getText(),
						lastNameField.getText(), phoneField.getText(), newAddress);

				listOfMembers.set(indexOfEditableMember, newLibraryMember);
				controller.updateMember(
						newLibraryMember.getMemberId(), 
						newLibraryMember.getFirstName(),
						newLibraryMember.getLastName(), 
						newLibraryMember.getAddress().getStreet(),
						newLibraryMember.getAddress().getCity(), 
						newLibraryMember.getAddress().getState(),
						newLibraryMember.getAddress().getZip(), 
						newLibraryMember.getTelephone());

				Settings.ShowMessage(AlertType.INFORMATION, "Save Complete.");

			} catch (LibrarySystemException ex) {
				Settings.ShowMessage(AlertType.ERROR, "New Memeber Error");
			}
			submitClicked = true;
			dialogStage.close();
			
		}

	}
	
	public int findIndex(List<LibraryMember> listOfMembers, LibraryMember oldLibraryMember) {
		int indexOfEditableMember = -1;
		int count = -1;
		for (LibraryMember m : listOfMembers) {
			count++;
			if (m.getMemberId().equals(oldLibraryMember.getMemberId())) {
				indexOfEditableMember = count;
				break;
			}
			
		}
		
		return indexOfEditableMember;
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
