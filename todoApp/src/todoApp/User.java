package todoApp;

import java.util.ArrayList;

public class User {
	private String name;
	private int age;
	private String gender;
	private ArrayList<Todo> todoList;
	private ArrayList<Todo> doneList;
	
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
		todoList.add(t1);
	}
	
	void deleteTodo(String title) {
		for (int i = 0 ; i < todoList.size() ; i ++) {
			if (title == todoList.get(i).getTitle()) {
				doneList.add(todoList.get(i));
				todoList.remove(i);
				
			}
		}
		
	}
	  
//	ArrayList<Todo> doneList() {
//		
//	}
//	
//	ArrayList<Todo> remainList() {
//		
//	}
	
	void introduce() {
		System.out.println("-----개인정보-----");
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("성별 : " + gender);
	}
}
