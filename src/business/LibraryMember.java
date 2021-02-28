package business;

import java.io.Serializable;


final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord checkoutRecord;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;	
		checkoutRecord = new CheckoutRecord();
	}
	
	
	public String getMemberId() {
		return memberId;
	}
	
	/**
	 * Add book checkout to history of the member
	 * @param bookCopy
	 */
	public void checkout (BookCopy bookCopy) {
		if(checkoutRecord == null) {
			checkoutRecord = new CheckoutRecord();
		}
		checkoutRecord.checkout(bookCopy);
	}

	public CheckoutRecord getCheckoutRecord () {
		return checkoutRecord;
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
