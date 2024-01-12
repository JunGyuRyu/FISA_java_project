package todoApp;

import java.time.LocalDate;
import java.time.Period;

public class Todo extends MainTodo{
	
	private String title;
	private Boolean isDone;
	private int priority;
	private LocalDate endDate;
	
	Todo(){
		
	}
	
	Todo(String title, int priority, LocalDate endDate) {
		this.title = title;
		this.isDone = false;
		this.priority = priority;
		this.endDate = endDate;
	}
	
	
	public String getTitle() {
		return title;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public int getPriority() {
		return priority;
	}
	
	public void setEndDate(LocalDate date) {
		this.endDate = date;
	}
	
	public LocalDate getEndDate() {
		return this.endDate;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String checkDeadLine(LocalDate endDate) {
		
		LocalDate date = LocalDate.now(); // 현재 날짜 	
		
		try {
			Period per = Period.between(date, endDate);
			String deadLine = per.getYears()+"년 "+per.getMonths()+"개월 " + per.getDays() + "일 남았습니다.";
			return deadLine;
		} catch (Exception e) {
			// TODO: handle exception
			return ("완료 시점이 존재하지 않습니다.");
		}
		
	}
	
	// toString 실행 시 객체가 아니라 들어있는 값 출력
	@Override
	public String toString() {
		return "Todo [title=" + title + ", isDone=" + isDone + ", priority=" + priority + "]";
		
	}
}
