package festival.server.function;

import org.java_websocket.WebSocket;
import org.json.JSONObject;

import festival.db.dao.MyPageDao;
import festival.dto.MyPageDto;
 

public class MyInfoHandler {
	private WebSocket conn = null;
	private String message = null;
	
	public MyInfoHandler(WebSocket conn, String message) {
		this.conn = conn;
		this.message = message;
	}
	
	public void handle() {
		 JSONObject packet = new JSONObject(message);
		MyPageDao dao = new MyPageDao();
		MyPageDto member = dao.getMember(packet.getInt("memberId"));
		System.out.println(member);
		
		
		
		if(member == null) {
			JSONObject response = new JSONObject();
			response.put("error","User not found");
			conn.send(response.toString());
		}else {
			JSONObject userInfo = new JSONObject();
			userInfo.put("cmd","MyInfo");
			userInfo.put("email", member.getEmail());
			userInfo.put("name", member.getName());
			userInfo.put("address", member.getAddress());
			userInfo.put("phone", member.getPhone());
			
			
			
			conn.send(userInfo.toString());
		}
	}
	
	
	

}
