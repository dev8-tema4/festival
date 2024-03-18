package festival.dto;

public class BoardDto {
	private int indexNum;
	private String subject;
	private int memberID;
	private String content;
	private String date;
	private int views;
	
	
	public BoardDto(int indexNum, String subject, int memberID, String content, int views){
		this.indexNum = indexNum;
		this.subject = subject;
		this.memberID = memberID;
		this.content = content;
		this.views = views;
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

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	
	
	
}
