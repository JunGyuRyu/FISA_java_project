package todoApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainTodo {
	public static void main(String[] argrs) {
		ArrayList<User> userList = new ArrayList<User>();
		
		while(true) {
			System.out.println("1번 유저 등록, 2번 할일 등록, 0번 종료");
			Scanner scan = new Scanner(System.in);
			if(scan.nextInt() == 1) {
				System.out.println("유저이름 입력");
				Scanner inputName = new Scanner(System.in);
				String name = inputName.next();
				inputName.close();
				
				System.out.println("유저나이 입력");
				Scanner inputAge = new Scanner(System.in);
				int age = inputAge.nextInt();
				inputAge.close();
				
				System.out.println("유저성별 입력");
				Scanner inputGender = new Scanner(System.in);
				String gender = inputGender.next();
				inputGender.close();
				
				User user = new User(name, age, gender);
				userList.add(user);
				System.out.println("유저 등록 완료");
				user.introduce();
			}else if(scan.nextInt() == 2) {
				System.out.println("유저 아이디 입력해주세요");
				Scanner inputId = new Scanner(System.in);
				
				for(int i = 0; i < userList.size(); i++) {
					if(userList.get(i).getUserId() == inputId.nextInt()) {
						
					}
				}
			}else if(scan.nextInt() == 0) {
				
			}
			
			
			
		}
		
//		Todo todo1 = new Todo("todo1", 1, LocalDate.of(2025, 7, 26));
//		System.out.println(todo1.getTitle());
//		System.out.println(todo1.getPriority());
//		System.out.println(todo1.getIsDone());
//		System.out.println(todo1);
//		todo1.setTitle("123123");
//		System.out.println(todo1.getTitle());
		String str = scan.nextLine();
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
	    LocalDate.parse(str, dtf);
		User user01 = new User();
		user01.deadLine("");
//		System.out.println(user01);
		
	}
}

