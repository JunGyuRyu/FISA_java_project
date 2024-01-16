package todoApp;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Todo {
	
	private String title;
	private Boolean isDone;
	private int priority;
	private String str_endDate;
	
	static {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();//예외 발생 상황을 디테일하게 출력
		}
	}
	
	// get Connection
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/java_todo?characterEncoding=UTF-8&serverTimezone=UTC";
	    String id = "root";
	    String pw = "0000";
		return DriverManager.getConnection(url, id, pw);
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

	public static void showTodoList(int userId) throws SQLException {
		Connection conn  = getConnection();
		Statement stmt = conn.createStatement();
		
		String showTodoSQL = "select * from TodoTable where isDone = false and userId = " + userId +
							" order by priority";
		
		ResultSet rs = stmt.executeQuery(showTodoSQL);
		
		while(rs.next()) {
//			if(rs.getInt("userId") == 0) {
//				System.out.println("[Todo List가 없습니다.]");
//				break;
//			}
			System.out.println("[Todo List]");
			System.out.println(rs.getInt("userId") + " "
					+ rs.getString("title") + " "
					+ rs.getInt("priority") + "순위 "
					+ rs.getString("endDate") + " "
					+ checkDeadLine(rs.getString("endDate"))
					);
		}
		
		close(conn, stmt, rs);
		
	}
	
	public static void showDoneList(int userId) throws SQLException {
		Connection conn  = getConnection();
		Statement stmt = conn.createStatement();
		
		String showTodoSQL = "select * from TodoTable where isDone = true  and userId = " + userId;
		
		ResultSet rs = stmt.executeQuery(showTodoSQL);

		while(rs.next()) {
			System.out.println("[Done List]");
			System.out.println(rs.getInt("userId") + " "
								+ rs.getString("title") + " "
								+ rs.getString("endDate") + " "
								);
			
		}
		close(conn, stmt, rs);
	}
	
	public static void addTodo(int userId, String title, int priority, String str_endDate) throws SQLException {
		Connection conn  = getConnection();
		PreparedStatement pstmt = null;
		boolean isDone = false;
		
		try {
			pstmt = conn.prepareStatement("Insert into TodoTable values (?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, userId);
			pstmt.setString(2, title);
			pstmt.setBoolean(3, isDone);
			pstmt.setInt(4, priority);
			pstmt.setString(5, str_endDate);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("저장 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	public static void doneTodo(int userId, String title) throws SQLException {
		
		Connection conn  = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String sql = String.format("Update TodoTable Set isDone = True where userId = " + userId +
					" and title = '%s'", title);
			pstmt = conn.prepareStatement(sql);
			
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("완료여부 변경 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
	}
	
	public static void deleteTodo(int userId, String title) throws SQLException {
		
		Connection conn  = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String sql = String.format(" DELETE FROM TodoTable "
					   + " WHERE userId = " + userId + " and title = '%s'", title);
			
			pstmt = conn.prepareStatement(sql);
			
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("삭제 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
	}
	
	
	public static String checkDeadLine(String strDate) {
	LocalDate endDate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
	System.out.println(endDate);
	LocalDate date = LocalDate.now(); // 현재 날짜 	
	try {
		Period per = Period.between(date, endDate);
		String deadLine = per.getYears()+"년 "+per.getMonths()+"개월 " + per.getDays() + "일 남았습니다.";
		if (per.getYears() == 0 & per.getMonths() == 0 & per.getDays() == 0) {
			deadLine = "완료 기한 당일입니다.";
		}
		else if (per.getYears() < 0 | per.getMonths() < 0 | per.getDays() < 0) {
			deadLine = "이미 기한이 지났습니다.";
		}
		return deadLine;
	} catch (Exception e) {
		// TODO: handle exception
		return ("완료 시점이 존재하지 않습니다.");
	}
}
	


	}




