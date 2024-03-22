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
//		dao.list(dtolist);
		
		JSONObject msgObj = new JSONObject(message);
		int pageNum = msgObj.getInt("pagenum");
		int totalBoardlist = dao.totalBoardCount();
		int selectPage = (pageNum - 1) *5;
		
		dao.pagelist(selectPage,dtolist);
		
		System.out.println(totalBoardlist);
		JSONObject ackObj = new JSONObject();
		ackObj.put("cmd", "boardlist");
		ackObj.put("result", dtolist);
		ackObj.put("pageCount",(totalBoardlist / 5) + 1);
		ackObj.put("currentPage", selectPage);

		conn.send(ackObj.toString());
	}

	public void view() {
		JSONObject msgObj = new JSONObject(message);
		int indexNum = msgObj.getInt("indexNum");
		BoardDao dao = new BoardDao();
		int result = dao.plusViews(indexNum);
		if (result == 1) {
			int views = dao.changeViews(indexNum);
			JSONObject ackObj = new JSONObject();
			ackObj.put("cmd", "view");
			ackObj.put("result", dao.view(indexNum));
			ackObj.put("indexNum", indexNum);

			conn.send(ackObj.toString());
		} else {
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "view");
			ackObj.put("result", "no");
			conn.send(ackObj.toString());
		}

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

		if (result == 1) {
			System.out.println("글 작성 완료");
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "write");
			ackObj.put("result", "ok");
			conn.send(ackObj.toString());
		} else {
			System.out.println("글 작성 실패");
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "write");
			ackObj.put("result", "no");
			conn.send(ackObj.toString());
		}
	}

	public void popularlist() {
		dao.popularlist(dtolist);
		JSONObject ackObj = new JSONObject();

		ackObj.put("cmd", "boardlist");
		ackObj.put("result", dtolist);

		conn.send(ackObj.toString());
	}

	public void questionlist() {
		dao.questionlist(dtolist);
		JSONObject ackObj = new JSONObject();

		ackObj.put("cmd", "boardlist");
		ackObj.put("result", dtolist);

		conn.send(ackObj.toString());
	}

	public void recruitlist() {
		dao.recruitlist(dtolist);
		JSONObject ackObj = new JSONObject();

		ackObj.put("cmd", "boardlist");
		ackObj.put("result", dtolist);

		conn.send(ackObj.toString());
	}

	public void freelist() {
		dao.freelist(dtolist);
		JSONObject ackObj = new JSONObject();

		ackObj.put("cmd", "boardlist");
		ackObj.put("result", dtolist);

		conn.send(ackObj.toString());
	}

	public void searchlist() {
		JSONObject msgObj = new JSONObject(message);
		String type = msgObj.getString("type");
		String search = msgObj.getString("search");
		System.out.println(type + search);

		if (type.equals("titleContent")) {
			dao.searchByTitleContent(dtolist, search);
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "boardlist");
			ackObj.put("result", dtolist);

			conn.send(ackObj.toString());
		} else if (type.equals("title")) {
			dao.searchByTitle(dtolist, search);
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "boardlist");
			ackObj.put("result", dtolist);

			conn.send(ackObj.toString());
		} else if (type.equals("author")) {
			dao.searchByAuthor(dtolist, search);
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "boardlist");
			ackObj.put("result", dtolist);

			conn.send(ackObj.toString());
		}

	}

	public void mylist() {
		JSONObject msgObj = new JSONObject(message);
		int MEMBER_ID = msgObj.getInt("memberId");
		dao.myList(dtolist, MEMBER_ID);
		JSONObject ackObj = new JSONObject();
		
		ackObj.put("cmd", "mylist");
		ackObj.put("result", dtolist);

		conn.send(ackObj.toString());
	}

	public void paging() {
		System.out.println("하이하이");
		JSONObject msgObj = new JSONObject(message);
		int pageNum = msgObj.getInt("pagenum");
		int totalBoardlist = dao.totalBoardCount();
		System.out.println(totalBoardlist);
		
	}
}
