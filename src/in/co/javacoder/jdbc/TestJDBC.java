package in.co.javacoder.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
	
	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimeZone=UTC";
		String user = "hbstudent";
		String password = "hbstudent";
		
		Connection mySqlConnection = null;
		try {
			System.out.println("Connecting to MYSQL server ...");
			mySqlConnection = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("Connection Successful !");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(mySqlConnection != null)
				try {
					mySqlConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

}
