package festival.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import festival.db.DBConnection;
import festival.dto.MyPageDto;

public class MyPageDao {
    // 생성자에서 DB 연결을 초기화하지 않습니다.
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
    
    // 멤버 정보 조회
    public MyPageDto getMember(int memberId) {
        MyPageDto member = null;
        System.out.println(memberId);
        String sql = "SELECT * FROM member WHERE member_id = ?";
        
        try {
			conn = DBConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberId);
			
			//executeQuery()메소드를 사용하여 SELECT 쿼리 실행
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				member = new MyPageDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
        System.out.println(member+"   dao");
		return member;

    }


}