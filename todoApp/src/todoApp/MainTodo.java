package todoApp;

import java.util.Scanner;

public class MainTodo {
	public static void main(String[] argrs) {
		
		Admin admin = new Admin();
		while(true) {
			System.out.println("----------------------------\n"
					+ "1번 유저 등록 \n"
					+ "2번 할 일 등록 \n"
					+ "3번 할 일 삭제 \n"
					+ "4번 할 일 완료 \n"
					+ "5번 할 일 목록 보기 \n"
					+ "6번 할 일 목록 저장 \n"
					+ "0번 종료 \n"
					+ "----------------------------");
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
				admin.addUserList();
			}
			else if(mode == 2) {
				admin.enrollTodo(admin.cheeckUserId());
			}
			else if(mode == 3) {
				admin.delTodo(admin.cheeckUserId());
			}
			else if(mode == 4) {
				admin.doneTodo(admin.cheeckUserId());
			}
			else if(mode == 5) {
				admin.printTodoList(admin.cheeckUserId());
			} 
			else if(mode == 6) {
				admin.saveTodoList(admin.cheeckUserId());
			}
			else if(mode == 0) {
				System.out.println("[Todo 프로그램 종료]");
				break;
			}
		}
		
	}
}

