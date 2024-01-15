package todoApp;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
	private String name;
	private static int no = 0;
	private int userId;
	private int age;
	private String gender;
	private ArrayList<Todo> todoList = new ArrayList<Todo>();
	private ArrayList<Todo> doneList = new ArrayList<Todo>();
	
	User() { // cheeckUserId에서 user = null 할 때 필요
		
	}
		
	 // test
	User(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		no++;
		this.userId = no;
	}
	public int getUserId() {
		return this.userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	void addTodo(String title, int priority, LocalDate date) {
		Todo td = new Todo(title, priority, date);
		this.todoList.add(td);
	}
	
	
	void deleteTodo(String title) { // 단순 삭제
		int done_flag = 0;
		for (int i = 0; i < this.todoList.size(); i++) {
			if (title.equals(this.todoList.get(i).getTitle()) ) {
				System.out.println(this.todoList.get(i) + " 삭제완료.");
				this.todoList.remove(i);
				done_flag = 1;
			}
		}
		if (done_flag == 0) {
			System.out.println("해당 Todo가 존재하지 않습니다.");
		}
	}
	
	
	void doneTodo(String title) { // doneList에 추가 후 삭제
		int done_flag = 0;
		for (int i = 0; i < this.todoList.size(); i++) {
			if (title.equals(this.todoList.get(i).getTitle()) ) {
				this.doneList.add(this.todoList.get(i));
				this.todoList.get(i).setIsDone(true);
				System.out.println(this.todoList.get(i) + " 완료!");
				this.todoList.remove(i);
				done_flag = 1;
			}			
		}
		if (done_flag == 0) {
			System.out.println("해당 Todo가 존재하지 않습니다.");
		}
	}
	  
	ArrayList<Todo> getDoneList() {
		return this.doneList;
	}
	
	ArrayList<Todo> getTodoList() {
		return this.todoList;
	}
	
	
	public void introduce() {
		System.out.println("-----개인정보-----");
		System.out.println("- 이름 : " + this.name);
		System.out.println("- 나이 : " + this.age);
		System.out.println("- 성별 : " + this.gender);
		System.out.println("- 아이디 : " + this.userId);
	}
	

	public void showTodoList() {
		System.out.println("[TodoList]");
		if (this.todoList.size() > 0) {
			for(int i = 0; i < this.todoList.size(); i++) {
				LocalDate endDate = this.todoList.get(i).getEndDate();
				System.out.println((i+1) + "." + this.todoList.get(i).getTitle() + " 완료 여부: " 
						+ this.todoList.get(i).getIsDone()
						+ " | 완료 날짜: " + endDate
						+ " | 남은 기간: " + this.todoList.get(i).checkDeadLine(endDate)
						+ " | 우선 순위: " + this.todoList.get(i).getPriority() + "순위");
			}
		} else {
			System.out.println("작성된 Todo가 없습니다.");
		}
	}
	
	public void showDoneList() {
		System.out.println("[DoneList]");
		if (this.doneList.size() > 0){
			for(int i = 0; i < this.doneList.size(); i++) {
				LocalDate endDate = this.doneList.get(i).getEndDate();
				System.out.println((i+1) + "." + this.doneList.get(i).getTitle() + " 완료 여부: " 
						+ this.doneList.get(i).getIsDone()
						+ " | 완료 날짜: " + endDate);
			}
		} else {
			System.out.println("완료된 Todo가 없습니다.");
		}
	}
}
