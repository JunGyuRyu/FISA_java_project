package todoApp;

import java.sql.SQLException;
import java.util.Scanner;

public class MainTodo {
	public static void main(String[] argrs) throws SQLException {
		
		boolean tableFlag = true;
		while(true) {
			System.out.println("----------------------------\n"
					+ "1번 유저 등록 \n"
					+ "2번 할 일 등록 \n"
					+ "3번 할 일 삭제 \n"
					+ "4번 할 일 완료 \n"
					+ "5번 할 일 목록 보기 \n"
					+ "6번 할 일 목록 저장 \n"
					+ "7번 사용자 나이 변경 \n"
					+ "8번 사용자 이름 변경 \n"
					+ "9번 사용자 성별 변경 \n"
					+ "0번 종료 \n"
					+ "----------------------------");
			if(tableFlag) {
				AdminUser.createUserTable();
				AdminTodo.createTodoTable();
				tableFlag = false;
			}
			Scanner scan = new Scanner(System.in);
			int mode; // int 자료형만 받기
			try {
				mode = scan.nextInt();
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[mode를 다시 입력해주세요.]");
				continue;
			}
			
			if(mode == 1) {
				AdminUser.addUser();
			}
			else if(mode == 2) {
				AdminTodo.enrollTodo(AdminUser.checkUserId());
			}
			else if(mode == 3) {
				AdminTodo.delTodo(AdminUser.checkUserId());
			}
			else if(mode == 4) {
				AdminTodo.doneTodo(AdminUser.checkUserId());
			}
			else if(mode == 5) {
				AdminTodo.printTodoList(AdminUser.checkUserId());
			} 
			else if(mode == 6) {
				AdminTodo.saveTodoList(AdminUser.checkUserId());
//				AdminTodo.todoToImg(AdminUser.checkUserId());
			}
			else if(mode == 7) {
				User.setAge(AdminUser.checkUserId());
			}
			else if(mode == 8) {
				User.setName(AdminUser.checkUserId());
			}
			else if(mode == 9) {
				User.setGender(AdminUser.checkUserId());
			}
			else if(mode == 0) {
				System.out.println("[Todo 프로그램 종료]");
				break;
			}
		}
		
	}
}

