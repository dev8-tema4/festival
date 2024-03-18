package festival.server.function;

import org.java_websocket.WebSocket;
import org.json.JSONObject;

import festival.db.dao.MemberDao;
import festival.dto.MemberDto;

public class Member {
	private WebSocket conn = null;
	private String message = null;

	public Member(WebSocket conn, String message) {
		this.conn = conn;
		this.message = message;
	}

	public Boolean login() {
		JSONObject msgObj = new JSONObject(message);
		String email = msgObj.getString("email");
		String password = msgObj.getString("password");

		System.out.printf("로그인 id: %s pass :%s\n", email, password);

		boolean check = false;
		// 로그인 DB에서 확인
		MemberDao dao = new MemberDao();
		check = dao.login(email, password);
		// 로그인 결과 패킷 보내기
		if (check) {
			JSONObject ackObj = new JSONObject();
			String name = dao.getNameByEmail(email);

			ackObj.put("cmd", "login");
			ackObj.put("result", "ok");
			ackObj.put("email", email);
			ackObj.put("name", name);
			conn.send(ackObj.toString());
		} else {
			JSONObject ackObj = new JSONObject();
			ackObj.put("cmd", "login");
			ackObj.put("result", "no");
			conn.send(ackObj.toString());
		}
		return check;

	}

	public void signUp() {
		JSONObject msgObj = new JSONObject(message);
		String email = msgObj.getString("email");
		String password = msgObj.getString("password");
		String name = msgObj.getString("name");
		String address = msgObj.getString("address");
		String phone = msgObj.getString("phone");

		System.out.println("회원가입 입력값: " + email + password + name + address + phone);

		MemberDto dto = new MemberDto(email, password, name, address, phone);
		MemberDao dao = new MemberDao();
		int result = dao.signUp(dto);

		if (result == 1) {
			System.out.println("회원가입 성공");
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "signup");
			ackObj.put("result", "ok");
			conn.send(ackObj.toString());
		} else {
			System.out.println("회원가입 실패");
			JSONObject ackObj = new JSONObject();

			ackObj.put("cmd", "signup");
			ackObj.put("result", "no");
			conn.send(ackObj.toString());
		}

	}

	public void checkEmail() {
		int count = 0;
		JSONObject msgObj = new JSONObject(message);
		String email = msgObj.getString("email");

		MemberDao dao = new MemberDao();
		count = dao.checkEmail(email);
		if (count == 1) {
			JSONObject ackObj = new JSONObject();
			ackObj.put("cmd", "checkemail");
			ackObj.put("result", "no");
			conn.send(ackObj.toString());

		} else {
			JSONObject ackObj = new JSONObject();
			ackObj.put("cmd", "checkemail");
			ackObj.put("result", "ok");
			conn.send(ackObj.toString());

		}

	}
}
