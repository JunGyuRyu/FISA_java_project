package todoApp;

import java.io.*;
import java.util.*;
import java.time.*;
 
public class AdminTodo {
	ArrayList<User> userList = new ArrayList<User>();
	
	public void enrollTodo(User user) {
		System.out.println("안녕하세요 " + user.getName() + "님"+
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
				user.addTodo(title, priority, date);
				System.out.println("할일 등록 완료");
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[날짜를 YYYY-MM-dd 형식으로 다시 입력해주세요.]");
				continue;
			}
		}
	}
	public void printTodoList(User user) {
		System.out.println(user.getName() + "님");
		user.showTodoList();
		System.out.println();
		user.showDoneList();
	}
	
	public void saveTodoList(User user) {
		System.out.println(user.getName() + "님");
		user.showTodoList();
		user.showDoneList();
		System.out.println();
		
		AdminTodo.todoToText(user);
		System.out.println(user.getName() + "의 txt 파일 작성 완료..");
	}
	
	public void delTodo(User user) {
		System.out.println("삭제할 Todo 제목을 입력해 주세요");
		Scanner inputDeleteTitle = new Scanner(System.in);
		String deleteTitle = inputDeleteTitle.next();
		user.deleteTodo(deleteTitle);
	}
	
	public void doneTodo(User user) {
		System.out.println("완료할 Todo 제목을 입력해 주세요");
		Scanner inputDoneTitle = new Scanner(System.in);
		String doneTitle = inputDoneTitle.next();
		user.doneTodo(doneTitle);
	}
	
	public static void todoToText(User user) {
		File folder = new File("src/todoApp/" + user.getName());
		// 폴더 있으면 false 반환, 없으면 생성
		boolean directoryCreated = folder.mkdir();
		System.out.println("User 생성 여부: "+ directoryCreated);
		ArrayList<Todo> doneList = user.getDoneList();
		ArrayList<Todo> todoList = user.getTodoList();
		String line = "----------------------------------------------------------------\n";
		
		// todoList 폴더에 todo.txt 파일 생성
		for (int i = 0; i < todoList.size(); i++) {
			File file = new File("src/todoApp/" + user.getName() + "/todoList.txt");
			String user_info = (String) "[사용자: " + user.getName() + " | 나이: " + user.getAge() 
			+ " | 성별: " + user.getGender() + "]\n\n";
			
			// 파일에 입력한 값 작성
			try {
				FileOutputStream fileTodo = new FileOutputStream(file);
				fileTodo.write(user_info.getBytes());
				fileTodo.write(line.getBytes()); // ----- ~~
				for (Todo todos: user.getTodoList()) {
					fileTodo.write((todos.getEndDate().toString() + "\n").getBytes()); // Date
					fileTodo.write(("우선순위: " + todos.getPriority() + "순위\n").getBytes()); // Priority
					fileTodo.write((todos.getTitle().toString() + "\n").getBytes()); // Title
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
		
		
		// doneList 폴더에 todo.txt 파일 생성
		for (int i = 0; i < doneList.size(); i++) {
			File file = new File("src/todoApp/" + user.getName() + "/doneList.txt");
			String user_info = (String) "[사용자: " + user.getName() + " | 나이: " + user.getAge() 
			+ " | 성별: " + user.getGender() + "]\n\n";
			
			
			// 파일에 입력한 값 작성
			try {
				FileOutputStream fileTodo = new FileOutputStream(file);
				fileTodo.write(user_info.getBytes());
				fileTodo.write(line.getBytes()); // ----- ~~
				for (Todo todos: user.getDoneList()) {
					fileTodo.write((todos.getEndDate().toString() + "\n").getBytes()); // Date
					fileTodo.write(("우선순위: "+todos.getPriority()+"순위\n").getBytes()); // Priority
					fileTodo.write((todos.getTitle().toString() + "\n").getBytes()); // Title
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
}
