package business;

public class BookDueDate {
	String BookTitle;
	String Copy;
	String MemberId;
	String DueDate;

	public BookDueDate(String bookTitle, String copy, String memberId, String dueDate) {
	 
		BookTitle = bookTitle;
		Copy = copy;
		MemberId = memberId;
		DueDate = dueDate;
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

	public String getMemberId() {
		return MemberId;
	}

	public void setMemberId(String memberId) {
		MemberId = memberId;
	}

	public String getDueDate() {
		return DueDate;
	}

	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}

}
