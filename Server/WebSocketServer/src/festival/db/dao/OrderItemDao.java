package festival.db.dao;

import festival.db.DBConnection;
import festival.dto.CartResponseDto;
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
    private static ResultSet rs = null;
    private static OrderListResponseDto orderListResponseDto = new OrderListResponseDto();

    /**
     * 장바구니 추가
     */
    public static int addCart(Connection conn, OrderItemDto orderItemDto, List<Integer> orderIdList) {

        int result = 0;
                try {
                    sql = "INSERT INTO ORDER_ITEM(order_id, item_id, count, price) VALUES(?, ?, ?, ?)";

                    pstmt = conn.prepareStatement(sql);

                    pstmt.setInt(1, orderItemDto.getOrderId());
                    pstmt.setInt(2, orderItemDto.getItemId());
                    pstmt.setInt(3, orderItemDto.getCount());
                    pstmt.setInt(4, orderItemDto.getPrice());

                    result = pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    DBConnection.DBClose.close(conn, pstmt, rs);
                }
        return result;
    }

    /**
     * 장바구니 상품 수량 추가
     */
    public static int addCountCart(Connection conn, int orderItemId) {
        int result = 0;
        try {
            sql = "UPDATE ORDER_ITEM SET count = count + 1 WHERE order_item_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderItemId);
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
                sql ="SELECT oi.order_item_id, i.name, i.price, oi.count " +
                        "FROM ORDER_ITEM oi " +
                        "INNER JOIN ITEM i " +
                        "ON oi.item_id = i.item_id " +
                        "WHERE oi.order_id = ? " +
                        "AND oi.isBuyed = false";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderId);

                rs = pstmt.executeQuery();

                while(rs.next()) {
                    CartResponseDto cartResponseDto = CartResponseDto.createCartResponse(
                            rs.getInt("order_item_id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("count")
                    );
                    cartList.add(cartResponseDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.DBClose.close(conn, pstmt, rs);
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
                sql = "SELECT oi.order_item_id, i.name, i.price, oi.count " +
                        "FROM ORDER_ITEM oi " +
                        "INNER JOIN ITEM i " +
                        "ON oi.item_id = i.item_id " +
                        "WHERE oi.order_id = ? " +
                        "AND oi.isBuyed = false";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderId);

                rs = pstmt.executeQuery();

                while(rs.next()) {
                    OrderListResponseDto orderListDto = OrderListResponseDto.createOrderList(
                            rs.getInt("order_item_id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("count")
                    );
                    orderList.add(orderListDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.DBClose.close(conn, pstmt, rs);
        }
        return orderList;
    }

    /**
     * 구매하기
     */
    public static void buyItem(Connection conn, int orderItemId) {

        try {
            sql = "UPDATE ORDER_ITEM SET isBuyed = 1 WHERE order_item_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderItemId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.DBClose.close(conn, pstmt, rs);
        }
    }

    /**
     * 장바구니에 있는 상품인지 확인
     */
    public static int alreadyItemId(Connection conn, List<Integer> orderIdList, int itemId) {

        int result = 0;

        try {

            for (int orderId : orderIdList) {
                sql = "SELECT order_item_id FROM ORDER_ITEM WHERE order_id = ? AND item_id = ?";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderId);
                pstmt.setInt(2, itemId);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    result = rs.getInt(1);
                }
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
