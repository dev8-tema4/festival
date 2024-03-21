package festival.db.dao;

import festival.db.DBConnection;
import festival.dto.OrdersDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {
    private static String sql;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    private static int check;

    /**
     * 회원 정보 추가
     */
    public static int injectMemberInfo(Connection conn, int memberId) {


        try {
            sql = "INSERT INTO ORDERS (member_id) VALUES(?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            check = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.DBClose.close(conn, pstmt, rs);
        }
        return check;
    }

    /**
     * OrderId 전체 조회
     */
    public static List<Integer> getOrderIdList(Connection conn, int memberId) {

        List<Integer> orderIdList = new ArrayList<>();

        try {
            sql = "SELECT order_id FROM ORDERS WHERE member_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);

            rs = pstmt.executeQuery();

        while (rs.next()) {
            orderIdList.add(rs.getInt("order_id"));
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.DBClose.close(conn, pstmt, rs);
        }

        return orderIdList;
    }

    /**
     * orderId 단 건 조회
     */
    public static int getOrderId(Connection conn, int getMemberId) {
        int orderId = 0;
        try {
            sql = "SELECT order_id FROM ORDERS WHERE member_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, getMemberId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.DBClose.close(conn, pstmt, rs);
        }
        return orderId;
    }
}


