package festival.server.function;

import java.util.ArrayList;
import java.util.List;

import org.java_websocket.WebSocket;
import org.json.JSONObject;

import festival.db.dao.BoardDao;
import festival.dto.BoardDto;


public class Board {
	private WebSocket conn = null;
	private String message = null;
	List<BoardDto> dtolist = new ArrayList<BoardDto>();
	BoardDto dto = new BoardDto();
	BoardDao dao = new BoardDao();

	public Board(WebSocket conn, String message) {
		this.conn = conn;
		this.message = message;
	}
	
	public void boardlist() {
		dao.list(dtolist);
		JSONObject ackObj = new JSONObject();
				
		ackObj.put("cmd", "boardlist");
		ackObj.put("result", dtolist);
		
		conn.send(ackObj.toString());
	}
	
	public void view() {
		JSONObject msgObj = new JSONObject(message);
		int indexNum = msgObj.getInt("indexNum");
				
		dao.view(indexNum);
		JSONObject ackObj = new JSONObject();
		ackObj.put("cmd", "view");
		ackObj.put("result", dao.view(indexNum));
		ackObj.put("indexNum", indexNum);
		
		conn.send(ackObj.toString());
	}
	
	public void write() {
		JSONObject msgObj = new JSONObject(message);
		String subject = msgObj.getString("subject");
		String content = msgObj.getString("content");
		String category = msgObj.getString("category");
		int MEMBER_ID = msgObj.getInt("memberId");
		String name = msgObj.getString("name");
		
		System.out.println("작성글 : " + subject + content + category + MEMBER_ID + name);
		
		BoardDto dto = new BoardDto(subject, content, category, MEMBER_ID, name);
		BoardDao dao = new BoardDao();
		
		int result = dao.write(dto);
		
		if(result == 1) {
			System.out.println("글 작성 완료");
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "write");
			ackObj.put("result", "ok");
			conn.send(ackObj.toString());
		}else {
			System.out.println("글 작성 실패");
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "write");
			ackObj.put("result", "no");
			conn.send(ackObj.toString());
		}
	}
	
	
}
