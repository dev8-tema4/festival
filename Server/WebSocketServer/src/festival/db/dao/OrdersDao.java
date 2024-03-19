package festival.db.dao;

import festival.dto.OrdersDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDao {

    private OrdersDto orderDto;

    /**
     * 회원 정보 추가
     */
    public static int injectMemberInfo(Connection conn, int memberId) {
        String sql;
        PreparedStatement pstmt = null;
        int orderId = 0;

        try {
            sql = "INSERT INTO ORDERS (member_id) VALUES(?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            pstmt.executeUpdate();

            sql = "SELECT order_id FROM ORDERS WHERE member_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);

            ResultSet rs = pstmt.executeQuery();

            orderId = rs.getInt("order_id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderId;
    }

}


