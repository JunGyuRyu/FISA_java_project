package todoApp;

public class mainTodo {
	public static void main(String[] argrs) {
		
		
		Todo todo1 = new Todo("todo1", 1);
		System.out.println(todo1.getTitle());
		System.out.println(todo1.getPriority());
		System.out.println(todo1.getIsDone());
		System.out.println(todo1);
		todo1.setTitle("123123");
		System.out.println(todo1.getTitle());
		
		User user01 = new User();
		System.out.println(user01);
		
	}
}
