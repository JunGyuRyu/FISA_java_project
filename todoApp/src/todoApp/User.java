package todoApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    
    public static void userInfo(int userId) {
        String sql = "SELECT name, age, gender FROM UserTable WHERE userId = ?";

        try (Connection conn = AdminUser.getConnection(); // AdminUser 클래스에서 연결 가져오기
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");

            System.out.println("[사용자: " + name + " | 나이: " + age + " | 성별: " + gender + "]\n\n");
            
            } else {
                 System.out.println( "해당 ID의 사용자가 없습니다.");
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
        
    }
    
    public static String getName(int userId) {
        String sql = "SELECT name FROM UserTable WHERE userId = ?";
        String name = "";
        try (Connection conn = AdminUser.getConnection(); // AdminUser 클래스에서 연결 가져오기
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
            
            } else {
            	System.out.println("해당 이름의 사용자가 없습니다.");

            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return e.toString();
        }
		return name;
        
    }
    
//    public static void setAge(int userId, int age) {
//    	
//    }
    
}