package ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import business.Address;
import business.Author;
import business.AuthorDetails;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.MemberAddress;
import business.Settings;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddBookController implements Initializable {
	private SystemController controller = new SystemController();
	private ObservableList<AuthorDetails> observableList = FXCollections.observableArrayList();
	private List<Author> authors = new ArrayList<Author>();

	@FXML
	private TableView<AuthorDetails> table;

	@FXML
	private TextField ISBN;
	@FXML
	private TextField Title;
	@FXML
	private TextField MaxCheckout;

	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField telephone;
	@FXML
	private TextField bio;

	@FXML
	private Button submit;
	@FXML
	private Button addAuthor;

	@FXML
	private TextField streetField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField stateField;
	@FXML
	private TextField zipField;
	@FXML
	private TextField copieField;

	private Stage dialogStage;

	@FXML
	private TableColumn<MemberAddress, String> colFirstName;

	@FXML
	private TableColumn<MemberAddress, String> colLastName;

	@FXML
	private TableColumn<MemberAddress, String> colTelephone;

	@FXML
	private TableColumn<MemberAddress, String> colBIO;

	@FXML
	private TableColumn<MemberAddress, String> colStreet;

	@FXML
	private TableColumn<MemberAddress, String> colCity;

	@FXML
	private TableColumn<MemberAddress, String> colState;

	@FXML
	private TableColumn<MemberAddress, String> colZip;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void loadAuthors() {

		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colTelephone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		colStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
		colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
		colState.setCellValueFactory(new PropertyValueFactory<>("state"));
		colZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
		colBIO.setCellValueFactory(new PropertyValueFactory<>("bio"));

		observableList.setAll();
		for (Author author : authors) {
			observableList.add(new AuthorDetails(

					author.getFirstName(), author.getLastName(), "", author.getBio(), author.getAddress().getStreet(),
					author.getAddress().getCity(), author.getAddress().getState(), author.getAddress().getZip()));
		}

		table.setItems(observableList);

//		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//			if (newSelection != null) {
//				editButton.setDisable(false);
//				currentMemberClicked = newSelection;
//			}
//		});
	}

	@FXML
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void AddBookEvent() throws NumberFormatException, LibrarySystemException {

		try {

			if (ISBN.getText().equals("") || Title.getText().equals("")
					|| Integer.parseInt(MaxCheckout.getText()) == 0) {
				Settings.ShowMessage(AlertType.WARNING, "Please Complete Book Data");
				return;
			}

			if (authors.size() == 0) {
				Settings.ShowMessage(AlertType.WARNING, "Please Add Authors on Book");
				return;
			}

			controller.addBook(ISBN.getText(), Title.getText(), Integer.parseInt(MaxCheckout.getText()),
					Integer.parseInt(copieField.getText()), authors);
			Settings.ShowMessage(AlertType.INFORMATION, "Save Complete");
			dialogStage.hide();
		} catch (NumberFormatException ex) {
			Settings.ShowMessage(AlertType.ERROR, ex.getMessage());
		}

	}

	public void AddAuthorEvent() {
//		String _firstName = firstName.getText();
//		String _lastName = lastName.getText();
		if (streetField.getText().equals("") || cityField.getText().equals("") || stateField.getText().equals("")
				|| zipField.getText().equals("") || firstName.getText().equals("") || lastName.getText().equals("")
				|| telephone.getText().equals("") || bio.getText().equals("")) {
			Settings.ShowMessage(AlertType.WARNING, "Please Complete Author Data");
			return;
		}

		Address a = new Address(streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText());
		authors.add(new Author(firstName.getText(), lastName.getText(), telephone.getText(), a, bio.getText()));
		loadAuthors();

		streetField.setText("");
		cityField.setText("");
		stateField.setText("");
		zipField.setText("");

		firstName.setText("");
		lastName.setText("");
		telephone.setText("");
		bio.setText("");
	}

}
