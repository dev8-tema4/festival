package festival.server.function;


import org.java_websocket.WebSocket;
import org.json.JSONObject;

import festival.db.dao.MemberDao;

public class Login {

	private WebSocket conn = null;
	private String message = null;

	public Login(WebSocket conn, String message) {
		this.conn = conn;
		this.message = message;
	}

	public Boolean checkLogin() {
		JSONObject msgObj = new JSONObject(message);
		String id = msgObj.getString("id");
		String pass = msgObj.getString("pass");

		System.out.printf("로그인 id: %s pass :%s\n", id, pass);

		boolean check = false;
		//로그인 DB에서 확인
		MemberDao dao = new MemberDao();
		check = dao.login(id, pass);
		//로그인 결과 패킷 보내기
		if (check) {
			JSONObject ackObj = new JSONObject();
			ackObj.put("cmd", "login");
			ackObj.put("result", "ok");
			ackObj.put("id", id);

			conn.send(ackObj.toString());
		}else {
			JSONObject ackObj = new JSONObject();
			ackObj.put("cmd", "login");
			ackObj.put("result", "no");
			conn.send(ackObj.toString());
		}
		return check;
		
	}
}
