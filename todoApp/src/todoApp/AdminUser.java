package todoApp;

import java.io.*;
import java.util.*;
import java.time.*;

public class AdminUser {
	ArrayList<User> userList = new ArrayList<User>();
	
	public void addUserList() {
		System.out.println("유저이름 입력");
		Scanner inputName = new Scanner(System.in);
		String name = inputName.next();

		System.out.println("유저나이 입력");
		int age;
		while (true) { // age가 int인지 확인
			try {
				Scanner inputAge = new Scanner(System.in);
				age = inputAge.nextInt();
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[나이를 다시 입력해주세요.]");
				continue;
			}
		}
		
		System.out.println("유저성별 입력");
		Scanner inputGender = new Scanner(System.in);
		String gender = inputGender.next();	
		User user = new User(name, age, gender);
		
		userList.add(user);
		System.out.println("유저 등록 완료");
		user.introduce();
	}
	
	
	
	public User cheeckUserId() {
		System.out.println("유저 아이디 입력해주세요");
		int userId;
		while (true) { // userId가 int인지 확인
			try {
				Scanner inputId = new Scanner(System.in);
				userId = inputId.nextInt();
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[아이디를 다시 입력해주세요.]");
				continue;
			}
		}
		
		User user = null;
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserId() == userId) {
				user = userList.get(i);
			}
		}
		return user;
	}
}
