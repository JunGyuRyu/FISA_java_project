package todoApp;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User {
	private String name;
	private static int no = 0;
	private int userId;
	private int age;
	private String gender;
	private ArrayList<Todo> todoList = new ArrayList<Todo>();
	private ArrayList<Todo> doneList = new ArrayList<Todo>();
	
	User(){
		
	}
	
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
		Todo t1 = new Todo(title, priority, date);
		this.todoList.add(t1);
	}
	
	void deleteTodo(String title) {
	
		for (int i = 0 ; i < this.todoList.size() ; i ++) {
			
			if (title.equals(this.todoList.get(i).getTitle()) ) {
				
				this.doneList.add(this.todoList.get(i));
				this.todoList.remove(i);
				
			}
		}
		
	}
	  
	ArrayList<Todo> doneList() {
		return this.doneList;
	}
	
	ArrayList<Todo> remainList() {
		return this.todoList;
	}
	
	
	public void introduce() {
		System.out.println("-----개인정보-----");
		System.out.println("이름 : " + this.name);
		System.out.println("나이 : " + this.age);
		System.out.println("성별 : " + this.gender);
		System.out.println("아이디 : " + this.userId);
	}
	

	public void showTodoList() {
		System.out.println("TodoList");
		for(int i = 0 ; i < todoList.size() ; i++) {
			LocalDate endDate = todoList.get(i).getEndDate();
			System.out.println((i+1) + "." + todoList.get(i).getTitle() + " 완료 여부 : " 
					+ todoList.get(i).getIsDone()
					+ " 완료 날짜 :" + endDate
					+ " 남은 기간 :" + todoList.get(i).checkDeadLine(endDate));
		}
		
	}
}
