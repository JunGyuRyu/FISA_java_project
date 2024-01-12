package todoApp;

public class mainTodo {
	public static void main(String[] argrs) {
		
		Todo todo1 = new Todo();
		todo1.setIsDone(false);
		todo1.setPriority(1);
		todo1.setTitle("할일1");
		System.out.println(todo1.getIsDone());
		System.out.println(todo1.getPriority());
		System.out.println(todo1.getTitle());
		System.out.println(todo1);
		
		
		
		
	}
}
