package business;

public class BookCheckoutTable {
	


	private String ISBN;
	private String BookTitle;
	private String Copy;
	private String checkoutDate;
	private String DueDate;
	
	public BookCheckoutTable(String iSBN, String bookTitle, String copy, String checkoutDate, String dueDate) {
		super();
		ISBN = iSBN;
		BookTitle = bookTitle;
		Copy = copy;
		this.checkoutDate = checkoutDate;
		DueDate = dueDate;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getBookTitle() {
		return BookTitle;
	}

	public void setBookTitle(String bookTitle) {
		BookTitle = bookTitle;
	}

	public String getCopy() {
		return Copy;
	}

	public void setCopy(String copy) {
		Copy = copy;
	}

 

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getDueDate() {
		return DueDate;
	}

	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}


	
	
	@Override
	public String toString() {
		return "BookCheckoutTable [ISBN=" + ISBN + ", BookTitle=" + BookTitle + ", Copy=" + Copy + ", checkoutDate="
				+ checkoutDate + ", DueDate=" + DueDate + "]";
	}

}
