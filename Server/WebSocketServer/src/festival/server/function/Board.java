package festival.server.function;

import org.java_websocket.WebSocket;

public class Board {
	private WebSocket conn = null;
	private String message = null;

	public Board(WebSocket conn, String message) {
		this.conn = conn;
		this.message = message;
	}
	
	public void boardlist() {
		
	}
}
