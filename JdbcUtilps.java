package com.assignment4;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcUtilps {

	public static Connection getConnection() throws SQLException {


		
		 String url="jdbc:mysql://localhost:3306/studentdatabase";
		String user = "root";
		String password = "Mysql@9902";
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;

	}

	public static int getUserInputForSelectQuery () {
		System.out.println("In this application i have used Prepared Statement\n");
		System.out.println("\t\t\tChoose the operation listed below on student table\r\n"
				+ "\t1. Create \t\t2. Read \t\t3. Update \t\t4. Delete");
		Scanner sc=new Scanner(System.in);
		System.out.println();
		int userChoice=sc.nextInt();
		return userChoice;
	}
	public static void getUserInputForInsertQuery() {
		int sid=3;
	}
	
	
	public static void closeConnections(ResultSet resultSet, Statement statement,Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException se) {
		
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
