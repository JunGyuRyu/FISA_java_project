package todoApp;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Admin {
	
//	public static void main(String[] args) {
//		User user = new User("user01", 10, "f");
//		
//		String strDate1 = "2023-01-02";
//		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//		LocalDate time1 = LocalDate.parse(strDate1, format1);
//		
//		user.addTodo("todo1", 1, time1);
//		System.out.println(user.getName());
//		todoToText(user);
//		System.out.println("끝");
//	}
	public static void todoToText(User user) {
		File folder = new File("src/todoApp/" + user.getName());
		// 폴더 있으면 false 반환, 없으면 생성
		boolean directoryCreated = folder.mkdir();
		System.out.println("User 생성 여부: "+ directoryCreated);
		
		ArrayList<Todo> doneList = user.doneList();
		ArrayList<Todo> todoList = user.remainList();
		
		String line = "----------------------------------------------------------------\n";
		
		// todoList 폴더에 todo.txt 파일 생성
		for (int i = 0; i < todoList.size(); i++) {
			Todo todo = todoList.get(i);
			File file = new File("src/todoApp/" + user.getName() + "/todoList.txt");
			String user_info = (String) "userName: " + user.getName() + "| userAge: " + user.getAge() 
			+ "| userGender: " + user.getGender() + "\n\n";
			
			
			// 파일에 입력한 값 작성
			try {
				FileOutputStream fileTodo = new FileOutputStream(file);
				fileTodo.write(user_info.getBytes());
				fileTodo.write(line.getBytes()); // ----- ~~
				for (Todo todos: user.remainList()) {
					System.out.println(todos);
					fileTodo.write((todo.getEndDate().toString()+"\n").getBytes()); // Date
					fileTodo.write((todo.getTitle().toString()+"\n").getBytes()); // Title
					fileTodo.write(line.getBytes()); // ----- ~~
				}
				fileTodo.close(); // 파일 닫기
			} catch (FileNotFoundException e) {
				System.out.println("[파일을 찾을 수 없습니다]");
				System.out.println(e);
			} catch (Exception e) {
				System.out.println("[예외 오류 발생]");
				System.out.println(e);
			} finally {
				System.out.println("text 작성 코드 실행 끝..");
			}
		}
		
		
		// doneList 폴더에 todo.txt 파일 생성
		for (int i = 0; i < doneList.size(); i++) {
			Todo todo = doneList.get(i);
			File file = new File("src/todoApp/" + user.getName() + "/doneList.txt");
			String user_info = (String) "userName: " + user.getName() + " | userAge: " + user.getAge() 
			+ " | userGender: " + user.getGender() + "\n\n";
			
			
			// 파일에 입력한 값 작성
			try {
				FileOutputStream fileTodo = new FileOutputStream(file);
				fileTodo.write(user_info.getBytes());
				fileTodo.write(line.getBytes()); // ----- ~~
				for (Todo todos: user.remainList()) {
					System.out.println(todos);
					fileTodo.write((todo.getEndDate().toString()+"\n").getBytes()); // Date
					fileTodo.write((todo.getTitle().toString()+"\n").getBytes()); // Title
					fileTodo.write(line.getBytes()); // ----- ~~
				}
				fileTodo.close(); // 파일 닫기
			} catch (FileNotFoundException e) {
				System.out.println("[파일을 찾을 수 없습니다]");
				System.out.println(e);
			} catch (Exception e) {
				System.out.println("[예외 오류 발생]");
				System.out.println(e);
			} finally {
				System.out.println("코드 실행 끝");
			}
		}
			
			
			
	}
}
