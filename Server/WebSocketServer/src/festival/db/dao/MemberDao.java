package festival.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import festival.db.DBConnection;
import festival.db.DBConnection.DBClose;

public class MemberDao {
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;

	   public boolean login(String login, String password) {
	        String sql = "SELECT COUNT(*) FROM MEMBER WHERE USER_ID = ? AND PASSWORD = ?";
	        int count = 0;
	        try {
	            conn = DBConnection.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, login);
	            pstmt.setString(2, password);
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	                count = rs.getInt(1);
	            }
	            System.out.println("로그인 값 : " + count);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // 연결 및 리소스 해제
				DBClose.close(conn, pstmt, rs);
	        }
	        
	        // 사용자 인증 여부 반환
	        return count == 1;
	    }
}
