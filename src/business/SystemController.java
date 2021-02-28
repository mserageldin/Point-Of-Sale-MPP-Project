package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import dataaccess.DataAccessFacade.StorageType;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("7amada not " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();

	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<LibraryMember> allMembers() {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().values());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public List<Book> allBooks() {
		DataAccess da = new DataAccessFacade();
		List<Book> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().values());
		return retval;
	}

	@Override
	public List<String> allUsersName() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readUserMap().keySet());
		return retval;
	}

	@Override
	public List<User> allUsers() {
		DataAccess da = new DataAccessFacade();
		List<User> retval = new ArrayList<>();
		retval.addAll(da.readUserMap().values());
		return retval;
	}

	/**
	 * Add new library member. If the operation because of duplicate library ID,
	 * LibrarySystemException will be raised
	 */
	@Override
	public void addMember(String memberId, String firstName, String lastName, String street, String city, String state,
			String zip, String telephone) throws LibrarySystemException {

		// Verifies if ID already exists
		if (this.allMemberIds().contains(memberId)) {
			throw new LibrarySystemException(" Member with id: " + memberId + " already exists in the system");
		}
		DataAccess da = new DataAccessFacade();
		// add new member
		Address address = new Address(street, city, state, zip);
		da.saveNewMember(new LibraryMember(memberId, firstName, lastName, telephone, address));
	}

	@Override
	public void addAuthor(String firstName, String lastName, String street, String city, String state, String zip,
			String telephone, String Bio) throws LibrarySystemException {

		DataAccess da = new DataAccessFacade();
		// add new member
		Address address = new Address(street, city, state, zip);
		da.saveNewAuthor(new Author(firstName, lastName, telephone, address, Bio));
	}

	@Override
	public void updateMember(String memberId, String firstName, String lastName, String street, String city,
			String state, String zip, String telephone) throws LibrarySystemException {

		DataAccess da = new DataAccessFacade();
		// add new member
		Address address = new Address(street, city, state, zip);
		da.saveNewMember(new LibraryMember(memberId, firstName, lastName, telephone, address));
	}

	/**
	 * Add copies of a book
	 */
	@Override
	public void addBookCopy(String ISBN, int numberOfCopies) throws LibrarySystemException {
		// check is books exists
		if (!this.allBookIds().contains(ISBN)) {
			throw new LibrarySystemException(" Book with ISBN: " + ISBN + " does not exists");
		}
		// get book
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> booksHash = da.readBooksMap();
		Book book = booksHash.get(ISBN);
		// add requested numbers of copies
		while (numberOfCopies-- > 0) {
			book.addCopy();
		}
		da.storeBook(book); // persist
	}

	/**
	 * Checkout a book for a member
	 */
	public LibraryMember checkOutBook(String memberID, String ISBN) throws LibrarySystemException {
		if (!this.allMemberIds().contains(memberID)) {
			throw new LibrarySystemException(" Member with id: " + memberID + " do not exist");
		}
		if (!this.allBookIds().contains(ISBN)) {
			throw new LibrarySystemException(" Book with ISBN: " + ISBN + " do not exist");
		}
		DataAccess da = new DataAccessFacade();
		// get book
		HashMap<String, Book> booksHash = da.readBooksMap();
		Book book = booksHash.get(ISBN);
		// check if there is a copy available
		if (!book.isAvailable()) {
			// there is no copy available
			throw new LibrarySystemException(" Book " + book.getTitle() + " have no available copy");
		}
		BookCopy bookForRent = book.getNextAvailableCopy();
		// get member
		HashMap<String, LibraryMember> memberHash = da.readMemberMap();
		LibraryMember member = memberHash.get(memberID);
		member.checkout(bookForRent); // checkout the book
		bookForRent.changeAvailability(); // change its availability
		// write to storage both books and members
		da.storeBook(book);
		da.saveMemberInfo(member);
		return member;
	}

	/**
	 * Add a new book to the system. might throw LibrarySystemException if book with
	 * isbn exists
	 */
	@Override
	public void addBook(String isbn, String title, int maxCheckoutLength, int numCopies, List<Author> authors)
			throws LibrarySystemException {
		if (this.allBookIds().contains(isbn)) {
			throw new LibrarySystemException(" Book With ISBN " + isbn + " Already Exists");
		}
		Book book = new Book(isbn, title, maxCheckoutLength, authors);
		while (--numCopies > 0) { // because the constructor added one copy by default
			book.addCopy();
		}
		// persist data
		DataAccess da = new DataAccessFacade();
		da.storeBook(book);
	}

	@Override
	public void addUser(String id, String pass, Auth auth) throws LibrarySystemException {

		DataAccess da = new DataAccessFacade();
		@SuppressWarnings("serial")
		List<User> allUsers = allUsers();

		if (this.allUsersName().contains(id)) {
			throw new LibrarySystemException(" User With User Name " + id + " Already Exists");
		}
		User user = new User(id, pass, auth);

		allUsers.add(user);

		DataAccessFacade.loadUserMap(allUsers);
	}

	@Override
	public List<Author> allAuthors() {
		DataAccess da = new DataAccessFacade();
		List<Author> retval = new ArrayList<>();
		retval.addAll(da.readAuthorMap().values());
		return retval;
	}

	/**
	 * Gets a checkout history for a member. Returns the List<CheckoutRecordEntries>
	 * for a member.
	 */
	@Override
	public List<CheckoutRecordEntry> getLibraryMemberCheckoutRecord(String memberID) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> membersMap = da.readMemberMap();
		if (membersMap.isEmpty()) {
			throw new LibrarySystemException("No Members Registered yet");
		}
		if (!membersMap.containsKey(memberID)) {
			throw new LibrarySystemException("Member With ID: " + memberID + " Doesn't Exist");
		}
		LibraryMember member = membersMap.get(memberID);
		return member.getCheckoutRecord().getCheckoutRecordEntries();
	}

	/**
	 * Get history of a book [and it's book copies] Returns List <List<Strings>>.
	 * The inner list contains <title, copy number, memberID, dueDate>.
	 */
	public List<List<String>> getBooKDueDate(String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> books = da.readBooksMap();
		if (books.isEmpty() || !books.containsKey(isbn)) {
			throw new LibrarySystemException("No Book found");
		}
		List<List<String>> results = new ArrayList<List<String>>();
		Book book = books.get(isbn);
		HashMap<String, LibraryMember> members = da.readMemberMap();
		for (BookCopy bookcopy : book.getCopies()) {
			String memberID = "";
			String dueDate = "";
			if (!bookcopy.isAvailable()) {
				for (LibraryMember member : members.values()) {
					boolean status = false; // book copy found or not
					List<CheckoutRecordEntry> entries = member.getCheckoutRecord().getCheckoutRecordEntries();
					for (CheckoutRecordEntry entry : entries) {
						if (entry.getBookCopy().equals(bookcopy)) {
							memberID = member.getMemberId();
							dueDate = entry.getReturnDueDate().toString();
							status = true;
							break;
						}
					}
					if (status)
						break;
				}
			}
			List<String> templist = new ArrayList<String>();
			templist.add(bookcopy.getBook().getTitle());
			templist.add(Integer.toString(bookcopy.getCopyNum()));
			templist.add(memberID);
			templist.add(dueDate);
			results.add(templist);
		}

		return results;
	}

//	public List<BookCheckoutTable> getMemberBooks(String memberID)   {
//		DataAccess da = new DataAccessFacade();
//		HashMap<String, Book> books = da.readBooksMap();
//
//		List<BookCheckoutTable> results = new ArrayList<BookCheckoutTable>();
//		HashMap<String, LibraryMember> members = da.readMemberMap();
//
//		for (Map.Entry me : books.entrySet()) {
//			Book book = (Book) me.getValue();
//
//			for (BookCopy bookcopy : book.getCopies()) {
//
//				String dueDate = "";
//				if (!bookcopy.isAvailable()) {
//					for (LibraryMember member : members.values()) {
//						boolean status = false; // book copy found or not
//						List<CheckoutRecordEntry> entries = member.getCheckoutRecord().getCheckoutRecordEntries();
//						for (CheckoutRecordEntry entry : entries) {
//							if (entry.getBookCopy().equals(bookcopy)) {
//								memberID = member.getMemberId();
//								dueDate = entry.getReturnDueDate().toString();
//								status = true;
//								break;
//							}
//						}
//						if (status)
//							break;
//					}
//				}
//
////				List<String> templist = new ArrayList<String>();
////				templist.add(bookcopy.getBook().getTitle());
////				templist.add(Integer.toString(bookcopy.getCopyNum()));
////				templist.add(memberID);
////				templist.add(dueDate);
//				results.add(new BookCheckoutTable(bookcopy.getBook().getIsbn(), bookcopy.getBook().getTitle(),
//						bookcopy.getCopyNum() + "", "checkoutDate", dueDate));
//
//			} 
//		}
//		return results;
//
//	}

	public List<BookCheckoutTable> getMemberBooks(String memberID) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
//		HashMap<String, Book> books = da.readBooksMap();
//
		List<BookCheckoutTable> results = new ArrayList<BookCheckoutTable>();
		HashMap<String, LibraryMember> members = da.readMemberMap();
//
//		for (Map.Entry me : books.entrySet()) {
//			Book book = (Book) me.getValue();
//
//			for (BookCopy bookcopy : book.getCopies()) {
//
		String dueDate = "";
//				if (!bookcopy.isAvailable()) {
		LibraryMember member = members.get(memberID);
		if(member==null) {
			throw new LibrarySystemException("No Member found");
			
			
			
		}
//						boolean status = false; // book copy found or not
		List<CheckoutRecordEntry> entries = member.getCheckoutRecord().getCheckoutRecordEntries();
		for (CheckoutRecordEntry entry : entries) {
//							if (entry.getBookCopy().equals(bookcopy)) {
			memberID = member.getMemberId();
			dueDate = entry.getReturnDueDate().toString();
//								status = true;
		 
//							}
			String s21 = entry.getBookCopy().getBook().getIsbn();
			String s2 = entry.getBookCopy().getBook().getTitle();
			String s3 = entry.getBookCopy().getCopyNum() + "";
			String s4 = entry.getCheckoutDate().toString();
			BookCheckoutTable x = new BookCheckoutTable(s21, s2, s3, s4, dueDate);

			results.add(x);
		}
//						if (status)
//							break; 
//				}

//				results.add(new BookCheckoutTable(bookcopy.getBook().getIsbn(), bookcopy.getBook().getTitle(),
//						bookcopy.getCopyNum() + "", "checkoutDate", dueDate));

//			} 
//		}
		return results;

	}

}
