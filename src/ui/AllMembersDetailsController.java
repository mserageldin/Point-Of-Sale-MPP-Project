package ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.ControllerInterface;
import business.LibraryMember;
import business.MemberAddress;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AllMembersDetailsController implements Initializable{
	@FXML
	private TableView<MemberAddress> table;
	
	@FXML
	private TableColumn<MemberAddress, String> colIdMember; 
	
	@FXML
	private TableColumn<MemberAddress, String> colFirstName; 
	
	@FXML
	private TableColumn<MemberAddress, String> colLastName; 
	
	@FXML
	private TableColumn<MemberAddress, String> colTelephone; 
	
	@FXML
	private TableColumn<MemberAddress, String> colStreet; 
	
	@FXML
	private TableColumn<MemberAddress, String> colCity; 
	
	@FXML
	private TableColumn<MemberAddress, String> colState; 
	
	@FXML
	private TableColumn<MemberAddress, String> colZip; 
	
	@FXML
	private Button editButton;
	
	@FXML
	private Button refreshButton;
	
	private MemberAddress currentMemberClicked;
	
	ObservableList<MemberAddress> observableList;
	
	List<LibraryMember> members ;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		load();
	}
	
	public void load(){
		observableList = FXCollections.observableArrayList();
		editButton.setDisable(true);
		ControllerInterface ci = new SystemController();
		members = ci.allMembers();
		observableList.setAll();
		for(LibraryMember member : members) {
			observableList.add(new MemberAddress(
					member.getMemberId(), 
					member.getFirstName(), 
					member.getLastName(), 
					member.getTelephone(), 
					member.getAddress().getStreet(), 
					member.getAddress().getCity(), 
					member.getAddress().getState(), 
					member.getAddress().getZip())
					);
		}
		
		colIdMember.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("fName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lName"));
		colTelephone.setCellValueFactory(new PropertyValueFactory<>("tel"));
		colStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
		colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
		colState.setCellValueFactory(new PropertyValueFactory<>("state"));
		colZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
		
		table.setItems(observableList);
		
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	editButton.setDisable(false);
		    	currentMemberClicked = newSelection;
		    }
		});
	}
	
	public void editButton() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("EditMember.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = new Stage();
			stage.setTitle("Edit member");
			stage.setScene(scene);
			stage.show();
			EditMemberController editMemberController = (EditMemberController) fxmlLoader.getController();
			editMemberController.setLibraryMember(currentMemberClicked);
			editMemberController.setDialogStage(stage);
			
		} catch (Exception ex) { }
	}

	public void refreshButton() {
		load();
	}
}
