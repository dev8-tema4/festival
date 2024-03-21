package festival.dto;



public class MyPageDto {
	private int memberId;
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;

	public MyPageDto(int memberId, String email, String password, String name, String address, String phone) {
		this.memberId = memberId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;

	}

	public MyPageDto() {
		// TODO Auto-generated constructor stub
	}

	public int getMemberId() {
		return memberId;
	}
	
	public void setMemberId(int memberIdx) {
		this.memberId = memberIdx;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "MyPageDto [memberId=" + memberId + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", address=" + address + ", phone=" + phone + "]";
	}
	
	

}
