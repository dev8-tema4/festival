package festival.dto;

public class BoardDto {
	private int indexNum;
	private String subject;
	private int memberID;
	private String content;
	private String date;
	private int views;
	private String name;
	
	
	public BoardDto(int indexNum, String subject, int memberID, String content, String date, int views, String name){
		this.indexNum = indexNum;
		this.subject = subject;
		this.memberID = memberID;
		this.content = content;
		this.date = date;
		this.views = views;
		this.name = name;
		
	}

	public BoardDto() {
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	
	
	
}
