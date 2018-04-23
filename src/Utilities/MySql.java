package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql extends MainUtilities {
	
	public static String[] getEmailPassword(String username) throws ClassNotFoundException, SQLException {
		String arr[] = new String[2];
		String email = null;
		String pass = null;

		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://18.221.206.225:3306/automation", "root" , "IdAnRuBiN");
		loggerMySql.info("DB connection success");
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery("select email , password from automation.user where username ='"+username+"';");
		while (resultSet.next()) {
			email = (resultSet.getString(1));
			pass = (resultSet.getString(2));
		}
		
		arr[0] = email;
		arr[1] = pass;
		
		conn.close();
		loggerMySql.info("return email and password from DB success");
		return arr;	
	}
}

