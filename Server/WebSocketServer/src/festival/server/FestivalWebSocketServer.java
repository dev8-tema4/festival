package festival.server;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import festival.server.function.Member;

public class FestivalWebSocketServer extends WebSocketServer{
	public static void main(String[] args) {
		String host = "192.168.0.45"; // localhost
		final int PORT = 9000;

		WebSocketServer server = new FestivalWebSocketServer(new InetSocketAddress(host, PORT));
		server.run();
	}

	public FestivalWebSocketServer(InetSocketAddress inetAddr) {
		super(inetAddr);
	}
	
	@Override
	public void onStart() {
		System.out.println("Server started successfully!!!");
	}
	
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println(conn + " has diconnected");
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.out.println(ex.getMessage());
		// ex.getStackTrace();
	}

	//cmd 패킷 명령제어
	public void onMessage(WebSocket conn, String message) {
		System.out.println("Message from client: " + message);
		JSONObject msgObj = new JSONObject(message);
		String cmd = msgObj.getString("cmd");

		//로그인 기능
		if (cmd.equals("login")) {
			System.out.println("===LOGIN===");
			//로그인 기능
			Member member = new Member(conn, message);
			member.login();
			
		} else if (cmd.equals("allchat")) {
			String id = msgObj.getString("id");
			String msg = msgObj.getString("msg");
			System.out.printf("채팅 id: %s msg:%s \n", id, msg);
			// 클라이언트한테 응답 전송
			JSONObject ackObj = new JSONObject();
			ackObj.put("cmd", "allchat");
			ackObj.put("result", "ok");
			conn.send(ackObj.toString());

			// 전체 접속자한테 브로드 캐스팅
			for (WebSocket con : this.getConnections()) {
				// if (conn != con) // 나를 제외한 모든 접속자들에게 전송
				 con.send(message);
			}
		}else if(cmd.equals("signup")) {
			System.out.println("===SIGN UP===");
			Member member = new Member(conn, message);
			member.signUp();
		}else if(cmd.equals("checkemail")) {
			System.out.println("=== checkmail ===");
			Member member = new Member(conn, message);
			member.checkEmail();

		}

	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		String hostIp = conn.getRemoteSocketAddress().getAddress().getHostAddress().toString();
		System.out.println(hostIp + " connected");

		JSONObject ackObj = new JSONObject();
		ackObj.put("cmd", "connect");
		conn.send(ackObj.toString()); // 클라이언트한테 메시지 보내기
	}


}
