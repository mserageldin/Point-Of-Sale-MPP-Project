package ui;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import business.AuthorDetails;
import business.BookDueDate;
import business.LibrarySystemException;
import business.MemberAddress;
import business.Settings;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BooKDueDateController {

	private SystemController controller = new SystemController();
	private Stage dialogStage;
	ObservableList<BookDueDate> observableList = FXCollections.observableArrayList();
	@FXML
	private TableView<BookDueDate> table;

	@FXML
	private TextField txtSearch;

	@FXML
	private TableColumn<List<String>, String> colBookTitle;
	@FXML
	private TableColumn<List<String>, String> colCopy;
	@FXML
	private TableColumn<List<String>, String> colMemberId;
	@FXML
	private TableColumn<List<String>, String> colDueDate;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	public void btnSearch_ClickEvent() {
		getDate(false);
	}

	void getDate(Boolean overdue) {

		try {
			if (txtSearch.getText().trim().equals("")) {
				Settings.ShowMessage(AlertType.WARNING, "Enter Text to Search");
				return;
			}
			observableList.setAll();
			for (List<String> list : controller.getBooKDueDate(txtSearch.getText())) {

				try {

					if (overdue) {

						LocalDate date1 = LocalDate.parse(list.get(3));
						LocalDate date2 = LocalDate.now();
						if (date1.isBefore(date2)) {
							observableList.add(new BookDueDate(list.get(0), list.get(1), list.get(2), list.get(3)));
						}

					} else {
						observableList.add(new BookDueDate(list.get(0), list.get(1), list.get(2), list.get(3)));

					}

				} catch (Exception e) {

				}

			}

			colBookTitle.setCellValueFactory(new PropertyValueFactory<>("BookTitle"));
			colCopy.setCellValueFactory(new PropertyValueFactory<>("Copy"));
			colMemberId.setCellValueFactory(new PropertyValueFactory<>("MemberId"));
			colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));

			table.setItems(observableList);

		} catch (LibrarySystemException e) {

			Settings.ShowMessage(AlertType.ERROR, e.getMessage());
		}

	}

	@FXML
	public void btnOverDue_ClickEvent() {
		getDate(true);
	}

	@FXML
	public void btnPrint_ClickEvent() {

	}

}
