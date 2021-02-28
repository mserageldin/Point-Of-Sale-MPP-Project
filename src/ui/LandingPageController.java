package ui;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import business.ControllerInterface;
import business.LibraryMember;
import business.Settings;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LandingPageController implements Initializable  {
	@FXML
	private Button addNewLibraryMember;
	@FXML
	private Button checkoutBook;
	@FXML
	private Button addBook;
	@FXML
	private Button addCopyOfAnExistingBook;
	@FXML
	private Label roleLabel;
	@FXML
	private Button editMembersButton;
	
	
	@FXML
	private Button bntBooKDueDate;
	@FXML
	private Button btnBookStatistics;
	@FXML
	private Button btnMembersStatistics; 
	
	
	
	@FXML
	private Button bntAddUsers;
	@FXML
	private Button btnUserChangepassword;
	@FXML
	private Button bntBookList; 
	
	@FXML
	private AnchorPane apn  ;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Image image = new Image("images/lib.jpg", 400, 300, false, false);
	//	apn.(image);
	    
	}
	
	public void addNewLibraryMember() {
 
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("CreateMember.fxml"));
			/*
			 * if "fx:controller" is not set in fxml
			 * fxmlLoader.setController(NewWindowController);
			 */
			Scene scene = new Scene(fxmlLoader.load());// , 600, 400);
			Stage stage = new Stage();
			stage.setTitle("Create Member");
			stage.setScene(scene);
			CreateMemberController m = (CreateMemberController) fxmlLoader.getController();
			m.setDialogStage(stage);
			stage.show();
		} catch (Exception ex) {
		Settings.ShowMessage(AlertType.ERROR, ex.getMessage());

		}
		
		
	}

	
	public void addNewUser() {

		Settings.stageShow(getClass().getResource("AddUser.fxml"), "Add User");
	}
	
 	public void btnMemberBooks() {

		Settings.stageShow(getClass().getResource("MemberBooks.fxml"), "Member Books");
	}
	
	public void checkoutBook() {

		Settings.stageShow(getClass().getResource("Checkout.fxml"), "Checkout a Book");
	}

	public void addBook() {

//		Settings.stageShow(getClass().getResource("AddBook.fxml"), "Add Book");
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("AddBook.fxml"));
			/*
			 * if "fx:controller" is not set in fxml
			 * fxmlLoader.setController(NewWindowController);
			 */
			Scene scene = new Scene(fxmlLoader.load());// , 600, 400);
			Stage stage = new Stage();
			stage.setTitle("Add Book");
			stage.setScene(scene);
			AddBookController m = (AddBookController) fxmlLoader.getController();
			m.setDialogStage(stage);
			stage.show();
		} catch (Exception ex) {
		Settings.ShowMessage(AlertType.ERROR, ex.getMessage());

		}	
		
	}

	public void addAuthor() {

		Settings.stageShow(getClass().getResource("CreateAuthor.fxml"), "Add Author");
	}

	public void addCopyOfAnExistingBook() {

		Settings.stageShow(getClass().getResource("AddBookCopy.fxml"), "Add Book Copies");
	}

	public void GetRole() {
		roleLabel.setText(SystemController.currentAuth.toString());
		if (SystemController.currentAuth == Auth.ADMIN) {
			checkoutBook.setVisible(false);
			bntBooKDueDate.setVisible(false);
			roleLabel.setText(SystemController.currentAuth.toString());
			
			btnBookStatistics.setVisible(false);
			
			
		} else if (SystemController.currentAuth == Auth.LIBRARIAN) {
			addNewLibraryMember.setVisible(false);
			addBook.setVisible(false);
			addCopyOfAnExistingBook.setVisible(false);
			editMembersButton.setVisible(false);
			
			bntAddUsers.setVisible(false);
			btnUserChangepassword.setVisible(false);
			bntBookList.setVisible(false); 
			btnMembersStatistics.setVisible(false);
			roleLabel.setText(SystemController.currentAuth.toString());
		}	
		else {
			roleLabel.setText(Auth.ADMIN + " & " + Auth.LIBRARIAN);
		}
//		 else if (SystemController.currentAuth == Auth.BOTH) {
//			roleLabel.setText(Auth.ADMIN + " " + Auth.LIBRARIAN);
//			checkoutBook.setVisible(true);
//			addNewLibraryMember.setVisible(true);
//			addBook.setVisible(true);
//			addCopyOfAnExistingBook.setVisible(true);
//			editMembersButton.setVisible(true);
//		}
	}

	public void editMembers() {

		Settings.stageShow(getClass().getResource("AllMembersDetails.fxml"), "All Members Details");
	}

	public void BookListEvent(ActionEvent e) {

		if (!AllBooksWindow.INSTANCE.isInitialized()) {
			AllBooksWindow.INSTANCE.init();
		}
		ControllerInterface ci = new SystemController();
		List<String> ids = ci.allBookIds();
		Collections.sort(ids);
		StringBuilder sb = new StringBuilder();
		for (String s : ids) {
			sb.append(s + "\n");
		}
		AllBooksWindow.INSTANCE.setData(sb.toString());
		AllBooksWindow.INSTANCE.show();
	}
	
	
	
	
	
	public void BookDueDate_ClickEvent() { 
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("BooKDueDate.fxml"));
		
			Scene scene = new Scene(fxmlLoader.load());// , 600, 400);
			Stage stage = new Stage();
			stage.setTitle("BooK Due Date");
			stage.setScene(scene);
			BooKDueDateController m = (BooKDueDateController) fxmlLoader.getController();
			m.setDialogStage(stage);
			stage.show();
		} catch (Exception ex) {
		Settings.ShowMessage(AlertType.ERROR, ex.getMessage());

		}	
		
	}
	
	

}
