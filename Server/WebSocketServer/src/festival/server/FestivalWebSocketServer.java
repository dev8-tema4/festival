package festival.server;

import java.net.InetSocketAddress;
import java.util.List;

import festival.server.function.OrderItem;
import festival.server.function.Orders;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import festival.server.function.Board;
import festival.server.function.Member;
import festival.server.function.MyInfoHandler;

public class FestivalWebSocketServer extends WebSocketServer{
	public static void main(String[] args) {
		String host = "192.168.0.33"; // localhost
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
			String memberId = msgObj.getString("memberId");
			String msg = msgObj.getString("msg");
			String name = msgObj.getString("name");
			System.out.printf("이름 : %s 채팅 id: %s msg:%s \n",name, memberId, msg);

			JSONObject ackObj = new JSONObject();
			ackObj.put("cmd", "allchat");
			ackObj.put("result", "ok");
			conn.send(ackObj.toString());

			// 전체 접속자한테 브로드 캐스팅
			for (WebSocket con : this.getConnections()) {
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
		} else if(cmd.equals("addCart")) {
			System.out.println("=== addCart ===");
			Orders orders = new Orders(conn, message);
			orders.injectMemberInfo();

			List<Integer> orderIdList = orders.getOrderIdList();

			OrderItem orderItem = new OrderItem(conn, message);
			orderItem.addCart(orderIdList);
		} else if(cmd.equals("getAllCart")) {
			System.out.println("=== getAllCart ===");
			OrderItem orderItem = new OrderItem(conn, message);
			orderItem.getAllCart();
		} else if (cmd.equals("getOrderList")) {
			System.out.println("=== getOrderList ===");
			OrderItem orderItem = new OrderItem(conn, message);
			orderItem.getOrderList();
		} else if (cmd.equals("buyCartItem")) {
			System.out.println("=== butCartItem ===");
			OrderItem orderItem = new OrderItem(conn, message);
			orderItem.buyItem();
		} else if(cmd.equals("boardlist")) {
			Board board = new Board(conn, message);
			board.boardlist();
		}else if(cmd.equals("popularlist")) {
			Board board = new Board(conn, message);
			board.popularlist();
		}else if(cmd.equals("questionlist")) {
			Board board = new Board(conn, message);
			board.questionlist();
		}else if(cmd.equals("recruitlist")) {
			Board board = new Board(conn, message);
			board.recruitlist();
		}else if(cmd.equals("freelist")) {
			Board board = new Board(conn, message);
			board.freelist();
		}else if(cmd.equals("searchlist")) {
			Board board = new Board(conn, message);
			board.searchlist();
		}else if(cmd.equals("mylist")) {
			Board board = new Board(conn, message);
			board.mylist();
		}else if(cmd.equals("view")) {
			Board board = new Board(conn, message);
			board.view();
		}else if(cmd.equals("write")) {
			Board board = new Board(conn, message);
			board.write();
		}else if(cmd.equals("MyInfo")) {
			MyInfoHandler handler = new MyInfoHandler(conn, message);
			handler.handle();
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
