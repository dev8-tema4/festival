package festival.dto;

public class MemberDto {
	private int memberId;
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;

	public MemberDto(String email, String password, String name,String address,String phone){
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
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
	
	

	
}
