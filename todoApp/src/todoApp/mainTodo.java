package todoApp;

import java.util.ArrayList;


public class mainTodo {
	public static void main(String[] argrs) {
		
		User user01 = new User();
		user01.setAge(10);
		user01.setGender("m");
		user01.setName("user01");
		user01.addTodo("할일1", 1);
		System.out.println(user01.getAge());
		System.out.println(user01.getGender());
		System.out.println(user01.getName());
		
		User user02 = new User("user02", 12, "f");
		System.out.println(user02.getAge());
		System.out.println(user02.getGender());
		System.out.println(user02.getName());
		
		user02.addTodo("todo1", 1);
		System.out.println(user02.remainList());
		System.out.println(user02.doneList());
		
		
		user02.addTodo("todo2", 1);
		System.out.println(user02.remainList());
		System.out.println(user02.doneList());
		
		user02.addTodo("todo3", 1);
		System.out.println(user02.remainList());
		System.out.println(user02.doneList());
		
		user02.deleteTodo("todo2");
		System.out.println(user02.remainList());
		System.out.println(user02.doneList());
		
		
	}
}
