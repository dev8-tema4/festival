package festival.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import festival.db.DBConnection;
import festival.db.DBConnection.DBClose;
import festival.dto.BoardDto;

public class BoardDao {
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;

	public void list(List<BoardDto> dtolist) {

		String sql = "SELECT * FROM BOARD";
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setIndexNum(rs.getInt(1));
				dto.setSubject(rs.getString(2));
				dto.setMemberID(rs.getInt(3));
				dto.setContent(rs.getString(4));
				dto.setDate(rs.getString(5));
				dto.setViews(rs.getInt(6));
				dto.setName(rs.getString(7));
				dtolist.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 및 리소스 해제
			DBClose.close(conn, pstmt, rs);
		}
	}

	public int write(BoardDto dto) {
		String sql = "INSERT INTO BOARD(CATEGORY, SUBJECT, CONTENT, MEMBER_ID, NAME) VALUE (?, ?, ?, ?, ?)";
		int result = 0;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCategory());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getMemberID());
			pstmt.setString(5, dto.getName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return result;
	}

	public String view(int indexNum) {
		String result = null;
		String sql = "SELECT * FROM BOARD WHERE INDEXNUM=?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, indexNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getString(4);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 및 리소스 해제
			DBClose.close(conn, pstmt, rs);
		}
		return result;
	}
}
