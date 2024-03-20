package festival.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import festival.db.DBConnection;
import festival.dto.MemberDto;

public class MyPageDao {
    // 생성자에서 DB 연결을 초기화하지 않습니다.
    
    // 멤버 정보 조회
    public MemberDto getMember(int memberId) {
        MemberDto member = null;
        String sql = "SELECT * FROM member WHERE id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    member = new MemberDto(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 내용을 더 구체적으로 추가할 수 있습니다.
        }
        return member;
    }
}