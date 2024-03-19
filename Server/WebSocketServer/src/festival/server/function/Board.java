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
	
	
}
