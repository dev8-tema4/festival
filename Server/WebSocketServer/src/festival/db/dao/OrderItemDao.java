package festival.db.dao;

import festival.dto.OrderItemDto;
import festival.dto.OrderListResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {

    private static String sql;
    private static PreparedStatement pstmt = null;
    private static OrderListResponseDto orderListResponseDto = new OrderListResponseDto();

    /**
     * 장바구니 추가
     */
    public static int addCart(Connection conn, OrderItemDto orderItemDto) {

        int result = 0;

        try {
            sql = "INSERT INTO ORDER_ITEM(order_id, item_id, count, total_price) VALUES(?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, orderItemDto.getOrderId());
            pstmt.setInt(2, orderItemDto.getItemId());
            pstmt.setInt(3, orderItemDto.getCount());
            pstmt.setInt(4, orderItemDto.getPrice());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 장바구니 조회
     */
    public static List<CartResponseDto> getAllCart(Connection conn, List<Integer> orderIdList) {

        List<CartResponseDto> cartList = new ArrayList<>();

        try {

            for (int orderId : orderIdList) {
                sql ="SELECT i.name, i.price, oi.total_price, oi.count " +
                        "FROM ORDER_ITEM oi " +
                        "INNER JOIN ITEM i " +
                        "ON oi.item_id = i.item_id " +
                        "WHERE oi.order_id = ? " +
                        "AND oi.isBuyed = false";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderId);

                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    CartResponseDto cartResponseDto = CartResponseDto.createCartResponse(
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("total_price"),
                            rs.getInt("count")
                    );

                    cartList.add(cartResponseDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartList;
    }

    /**
     * 주문내역 조회
     */
    public static List<OrderListResponseDto> getOrderList(Connection conn, List<Integer> orderIdList) {

        List<OrderListResponseDto> orderList = new ArrayList<>();

        try {

            for (int orderId : orderIdList) {
                sql = "SELECT i.name, i.price, oi.total_price, oi.count " +
                        "FROM ORDER_ITEM oi " +
                        "INNER JOIN ITEM i " +
                        "ON oi.item_id = i.item_id " +
                        "WHERE oi.order_id = ? " +
                        "AND oi.isBuyed = false";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderId);

                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    orderList.add(
                            orderListResponseDto = new OrderListResponseDto(
                                    rs.getString(1),
                                    rs.getInt(2),
                                    rs.getInt(3),
                                    rs.getInt(4)
                            )
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
