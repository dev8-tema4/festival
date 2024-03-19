package festival.server.function;

import festival.db.DBConnection;
import festival.db.dao.OrdersDao;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

public class Orders {
    private WebSocket conn = null;
    private String message = null;

    public Orders(WebSocket conn, String message) {
        this.conn = conn;
        this.message = message;
    }

    public void injectMemberInfo() {
        JSONObject msgObj = new JSONObject(message);
        int memberId = msgObj.getInt("memberId");

        boolean check = false;

        check = OrdersDao.injectMemberInfo(DBConnection.getConnection(), memberId);

        if(check) {
            JSONObject ackObj = new JSONObject();

            ackObj.put("result", "success");
            conn.send(ackObj.toString());
        } else {
            JSONObject ackObj = new JSONObject();

            ackObj.put("result", "fail");
            conn.send(ackObj.toString());
        }
    }
}
