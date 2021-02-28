package business;

public class MemberAddress {
	private String memberId;
	private String fName;
	private String lName;
	private String tel;
	private String street;
	private String city;
	private String state;
	private String  zip;
	
	public MemberAddress(String memberId, String fName, String lName, String tel, String street, String city, String state, String zip) {
		this.memberId = memberId;
		this.fName = fName;
		this.lName = lName;
		this.tel = tel;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getFName() {
		return fName;
	}

	public String getLName() {
		return lName;
	}

	public String getTel() {
		return tel;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "MemberAddress [memberId=" + memberId + ", fName=" + fName + ", lName=" + lName + ", tel=" + tel
				+ ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}
	
	
	
	
}
