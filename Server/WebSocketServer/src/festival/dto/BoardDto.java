package festival.dto;

public class BoardDto {
	

	private int indexNum;
	private String subject;
	private int memberID;
	private String content;
	private String date;
	private int views;
	private String name;
	private String category;
	
	public BoardDto() {
		
	}
	

	public BoardDto(String subject, String content, String category, int memberID, String name) {
		this.category = category;
		this.subject = subject;
		this.content = content;
		this.memberID = memberID;
		this.name = name;
	}


	public int getIndexNum() {
		return indexNum;
	}


	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public int getMemberID() {
		return memberID;
	}


	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getViews() {
		return views;
	}


	public void setViews(int views) {
		this.views = views;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	

	
	
	
}
