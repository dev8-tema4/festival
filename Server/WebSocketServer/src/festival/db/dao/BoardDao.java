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

		String sql = "SELECT * FROM BOARD ORDER BY INDEXNUM DESC";
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
				dto.setCategory(rs.getString(8));
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
		String sql = "SELECT * FROM BOARD WHERE INDEXNUM=? ORDER BY INDEXNUM DESC limit 14";

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

	public int plusViews(int indexNum) {
		int result = 0;
		String sql = "UPDATE BOARD SET VIEWS = VIEWS +1  WHERE INDEXNUM = ?";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, indexNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return result;
	}

	public int changeViews(int indexNum) {
		String sql = "SELECT VIEWS FROM BOARD WHERE INDEXNUM = ?";
		int result = 0;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, indexNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return result;
	}

	public void popularlist(List<BoardDto> dtolist) {

		String sql = "SELECT * FROM BOARD ORDER BY VIEWS DESC LIMIT 14";
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
				dto.setCategory(rs.getString(8));
				dtolist.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 및 리소스 해제
			DBClose.close(conn, pstmt, rs);
		}
	}

	public void questionlist(List<BoardDto> dtolist) {

		String sql = "SELECT * FROM BOARD WHERE CATEGORY='질문' ORDER BY INDEXNUM DESC";
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
				dto.setCategory(rs.getString(8));
				dtolist.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 및 리소스 해제
			DBClose.close(conn, pstmt, rs);
		}
	}

	public void recruitlist(List<BoardDto> dtolist) {

		String sql = "SELECT * FROM BOARD WHERE CATEGORY='모집' ORDER BY INDEXNUM DESC";
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
				dto.setCategory(rs.getString(8));
				dtolist.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 및 리소스 해제
			DBClose.close(conn, pstmt, rs);
		}
	}

	public void freelist(List<BoardDto> dtolist) {

		String sql = "SELECT * FROM BOARD WHERE CATEGORY='자유' ORDER BY INDEXNUM DESC";
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
				dto.setCategory(rs.getString(8));
				dtolist.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 및 리소스 해제
			DBClose.close(conn, pstmt, rs);
		}
	}

	public void searchByTitleContent(List<BoardDto> dtolist, String search) {
	    String sql = "SELECT * FROM BOARD WHERE SUBJECT LIKE ? OR CONTENT LIKE ?";
	    try {
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + search + "%");
	        pstmt.setString(2, "%" + search + "%");

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
	            dto.setCategory(rs.getString(8));
	            dtolist.add(dto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 연결 및 리소스 해제
	        DBClose.close(conn, pstmt, rs);
	    }
	}

	public void searchByTitle(List<BoardDto> dtolist, String search) {
		String sql = "SELECT * FROM BOARD WHERE SUBJECT LIKE ?";
	    try {
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + search + "%");
	        
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
	            dto.setCategory(rs.getString(8));
	            dtolist.add(dto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 연결 및 리소스 해제
	        DBClose.close(conn, pstmt, rs);
	    }		
	}

	public void searchByAuthor(List<BoardDto> dtolist, String search) {
		String sql = "SELECT * FROM BOARD WHERE NAME LIKE ?";
	    try {
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + search + "%");
	        
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
	            dto.setCategory(rs.getString(8));
	            dtolist.add(dto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 연결 및 리소스 해제
	        DBClose.close(conn, pstmt, rs);
	    }		
	}
	public void myList(List<BoardDto> dtolist, int MEMBER_ID) {
		String sql = "SELECT * FROM BOARD WHERE MEMBER_ID = ? ORDER BY INDEXNUM DESC";
	    try {
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, MEMBER_ID);
	        
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
	            dto.setCategory(rs.getString(8));
	            dtolist.add(dto);
	        }
	        System.out.println(dtolist.toString());
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 연결 및 리소스 해제
	        DBClose.close(conn, pstmt, rs);
	    }		
	}

	public int totalBoardCount() {
		String sql = "SELECT COUNT(*) FROM BOARD";
		int result = 0;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		}catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 연결 및 리소스 해제
	        DBClose.close(conn, pstmt, rs);
	    }
		return result;
	}

}
