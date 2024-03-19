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
	
	public void write() {
		
	}
}
