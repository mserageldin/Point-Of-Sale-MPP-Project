package business;

import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.User;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;

	public List<String> allMemberIds();

	public void addMember(String memberId, String firstName, String lastName, String street, String city, String state,
			String zip, String telephone) throws LibrarySystemException;

	public LibraryMember checkOutBook(String memberID, String ISBN) throws LibrarySystemException;

	public List<LibraryMember> allMembers();

	public List<String> allBookIds();

	public void addBookCopy(String ISBN, int numberOfCopies) throws LibrarySystemException;

	public List<Book> allBooks();

	public void addBook(String ISBN, String title, int maxCheckoutLength, int numCopies, List<Author> authors)
			throws LibrarySystemException;

	public List<CheckoutRecordEntry> getLibraryMemberCheckoutRecord(String memberID) throws LibrarySystemException;

	public void updateMember(String memberId, String firstName, String lastName, String street, String city,
			String state, String zip, String telephone) throws LibrarySystemException;

	public List<Author> allAuthors();

	public void addAuthor(String firstName, String lastName, String street, String city, String state, String zip,
			String telephonem, String Bio) throws LibrarySystemException;

	public void addUser(String id, String pass, Auth auth) throws LibrarySystemException;

	public List<String> allUsersName();

	public	List<User> allUsers();
 

}
