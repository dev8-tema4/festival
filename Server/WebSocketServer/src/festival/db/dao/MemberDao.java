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


	/**
	 * 로그인
	 */
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

	/**
	 * 회원가입
	 */
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

	/**
	 * 회원 아이디 조회
	 */
	public int getMemberId(Connection conn, int memberId) {
		int getMemberId = 0;
		String sql = "SELECT member_id FROM MEMBER WHERE member_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				getMemberId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getMemberId;
	}


	/**
	 * 회원 이메일 조회
	 */
	public MemberDto getMemberInfoByEmail(String email) {
		
	    String sql = "SELECT * FROM MEMBER WHERE EMAIL = ?";
		MemberDto dto = null;
		
	    try {
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        rs = pstmt.executeQuery(); // Corrected method call
	        
	        while (rs.next()) {
				dto = new MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) ,rs.getString(6));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBClose.close(conn, pstmt, rs);
	    }
	    return dto;
	}

	public int checkEmail(String email) {
	    String sql = "SELECT COUNT(*) FROM MEMBER WHERE EMAIL = ?";
	    int count = 0;
	    try {
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
			rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBClose.close(conn, pstmt, rs);
	    }
	    System.out.println(count);
	    return count;
	}

}
