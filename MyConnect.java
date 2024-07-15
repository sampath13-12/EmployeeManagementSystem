package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnect {
	static String url = "jdbc:mysql://localhost:3306/jdbc_2024";
	static String username = "root";
	static String password = "Kkross80@";
	static Connection con = null;
		
	public static Connection connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");			
		con  = DriverManager.getConnection(url,username,password);
		return con;
	}
}
