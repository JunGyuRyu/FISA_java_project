package todoApp;

public class ConnectionInfo {
	public static String getUrl() {
		String url = "jdbc:mysql://localhost:3306/java_todo?characterEncoding=UTF-8&serverTimezone=UTC";
		return url;
	}
	public static String getId() {
		String id = "root";
		return id;
	}
	public static String getPw() {
		String pw = "0000";
		return pw;
	}
}
