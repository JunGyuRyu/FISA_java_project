package todoApp;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.time.*;
 
public class AdminTodo {
	
	public static void createTodoTable() throws SQLException{
		Connection conn  = Todo.getConnection();
		Statement stmt = conn.createStatement();
		String createTableSQL = "CREATE TABLE IF NOT EXISTS TodoTable("+
				"userId smallint   auto_increment," +
			    "title   VARCHAR(30)," +
			   " isDone   boolean," +
			  " priority  smallint,"+
			   " endDate   VARCHAR(20),"+
			    "primary key(userId, title)"+
			")";
		String checkTableExist = "Select * from TodoTable";
		
		try {
			stmt.executeQuery(checkTableExist);		
			System.out.println("[TodoTable이 이미 존재합니다]");
			Todo.close(conn, stmt);
			
		} catch (Exception e) {
			System.out.println("TodoTable 생성완료");
		}
		
		conn  = Todo.getConnection();
		stmt = conn.createStatement();
		stmt.execute(createTableSQL);
	    
		Todo.close(conn, stmt);
	}
	
	public static void enrollTodo(int userId) {
		System.out.println("안녕하세요 " + User.getName(userId) + "님"+
				"\n할일 등록 시작하겠습니다.");
		
		System.out.println("할일 제목 입력");
		Scanner inputTitle = new Scanner(System.in);
		String title = inputTitle.next();
		
		System.out.println("할일 우선순위 입력");
		
		int priority;
		while (true) { // priority가 int인지 확인
			try {
				Scanner inputPriority = new Scanner(System.in);
				priority = inputPriority.nextInt();
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[우선순위를 다시 입력해주세요.]");
				continue;
			}
		}
		
		System.out.println("할일완료날짜(YYYY-MM-dd) 입력");
		String endDate;
		while (true) { // endDate format 확인
			try {
				Scanner inputEndDate = new Scanner(System.in);
				endDate = inputEndDate.next();
				LocalDate date =  LocalDate.parse(endDate);
				// 수정필요 해당 파라미터 받아서 insert
				Todo.addTodo(userId, title, priority, endDate);
				System.out.println("할일 등록 완료");
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[날짜를 YYYY-MM-dd 형식으로 다시 입력해주세요.]");
				continue;
			}
		}
		
	}
	public static void printTodoList(int userId) throws SQLException {
		System.out.println(User.getName(userId) + "님");
		Todo.showTodoList(userId);
		System.out.println();
		Todo.showDoneList(userId);
	}
	
	public static void saveTodoList(int userId) throws SQLException {
		System.out.println(User.getName(userId) + "님");
		Todo.showTodoList(userId);
		Todo.showDoneList(userId);
		System.out.println();
		
		todoToText(userId);
		System.out.println(User.getName(userId) + "의 txt 파일 작성 완료..");
	}
	
	public static void delTodo(int userId) throws SQLException {
		System.out.println("삭제할 Todo 제목을 입력해 주세요");
		Scanner inputDeleteTitle = new Scanner(System.in);
		String deleteTitle = inputDeleteTitle.next();
		Todo.deleteTodo(userId, deleteTitle);
	}
	
	public static void doneTodo(int userId) throws SQLException {
		System.out.println("완료할 Todo 제목을 입력해 주세요");
		Scanner inputDoneTitle = new Scanner(System.in);
		String doneTitle = inputDoneTitle.next();
		Todo.doneTodo(userId, doneTitle);
	}
	
	public static void todoToText(int userId) throws SQLException {
		File folder = new File("src/todoApp/" + User.getName(userId));
		// 폴더 있으면 false 반환, 없으면 생성
		boolean directoryCreated = folder.mkdir();
		System.out.println("User 생성 여부: "+ directoryCreated);
//		ArrayList<Todo> doneList = Todo.getDoneList();
//		ArrayList<Todo> todoList = user.getTodoList();
		String line = "----------------------------------------------------------------\n";
		
		
		Connection conn  = Todo.getConnection();
		Statement stmt = conn.createStatement();
		
		String userInfoSQL = "select name, age, gender from UserTable where userId = " + userId;
//		System.out.println(userInfoSQL);
		ResultSet rs = stmt.executeQuery(userInfoSQL);
		String user_info = "";
		if (rs.next()) {
			user_info = "[사용자: " + rs.getString("name") + " | 나이: " + rs.getInt("age") + " | 성별: " + rs.getString("gender") + "]\n\n";
		}	
		
		// todoList 폴더에 todo.txt 파일 생성
		
		File file = new File("src/todoApp/" +  User.getName(userId) + "/todoList.txt");
//			String user_info = (String) "[사용자: " +  User.getName(userId) + " | 나이: " + user.getAge() 
//			+ " | 성별: " + user.getGender() + "]\n\n";
		
		// 파일에 입력한 값 작성
		try {
			FileOutputStream fileTodo = new FileOutputStream(file);
			fileTodo.write(user_info.getBytes());
			fileTodo.write(line.getBytes()); // ----- ~~
			String showTodoSQL = "select * from TodoTable where isDone = false  and userId = " + userId;
			
			ResultSet todoRs = stmt.executeQuery(showTodoSQL);
			while(todoRs.next()) {
				fileTodo.write((todoRs.getString("endDate") + "\n").getBytes()); // Date
				fileTodo.write(("우선순위: " + todoRs.getInt("priority") + "순위\n").getBytes()); // Priority
				fileTodo.write((todoRs.getString("title") + "\n").getBytes()); // Title
				fileTodo.write(line.getBytes()); // ----- ~~
			}
			
			fileTodo.close(); // 파일 닫기
		} catch (FileNotFoundException e) {
			System.out.println("[파일을 찾을 수 없습니다]");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("[예외 오류 발생]");
			System.out.println(e);
		}
		
		
		
		// doneList 폴더에 todo.txt 파일 생성
		
		File doneFile = new File("src/todoApp/" + User.getName(userId) + "/doneList.txt");
		
		
		
		// 파일에 입력한 값 작성
		try {
			FileOutputStream fileTodo = new FileOutputStream(doneFile);
			fileTodo.write(user_info.getBytes());
			fileTodo.write(line.getBytes()); // ----- ~~
			
			String showDoneSQL = "select * from TodoTable where isDone = true  and userId = " + userId;
			ResultSet doneRs = stmt.executeQuery(showDoneSQL);
			while(doneRs.next()) {
				fileTodo.write((doneRs.getString("endDate") + "\n").getBytes()); // Date
				fileTodo.write(("우선순위: " + doneRs.getInt("priority") + "순위\n").getBytes()); // Priority
				fileTodo.write((doneRs.getString("title") + "\n").getBytes()); // Title
				fileTodo.write(line.getBytes()); // ----- ~~
			}
			
		
			fileTodo.close(); // 파일 닫기
		} catch (FileNotFoundException e) {
			System.out.println("[파일을 찾을 수 없습니다]");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("[예외 오류 발생]");
			System.out.println(e);
		}
		
			
	}
}
