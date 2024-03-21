package festival.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    private static String sql;
    private static PreparedStatement pstmt = null;

    public int checkedItem(Connection conn, int itemId) {
        int checkedItemId = 0;
        sql = "SELECT item_id FROM ITEM WHERE item_id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                checkedItemId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkedItemId;
    }
}
