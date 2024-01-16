package todoApp;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
=======
import java.time.LocalDate;
import java.util.ArrayList;
>>>>>>> branch 'main' of https://github.com/JunGyuRyu/FISA_java_project

public class User {
<<<<<<< HEAD
    
    public static void userInfo(int userId) {
        String sql = "SELECT name, age, gender FROM UserTable WHERE userId = ?";

        try (Connection conn = AdminUser.getConnection(); // AdminUser 클래스에서 연결 가져오기
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");

            System.out.println("[사용자: " + name + " | 나이: " + age + " | 성별: " + gender + "]\n\n");
            
            } else {
                 System.out.println( "해당 ID의 사용자가 없습니다.");
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
        
    }
    
    
    public static String getName(int userId) {
        String sql = "SELECT name FROM UserTable WHERE userId = ?";
        String name = "";
        try (Connection conn = AdminUser.getConnection(); // AdminUser 클래스에서 연결 가져오기
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
            
            } else {
            	System.out.println("해당 이름의 사용자가 없습니다.");

            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return e.toString();
        }
=======
	private String name;
	private static int no = 0;
	private int userId;
	private int age;
	private String gender;
	private ArrayList<Todo> todoList = new ArrayList<Todo>();
	private ArrayList<Todo> doneList = new ArrayList<Todo>();
	 
	User() { // cheeckUserId에서 user = null 할 때 필요
		
	}
		 
	 
	User(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		no++;
		this.userId = no;
	}
	public int getUserId() {
		return this.userId;
	}
	public String getName() {
>>>>>>> branch 'main' of https://github.com/JunGyuRyu/FISA_java_project
		return name;
<<<<<<< HEAD
        
    }
    
    
    public static void setAge(int userId) {
    	System.out.println(User.getName(userId)+ "님의 바꿀 나이를 입력해 주세요");
    	int newAge;
    	while (true) { // newAge가 int인지 확인
			try {
				Scanner setAge = new Scanner(System.in);
				newAge = setAge.nextInt();
				break;
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[나이를 다시 입력해주세요.]");
				continue;
			}
		}
    	
        String sql = "UPDATE UserTable SET age = ? WHERE userId = ?";
        try (Connection conn = AdminUser.getConnection(); // AdminUser 클래스에서 연결 가져오기
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newAge); // 첫 번째 물음표에 newAge 설정
            pstmt.setInt(2, userId); // 두 번째 물음표에 userId 설정

            int affectedRows = pstmt.executeUpdate(); // 쿼리 실행, 영향 받은 행의 수 반환

            if (affectedRows > 0) {
                System.out.println("[사용자 나이 업데이트 성공]");
            } else {
                System.out.println("해당 ID의 사용자가 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        }
    }
    
    
    public static void setName(int userId) {
    	System.out.println(User.getName(userId)+ "님의 바꿀 이름을 입력해 주세요");
    	String newName;
    	Scanner setName = new Scanner(System.in);
    	newName = setName.next();
    	
        String sql = "UPDATE UserTable SET name = ? WHERE userId = ?";
        try (Connection conn = AdminUser.getConnection(); // AdminUser 클래스에서 연결 가져오기
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName); // 첫 번째 물음표에 newAge 설정
            pstmt.setInt(2, userId); // 두 번째 물음표에 userId 설정

            int affectedRows = pstmt.executeUpdate(); // 쿼리 실행, 영향 받은 행의 수 반환

            if (affectedRows > 0) {
                System.out.println("[사용자 이름 업데이트 성공]");
            } else {
                System.out.println("해당 이름의 사용자가 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        }
    }
    

    public static void setGender(int userId) {
    	System.out.println(User.getName(userId)+ "님의 바꿀 성별을 입력해 주세요");
    	String newGender;
    	Scanner setGender = new Scanner(System.in);
    	newGender = setGender.next();

        String sql = "UPDATE UserTable SET gender = ? WHERE userId = ?";
        try (Connection conn = AdminUser.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newGender);
            pstmt.setInt(2, userId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("[사용자 성별 업데이트 성공]");
            } else {
                System.out.println("해당 ID의 사용자가 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        }
    }
}
=======
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	void addTodo(String title, int priority, LocalDate date) {
		Todo td = new Todo(title, priority, date);
		this.todoList.add(td);
	}
	
	
	void deleteTodo(String title) { // 단순 삭제
		int done_flag = 0;
		for (int i = 0; i < this.todoList.size(); i++) {
			if (title.equals(this.todoList.get(i).getTitle()) ) {
				System.out.println(this.todoList.get(i) + " 삭제완료.");
				this.todoList.remove(i);
				done_flag = 1;
			}
		}
		if (done_flag == 0) {
			System.out.println("해당 Todo가 존재하지 않습니다.");
		}
	}
	
	
	void doneTodo(String title) { // doneList에 추가 후 삭제
		int done_flag = 0;
		for (int i = 0; i < this.todoList.size(); i++) {
			if (title.equals(this.todoList.get(i).getTitle()) ) {
				this.doneList.add(this.todoList.get(i));
				this.todoList.get(i).setIsDone(true);
				System.out.println(this.todoList.get(i) + " 완료!");
				this.todoList.remove(i);
				done_flag = 1;
			}			
		}
		if (done_flag == 0) {
			System.out.println("해당 Todo가 존재하지 않습니다.");
		}
	}
	  
	ArrayList<Todo> getDoneList() {
		return this.doneList;
	}
	
	ArrayList<Todo> getTodoList() {
		return this.todoList;
	}
	
	
	public void introduce() {
		System.out.println("-----개인정보-----");
		System.out.println("- 이름 : " + this.name);
		System.out.println("- 나이 : " + this.age);
		System.out.println("- 성별 : " + this.gender);
		System.out.println("- 아이디 : " + this.userId);
	}
	

	public void showTodoList() {
		System.out.println("[TodoList]");
		if (this.todoList.size() > 0) {
			for(int i = 0; i < this.todoList.size(); i++) {
				LocalDate endDate = this.todoList.get(i).getEndDate();
				System.out.println((i+1) + "." + this.todoList.get(i).getTitle() + " 완료 여부: " 
						+ this.todoList.get(i).getIsDone()
						+ " | 완료 날짜: " + endDate
						+ " | 남은 기간: " + this.todoList.get(i).checkDeadLine(endDate)
						+ " | 우선 순위: " + this.todoList.get(i).getPriority() + "순위");
			}
		} else {
			System.out.println("작성된 Todo가 없습니다.");
		}
	}
	
	public void showDoneList() {
		System.out.println("[DoneList]");
		if (this.doneList.size() > 0){
			for(int i = 0; i < this.doneList.size(); i++) {
				LocalDate endDate = this.doneList.get(i).getEndDate();
				System.out.println((i+1) + "." + this.doneList.get(i).getTitle() + " 완료 여부: " 
						+ this.doneList.get(i).getIsDone()
						+ " | 완료 날짜: " + endDate);
			}
		} else {
			System.out.println("완료된 Todo가 없습니다.");
		}
	}
}
>>>>>>> branch 'main' of https://github.com/JunGyuRyu/FISA_java_project
