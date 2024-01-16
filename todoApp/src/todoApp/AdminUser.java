package todoApp;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AdminUser {
	
	public static void addUser() {
		System.out.println("유저이름 입력");
		Scanner inputName = new Scanner(System.in);
		String name = inputName.next();

		System.out.println("유저나이 입력");
		int age;
		while (true) { // age가 int인지 확인
			try {
				Scanner inputAge = new Scanner(System.in);
				age = inputAge.nextInt();
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[나이를 다시 입력해주세요.]");
				continue;
			}
		}
		
		System.out.println("유저성별 입력");
		Scanner inputGender = new Scanner(System.in);
		String gender = inputGender.next();

		insertUser(name, age, gender);
		int maxId = getMaxId();
		
		System.out.println("-----개인정보-----");
		System.out.println("- 아이디 : " + maxId);
		System.out.println("- 이름 : " + name);
		System.out.println("- 나이 : " + age);
		System.out.println("- 성별 : " + gender);
		
	}
	
	
	
	public static int checkUserId() {
		System.out.println("유저 아이디 입력해주세요");
		int userId;
		while (true) { // userId가 int인지 확인
			try {
				Scanner inputId = new Scanner(System.in);
				userId = inputId.nextInt();
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[아이디를 다시 입력해주세요.]");
				continue;
			}
		}
		
		try {
			Connection conn  = getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "select userId from UserTable"
			+ "WHERE userId = " + userId;
			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("userId");
			}	

			close(conn, stmt, rs);
		} catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return -1;
	}
	
	
	static {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	// get Connection
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/java_todo?characterEncoding=UTF-8&serverTimezone=UTC";
	    String id = "root";
	    String pw = "0000";
		return DriverManager.getConnection(url, id, pw);
	}

	public static void createUserTable() throws SQLException{
		try {
			Connection conn  = getConnection();
			Statement stmt = conn.createStatement();
			String createTableQuery = "CREATE TABLE IF NOT EXISTS UserTable ("
		            + "userId	smallint auto_increment,"
		            + "name		VARCHAR(800),"
		            + "age		smallint,"
		            + "gender	VARCHAR(30),"
		            + "primary key(userId)"
		            + ")";
			
			String checkTableExist = "Select * from UserTable";
			try {
				stmt.executeQuery(checkTableExist);		
				System.out.println("[UserTable이 이미 존재합니다]");
				close(conn, stmt);
				
			} catch (Exception e) {
				System.out.println("[UserTable 생성완료]");
			}
			
			conn  = getConnection();
			stmt = conn.createStatement();
		    // 테이블 생성 쿼리 실행
			stmt.execute(createTableQuery);
		    
			close(conn, stmt);
		} catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}

	public static void close(Connection conn, Statement stmt) throws SQLException {
		stmt.close();
		conn.close();
	}
	
	
	public static void insertUser(String name, int age, String gender){
		try {
			Connection conn  = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO UserTable (name, age, gender) VALUES (?, ?, ?)");

			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, gender);
			
		    // INSERT 쿼리 실행
			int result = pstmt.executeUpdate();	
			if(result == 1) {
				System.out.println("[" + name + "님 등록 완료]");
			}   

			close(conn, pstmt);
		} catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static int getMaxId() {
		try {
			Connection conn  = getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "select MAX(userId) AS userId from UserTable;";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("userId");
			}	

			close(conn, stmt, rs);
		} catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		System.out.println("ID 조회 실패..");
		return -1;
	}
	
}


