package ui;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BookCheckoutTable;
import business.BookDueDate;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckoutController {
	SystemController controller = new SystemController();
	@FXML
	private TextField memberIdField;
	@FXML
	private TextField isbnField;
	@FXML
	private Label responseMessage;
	@FXML
	private TableView<BookCheckoutTable> table;

	ObservableList<BookCheckoutTable> observableList = FXCollections.observableArrayList();

	@FXML
	private TableColumn<List<String>, String> colISBN;
	@FXML
	private TableColumn<List<String>, String> colBookTitle;
	@FXML
	private TableColumn<List<String>, String> colCopyNo;
	@FXML
	private TableColumn<List<String>, String> colCheckoutDate;
	@FXML
	private TableColumn<List<String>, String> colDueDate;
	LibraryMember libraryMember;

	
	
	List<BookCheckoutTable> getMemberBooks ;
	
	String strName ="";
	
	@FXML
	public void Search() {
		try {
			libraryMember = controller.checkOutBook(memberIdField.getText(), isbnField.getText());
			// System.out.println("****************");
			strName=libraryMember.getFirstName() + " "
					+ libraryMember.getLastName();
			responseMessage.setText("Check Out completed Successfully to " +strName);

			colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
			colBookTitle.setCellValueFactory(new PropertyValueFactory<>("BookTitle"));
			colCopyNo.setCellValueFactory(new PropertyValueFactory<>("Copy"));
			colCheckoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
			colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));

			DataAccess da = new DataAccessFacade();
			HashMap<String, Book> booksHash = da.readBooksMap();
			Book book = booksHash.get(isbnField.getText());

			observableList.setAll();

			CheckoutRecordEntry ch = new CheckoutRecordEntry(libraryMember.getCheckoutRecord().getBookCopy());
			int xx = libraryMember.getCheckoutRecord().getBookCopy().getCopyNum();
			observableList.add(new BookCheckoutTable(book.getIsbn(), book.getTitle(), xx + "",
					LocalDate.now().toString(), ch.getReturnDueDate().toString()));
			table.setItems(observableList);


			
			// getMemberBooks = controller.getMemberBooks(memberIdField.getText());
			// observableList.addAll(getMemberBooks);
				table.setItems(observableList); 
					 
					 
			isbnField.setText("");
			memberIdField.setText("");
			// System.out.println(libraryMember.getCheckoutRecord().getCheckoutRecordEntries());

		} catch (

		LibrarySystemException e) {
			// TODO Auto-generated catch block
			responseMessage.setText(e.getMessage());
		}
	}

	@FXML
	public void Print() {

	

		System.out.println("***********************************************************");
		System.out.println(" Member Name : "+ strName);
		System.out.println("------------------------------------------------------------");

		for (BookCheckoutTable bookCheckoutTable : observableList) {
			System.out.println(bookCheckoutTable.toString());
		}
		System.out.println("***********************************************************");

	}
}
