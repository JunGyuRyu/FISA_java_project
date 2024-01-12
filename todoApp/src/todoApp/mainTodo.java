package todoApp;

public class mainTodo {
	public static void main(String[] argrs) {
		System.out.println("Hi");
		
		Todo a = new Todo();
		a.setIsDone(false);
		a.setPriority(1);
		a.setTitle("할일1");
		System.out.println("ho");
		System.out.println(a.getPriority());
		System.out.println(a.getPriority());
		System.out.println(a.getPriority());
		System.out.println(a.toString());
	}
}
