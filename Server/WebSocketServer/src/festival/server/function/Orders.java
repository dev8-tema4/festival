package festival.server.function;

import festival.db.DBConnection;
import festival.db.dao.OrdersDao;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

import java.util.List;

public class Orders {
    private WebSocket conn = null;
    private String message = null;

    public Orders() {}

    public Orders(WebSocket conn, String message) {
        this.conn = conn;
        this.message = message;
    }

    /**
     * 주문한 회원 아이디 주입
     */
    public void injectMemberInfo(int memberId) {
        int check = OrdersDao.injectMemberInfo(DBConnection.getConnection(), memberId);

        if(check > 0) {
            JSONObject ackObj = new JSONObject();

            ackObj.put("result", "success");
            conn.send(ackObj.toString());
        } else {
            JSONObject ackObj = new JSONObject();

            ackObj.put("result", "fail");
            conn.send(ackObj.toString());
        }
    }

    /**
     * 로그인한 사용자가 주문한 orderId
     */
    public List<Integer> getOrderIdList() {
        JSONObject msgObj = new JSONObject(message);
        int memberId = msgObj.getInt("memberId");

        return OrdersDao.getOrderIdList(DBConnection.getConnection(), memberId);
    }
}
