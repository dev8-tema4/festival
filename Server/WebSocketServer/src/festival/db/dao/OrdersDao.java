package festival.db.dao;

import festival.dto.OrdersDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersDao {

    private OrdersDto orderDto;

    /**
     * 회원 정보 추가
     */
    public static boolean injectMemberInfo(Connection conn, int memberId) {
        String sql;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
                sql = "INSERT INTO ORDERS (member_id) VALUES(?)";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, memberId);

                result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(result > 0) {
            result = 1;
        }

        return result == 1;
    }
}


