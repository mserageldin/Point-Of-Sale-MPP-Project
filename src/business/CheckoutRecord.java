package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6166156083208597135L;

	private List<CheckoutRecordEntry> checkoutRecordEntries;
	private BookCopy bookCopy;

	public CheckoutRecord() {
		checkoutRecordEntries = new ArrayList<>();
	}

	/**
	 * Add a checkout for a book copy
	 * 
	 * @param bookCopy an available book copy
	 */
	public void checkout(BookCopy bookCopy) {

		this.bookCopy = bookCopy;

		checkoutRecordEntries.add(new CheckoutRecordEntry(bookCopy));
	}

	public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
		return Collections.unmodifiableList(checkoutRecordEntries);
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

}
