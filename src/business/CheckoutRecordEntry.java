package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1984639720831608973L;
	private LocalDate checkoutDate;
	private LocalDate returnDueDate;
	private BookCopy bookCopy;

	
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	
	public CheckoutRecordEntry (BookCopy bookCopy) {
		this.bookCopy = bookCopy;
		this.checkoutDate = LocalDate.now();	// set to now
		calculateReturnDate();	// set returning date
	}
	
	/**
	 * Method to calculate the return date for the book
	 */
	private void calculateReturnDate () {
		int lendingInterval = bookCopy.getMaximumCheckoutLength();
		returnDueDate = LocalDate.now().plusDays(lendingInterval);
	}
	
	@Override
	public String toString () {
		return "copy:" + bookCopy.getBook().getTitle() + "\n CheckoutDate: " + checkoutDate;
	}
	
	public BookCopy getBookCopy () {
		return bookCopy;
	}
	
	public LocalDate getReturnDueDate() {
		return returnDueDate;
	}
}
