package festival.dto;

public class MemberDto {
	private int memberIdx;
	private String userId;
	private String password;
	private String name;
	
	public MemberDto(String userId, String password, String name) {
		this.userId = userId;
		this.password = password;
		this.name = name;
	}
	
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	
	
}
