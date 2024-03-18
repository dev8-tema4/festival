package festival.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import festival.db.DBConnection;
import festival.db.DBConnection.DBClose;
import festival.dto.MemberDto;

public class MemberDao {
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;


	public boolean login(String login, String password) {
	        String sql = "SELECT COUNT(*) FROM MEMBER WHERE EMAIL = ? AND PASSWORD = ?";
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

	public int signUp(MemberDto dto) {
		String sql = "INSERT INTO MEMBER(EMAIL, PASSWORD, NAME, ADDRESS, PHONE) VALUES(?, ?, ?, ?, ?)";
		int result = 0;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return result;
	}

	public String getNameByEmail(String email) {
	    String name = null;
	    String sql = "SELECT NAME FROM MEMBER WHERE EMAIL = ?";
	    
	    try {
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        rs = pstmt.executeQuery(); // Corrected method call
	        
	        while (rs.next()) {
	            name = rs.getString(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBClose.close(conn, pstmt, rs);
	    }
	    return name;
	}

}
