package todoApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainTodo {
	public static void main(String[] argrs) {
		ArrayList<User> userList = new ArrayList<User>();
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("1번 유저 등록, 2번 할일 등록, 3번 할일 삭제, 4번 할일 목록보기, 0번 종료");
			int mode = scan.nextInt();
			if(mode == 1) {
				System.out.println("유저이름 입력");
				Scanner inputName = new Scanner(System.in);
				String name = inputName.next();

				
				System.out.println("유저나이 입력");
				Scanner inputAge = new Scanner(System.in);
				int age = inputAge.nextInt();
			
				
				System.out.println("유저성별 입력");
				Scanner inputGender = new Scanner(System.in);
				String gender = inputGender.next();
				
				
				User user = new User(name, age, gender);
				userList.add(user);
				System.out.println("유저 등록 완료");
				user.introduce();
			}else if(mode == 2) {
				System.out.println("유저 아이디 입력해주세요");
				Scanner inputId = new Scanner(System.in);
				int userId = inputId.nextInt();
				for(int i = 0; i < userList.size(); i++) {
					if(userList.get(i).getUserId() == userId) {
						System.out.println("안녕하세요 " + userList.get(i).getName() + "님"+
					"\n할일 등록 시작하겠습니다.");
						
						System.out.println("할일 제목 입력");
						Scanner inputTitle = new Scanner(System.in);
						String title = inputTitle.next();
						
						
						System.out.println("할일 우선순위 입력");
						Scanner inputPriority = new Scanner(System.in);
						int priority = inputPriority.nextInt();
						
						
						System.out.println("할일완료날짜(YYYY-MM-dd) 입력");
						Scanner inputEndDate = new Scanner(System.in);
						String endDate = inputEndDate.next();
//						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
						LocalDate date =  LocalDate.parse(endDate);
						
						userList.get(i).addTodo(title, priority, date);
						System.out.println("할일 등록 완료");
					}
				}
			}else if(mode == 3) {
				System.out.println("유저 아이디 입력해주세요");
				Scanner inputId = new Scanner(System.in);
				
				for(int i = 0; i < userList.size(); i++) {
					int userId = inputId.nextInt();
					if(userList.get(i).getUserId() == userId) {
						System.out.println("삭제할 할일 제목을 입력해 주세요");
						Scanner inputDeleteTitle = new Scanner(System.in);
						String DeleteTitle = inputDeleteTitle.next();
						userList.get(i).deleteTodo(DeleteTitle);
						System.out.println("삭제완료");
						break;
					}
				}
			}
			else if(mode == 4) {
				System.out.println("유저 아이디 입력해주세요");
				Scanner inputId = new Scanner(System.in);
				int userId = inputId.nextInt();
				for(int i = 0; i < userList.size(); i++) {
					if(userList.get(i).getUserId() == userId) {
						System.out.println(userList.get(i).getName() + "님");
						userList.get(i).showTodoList();
						System.out.println();
						System.out.println("완료한 리스트");
						System.out.println(userList.get(i).doneList());
					}
				}
			}
			else if(mode == 0) {
				break;
			}
			
			
		}
		

	}
}

