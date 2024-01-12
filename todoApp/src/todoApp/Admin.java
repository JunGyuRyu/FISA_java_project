package todoApp;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Admin {
	ArrayList<User> userList = new ArrayList<User>();
	
	public void addUserList() {
		System.out.println("유저이름 입력");
		Scanner inputName = new Scanner(System.in);
		String name = inputName.next();

		System.out.println("유저나이 입력");
		Scanner inputAge = new Scanner(System.in);
		int age = inputAge.nextInt();
		
		System.out.println("유저성별 입력");
		Scanner inputGender = new Scanner(System.in);
		String gender = inputGender.next();	
		User user = new User(name, age, gender);
		
		userList.add(user);
		System.out.println("유저 등록 완료");
		user.introduce();
	}
	
	
	
	public User cheeckUserId() {
		System.out.println("유저 아이디 입력해주세요");
		Scanner inputId = new Scanner(System.in);
		int userId = inputId.nextInt();
		User user = null;
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserId() == userId) {
				user = userList.get(i);
			}
		}
		return user;
	}
	
	public void enrollTodo(User user) {
		System.out.println("안녕하세요 " + user.getName() + "님"+
				"\n할일 등록 시작하겠습니다.");
		
		System.out.println("할일 제목 입력");
		Scanner inputTitle = new Scanner(System.in);
		String title = inputTitle.next();
		
		System.out.println("할일 우선순위 입력");
		Scanner inputPriority = new Scanner(System.in);
		int priority = inputPriority.nextInt();
		
		System.out.println("할일완료날짜(YYYY-MM-dd) 입력");
		Scanner inputEndDate = new Scanner(System.in);
		String endDate = inputEndDate.next();
		
		LocalDate date =  LocalDate.parse(endDate);
		
		user.addTodo(title, priority, date);
		System.out.println("할일 등록 완료");
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
		Admin.todoToText(user);
		
		System.out.println();
		System.out.println(user.getTodoList());
		System.out.println(user.getDoneList());
		System.out.println("txt 파일 작성 완료.");
	}
	
	public void delTodo(User user) {
		System.out.println("삭제할 할일 제목을 입력해 주세요");
		Scanner inputDeleteTitle = new Scanner(System.in);
		String DeleteTitle = inputDeleteTitle.next();
		user.deleteTodo(DeleteTitle);
		System.out.println("삭제완료");
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
			Todo todo = todoList.get(i);
			File file = new File("src/todoApp/" + user.getName() + "/todoList.txt");
			String user_info = (String) "userName: " + user.getName() + "| userAge: " + user.getAge() 
			+ "| userGender: " + user.getGender() + "\n\n";
			
			
			// 파일에 입력한 값 작성
			try {
				FileOutputStream fileTodo = new FileOutputStream(file);
				fileTodo.write(user_info.getBytes());
				fileTodo.write(line.getBytes()); // ----- ~~
				for (Todo todos: user.getTodoList()) {
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
				for (Todo todos: user.getTodoList()) {
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
