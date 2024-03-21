package festival.server.function;

import festival.db.DBConnection;
import festival.db.dao.OrdersDao;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

import java.util.List;

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

        int check = OrdersDao.injectMemberInfo(DBConnection.getConnection(), memberId);

        if(check > 0) {
            System.out.println("success");
            JSONObject ackObj = new JSONObject();

            ackObj.put("result", "success");
            conn.send(ackObj.toString());
        } else {
            JSONObject ackObj = new JSONObject();

            ackObj.put("result", "fail");
            conn.send(ackObj.toString());
        }
    }

    public List<Integer> getOrderIdList() {
        System.out.println("success");
        JSONObject msgObj = new JSONObject(message);
        int memberId = msgObj.getInt("memberId");

        return OrdersDao.getOrderIdList(DBConnection.getConnection(), memberId);
    }
}
