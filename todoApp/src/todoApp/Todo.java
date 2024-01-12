package todoApp;

public class Todo extends mainTodo{
	
	private String title;
	private Boolean isDone;
	private int priority;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "Todo [title=" + title + ", isDone=" + isDone + ", priority=" + priority + "]";
	}
	
	
	
	
}
