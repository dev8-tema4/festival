package festival.server.function;

import festival.db.DBConnection;
import festival.db.dao.*;
import festival.dto.CartResponseDto;
import festival.dto.OrderItemDto;
import festival.dto.OrderListResponseDto;
import org.java_websocket.WebSocket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private WebSocket conn = null;
    private String message = null;
    private int result;
    private static MemberDao memberDao = new MemberDao();
    private static OrderItemDto orderItemDto = new OrderItemDto();
    private static ItemDao itemDao = new ItemDao();
    private static int getMemberId;
    private static List<Integer> getOrderIdList = new ArrayList<>();

    public OrderItem(WebSocket conn, String message) {
        this.conn = conn;
        this.message = message;
    }

    public void addCart(List<Integer> orderIdList) {
        JSONObject msgObj = new JSONObject(message);
        int itemId = msgObj.getInt("itemId");
        int count =  msgObj.getInt("count");
        int price =  msgObj.getInt("price");

        for (int orderId : orderIdList) {
            int checkedItemId = itemDao.checkedItem(DBConnection.getConnection(), itemId);
            orderItemDto = new OrderItemDto(orderId, checkedItemId, count, price);
        }
            result = OrderItemDao.addCart(DBConnection.getConnection(), orderItemDto);

        if(result > 0) {
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

        getMemberId = memberDao.getMemberId(DBConnection.getConnection(), memberId);
        getOrderIdList = OrdersDao.getOrderIdList(DBConnection.getConnection(), getMemberId);

        if (getMemberId == memberId) {
            List<CartResponseDto> cartList = OrderItemDao.getAllCart(DBConnection.getConnection(), getOrderIdList);

            JSONObject ackObj = null;
            JSONArray ackArrObj = new JSONArray();
            for (CartResponseDto cartResponseDto : cartList) {
                ackObj = new JSONObject();

                ackObj.put("cmd", "getAllCart");
                ackObj.put("orderItemId", cartResponseDto.getOrderItemId());
                ackObj.put("name", cartResponseDto.getName());
                ackObj.put("price", cartResponseDto.getPrice());
                ackObj.put("count", cartResponseDto.getCount());

                ackArrObj.put(ackObj);
            }
            conn.send(ackArrObj.toString());
        }
    }

    public void buyItem() {
        JSONObject msgObj = new JSONObject(message);
        String orderItemIdSet = msgObj.getString("orderItemId");

        String[] orderItemIdArr = orderItemIdSet.split(", ");

        for (String orderItemId : orderItemIdArr) {
            int findOrderItemId = Integer.parseInt(orderItemId);
            OrderItemDao.buyItem(DBConnection.getConnection(), findOrderItemId);
        }

        JSONObject ackObj = new JSONObject();

        ackObj.put("cmd", "buyCartItem");
        ackObj.put("result", "ok");

        conn.send(ackObj.toString());
    }

    public void getOrderList() {
        JSONObject msgObj = new JSONObject(message);
        int memberId = msgObj.getInt("memberId");

        getMemberId = memberDao.getMemberId(DBConnection.getConnection(), memberId);
        getOrderIdList = OrdersDao.getOrderIdList(DBConnection.getConnection(), getMemberId);

        if (getMemberId == memberId) {
            List<OrderListResponseDto> orderList = OrderItemDao.getOrderList(DBConnection.getConnection(), getOrderIdList);

            JSONObject ackObj = null;
            JSONArray ackArrObj = new JSONArray();

            for (OrderListResponseDto orderListResponseDto : orderList) {
                ackObj = new JSONObject();

                ackObj.put("cmd", "getOrderList");
                ackObj.put("orderItemId", orderListResponseDto.getOrderItemId());
                ackObj.put("name", orderListResponseDto.getName());
                ackObj.put("price", orderListResponseDto.getPrice());
                ackObj.put("count", orderListResponseDto.getCount());

                ackArrObj.put(ackObj);
            }

            conn.send(ackArrObj.toString());
        }
    }
}
