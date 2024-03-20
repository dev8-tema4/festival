package festival.server.function;

import festival.db.DBConnection;
import festival.db.dao.CartResponseDto;
import festival.db.dao.OrderItemDao;
import festival.dto.OrderItemDto;
import festival.dto.OrderListResponseDto;
import festival.dto.OrdersDto;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

import java.util.List;

public class OrderItem {
    private WebSocket conn = null;
    private String message = null;
    private OrdersDto ordersDto;
    private OrderItemDto orderItemDto;

    public OrderItem(WebSocket conn, String message) {
        this.conn = conn;
        this.message = message;
    }

    public void addCart(int orderId) {
        JSONObject msgObj = new JSONObject(message);
        int itemId = msgObj.getInt("itemId");
        int count =  msgObj.getInt("count");
        int price =  msgObj.getInt("price");

        OrderItemDto orderItemDto = new OrderItemDto(orderId, itemId, count, price);
        int result = OrderItemDao.addCart(DBConnection.getConnection(), orderItemDto);

        if(result > 0) {
            System.out.println("success");

            JSONObject ackObj = new JSONObject();

            ackObj.put("cmd", "addCart");
            ackObj.put("result", "success");

            conn.send(ackObj.toString());
        } else {
            System.out.println("fail");

            JSONObject ackObj = new JSONObject();

            ackObj.put("cmd", "addCart");
            ackObj.put("result", "fail");

            conn.send(ackObj.toString());
        }
    }

    public void getAllCart() {

        JSONObject msgObj = new JSONObject(message);

        int memberId = msgObj.getInt("memberId");

        if (ordersDto.getMemberId() == memberId) {
            List<CartResponseDto> cartList = OrderItemDao.getAllCart(DBConnection.getConnection(), orderItemDto);

            JSONObject ackObj = new JSONObject();
            ackObj.put("cmd", "getAllCart");
            ackObj.put("getAllCart", cartList);
        }
    }

    public void getOrderList() {

        JSONObject msgObj = new JSONObject(message);

        int memberId = msgObj.getInt("memberId");

        if (ordersDto.getMemberId() == memberId) {
            List<OrderListResponseDto> orderList = OrderItemDao.getOrderList(DBConnection.getConnection(), orderItemDto);

            JSONObject ackObj = new JSONObject();
            ackObj.put("cmd", "getOrderList");
            ackObj.put("getOrderList", orderList);
        }
    }
}
