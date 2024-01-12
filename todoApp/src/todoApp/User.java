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
	
	
	
	void addTodo(String title, int priority) {
		Todo t1 = new Todo();
		t1.setTitle(title);
		t1.setPriority(priority);
		this.todoList.add(t1);
	}
	
	void deleteTodo(String title) {
		for (int i = 0 ; i < this.todoList.size() ; i ++) {
			if (title == this.todoList.get(i).getTitle()) {
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
	void deadLine(String title) {
		
		LocalDate date = LocalDate.now(); // 현재 날짜 	
		LocalDate endDate = null;
		for (int i = 0 ; i < this.todoList.size() ; i ++) {
			if (title == this.todoList.get(i).getTitle()) {
				endDate = this.todoList.get(i).getEndDate();
			}
		}
		
//		LocalDate specialdate = LocalDate.of(2025, 7, 26);
//		System.out.println(date + "  " + endDate);
		try {
			Period per = Period.between(date, endDate);
			System.out.println("앞으로" + title + "완료시점은 " +
					per.getYears()+"년 "+per.getMonths()+"개월 " + per.getDays() + "일 남았습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("완료 시점이 존재하지 않습니다.");
		}
		
//		long diff = period.toDays();
//		System.out.println(diff);
	}
	
	public void introduce() {
		System.out.println("-----개인정보-----");
		System.out.println("이름 : " + this.name);
		System.out.println("나이 : " + this.age);
		System.out.println("성별 : " + this.gender);
		System.out.println("아이디 : " + this.userId);
	}
}
