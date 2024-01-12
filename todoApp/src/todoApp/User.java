package todoApp;

import java.util.ArrayList;

public class User {
	private String name;
	private int age;
	private String gender;
	private ArrayList<Todo> todoList;
	private ArrayList<Todo> doneList;
	
	User(){
		
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
	public ArrayList<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(ArrayList<Todo> todoList) {
		this.todoList = todoList;
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
	
	void introduce() {
		System.out.println("-----개인정보-----");
		System.out.println("이름 : " + this.name);
		System.out.println("나이 : " + this.age);
		System.out.println("성별 : " + this.gender);
	}
}
