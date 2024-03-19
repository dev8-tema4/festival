package festival.db.dao;

import festival.dto.OrderItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDao {

    private static String sql;
    private static PreparedStatement pstmt = null;

    public static int addCart(Connection conn, OrderItemDto orderItemDto) {

        int result = 0;

        try {
            sql = "INSERT INTO ORDER_ITEM(order_id, item_id, count, order_price) VALUES(?, ?, ?, ?)";

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
}
