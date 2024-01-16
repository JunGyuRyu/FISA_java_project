package todoApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
    
    
    public static void setAge(int userId) {
    	System.out.println(User.getName(userId)+ "님의 바꿀 나이를 입력해 주세요");
    	int newAge;
    	while (true) { // newAge가 int인지 확인
			try {
				Scanner setAge = new Scanner(System.in);
				newAge = setAge.nextInt();
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[나이를 다시 입력해주세요.]");
				continue;
			}
		}
    	
        String sql = "UPDATE UserTable SET age = ? WHERE userId = ?";
        try (Connection conn = AdminUser.getConnection(); // AdminUser 클래스에서 연결 가져오기
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newAge); // 첫 번째 물음표에 newAge 설정
            pstmt.setInt(2, userId); // 두 번째 물음표에 userId 설정

            int affectedRows = pstmt.executeUpdate(); // 쿼리 실행, 영향 받은 행의 수 반환

            if (affectedRows > 0) {
                System.out.println("[사용자 나이 업데이트 성공]");
            } else {
                System.out.println("해당 ID의 사용자가 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        }
    }
    
    
    public static void setName(int userId) {
    	System.out.println(User.getName(userId)+ "님의 바꿀 이름을 입력해 주세요");
    	String newName;
    	Scanner setName = new Scanner(System.in);
    	newName = setName.next();
    	
        String sql = "UPDATE UserTable SET name = ? WHERE userId = ?";
        try (Connection conn = AdminUser.getConnection(); // AdminUser 클래스에서 연결 가져오기
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName); // 첫 번째 물음표에 newAge 설정
            pstmt.setInt(2, userId); // 두 번째 물음표에 userId 설정

            int affectedRows = pstmt.executeUpdate(); // 쿼리 실행, 영향 받은 행의 수 반환

            if (affectedRows > 0) {
                System.out.println("[사용자 이름 업데이트 성공]");
            } else {
                System.out.println("해당 이름의 사용자가 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        }
    }
    

    public static void setGender(int userId) {
    	System.out.println(User.getName(userId)+ "님의 바꿀 성별을 입력해 주세요");
    	String newGender;
    	Scanner setGender = new Scanner(System.in);
    	newGender = setGender.next();

        String sql = "UPDATE UserTable SET gender = ? WHERE userId = ?";
        try (Connection conn = AdminUser.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newGender);
            pstmt.setInt(2, userId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("[사용자 성별 업데이트 성공]"); 
            } else {
                System.out.println("해당 ID의 사용자가 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        }
    }
}