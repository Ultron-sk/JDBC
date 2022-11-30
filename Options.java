package com.assignment4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
							
public class Options {

	public static void main(String[] args) {
		
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		Scanner sc = null;
		int s_id = 0;
		String Name = null;
		int c_id = 0;
		int Age = 0;
		int userChoiceMain = 0;
		String insertQuery = null;
		String updateQuery = null;

		userChoiceMain = JdbcUtilps.getUserInputForSelectQuery();

		try {
			connection = JdbcUtilps.getConnection();
			if (connection != null) {
				statement = connection.createStatement();
				switch (userChoiceMain) {
				case 1: {
					if (statement != null) {
						sc = new Scanner(System.in);
						System.out.println("Please provide the required details to create the query");
						System.out.println("Enter the Stud_Id :");
						s_id = sc.nextInt();
						System.out.println("Enter the Stud_Name :");
						Name = sc.next();
						System.out.println("Enter the Course_Id :");
						c_id = sc.nextInt();
						System.out.println("Enter the Stud_Age :");
						Age = sc.nextInt();

						insertQuery = String.format("Insert into student values('%d','%s','%d','%d')", s_id, Name, c_id,
								Age);
						int rowCount = statement.executeUpdate(insertQuery);
						if (rowCount != 0) {
							
							System.out.println("No of rows affected :: " + rowCount);
							JdbcUtilps.closeConnections(resultSet, statement, connection);
						} else
							System.out.println("No rows affected");

					}
					break;
				}
				case 2: {

					if (statement != null) {
						String selectQuery = "Select s_id,Name,c_id,Age from student;";
						resultSet = statement.executeQuery(selectQuery);
						if (resultSet != null) {
							System.out.println("S_Id\t\tName\t\tC_Id\t\tAge");
							System.out.println("-----------------------------------------------------");
							while (resultSet.next()) {
								Integer stud_id = resultSet.getInt(1);
								String stud_Name = resultSet.getString(2);
								Integer course_id = resultSet.getInt(3);
								Integer stud_Age = resultSet.getInt(4);
								System.out
										.println(stud_id + "\t\t" + stud_Name + "\t\t" + course_id + "\t\t" + stud_Age);
							}
						}
					}
					break;
				}
		
				case 3: {
					if (statement != null) {
						sc = new Scanner(System.in);
						System.out.println("Please provide the required details to create the query");
						System.out.println(
								"Which field do you want to update?\r\n 1.Name\t2.c_id\t3.Age\t4.All the 3 fileds");
						int updateOption = sc.nextInt();
						if (updateOption == 1) {
							System.out.println("Enter the Stud_id whose name you want to  change : ");
							s_id = sc.nextInt();
							System.out.println("Enter the new Stud_Name :");
							Name = sc.next();
							Name = "'" + Name + "'";
							updateQuery = "Update student set Name =" + Name + " where s_Id=" + s_id + ";";
						}
						if (updateOption == 2) {
							System.out.println("Enter the Stud_id whose CourseId you want to  change : ");
							s_id = sc.nextInt();
							System.out.println("Enter the new Course_Id :");
							c_id = sc.nextInt();
							insertQuery = String.format("Update student set c_id='%d' where s_id='%d'", c_id, s_id);
						}
						if (updateOption == 3) {
							System.out.println("Enter the Stud_id whose age you want to  change : ");
							s_id = sc.nextInt();
							System.out.println("Enter the new Stud_Age :");
							Age = sc.nextInt();
							updateQuery = String.format("Update student set Age='%d' where s_id='%d'", Age, s_id);
						}
						if (updateOption == 4) {
							System.out.println("Enter the Stud_id whose details you want to  change : ");
							s_id = sc.nextInt();
							System.out.println("Enter the new Stud_Name :");
							Name = sc.next();
							System.out.println("Enter the new Course_Id :");
							c_id = sc.nextInt();
							System.out.println("Enter the new Stud_Age :");
							Age = sc.nextInt();
							updateQuery = String.format(
									"Update student set Name='%s', c_id='%d' ,Age='%d' where s_id='%d'", Name, c_id,
									Age, s_id);
						}
						int rowCount = statement.executeUpdate(updateQuery);
						if (rowCount != 0) {
							System.out.println("No of rows affected :: " + rowCount);
							JdbcUtilps.closeConnections(resultSet, statement, connection);
						} else
							System.out.println("No recrods affected ");

					}
					break;
				}
				case 4: {
					if (statement != null) {
						sc = new Scanner(System.in);
						System.out.println("Please provide the required details to create the query");
						System.out.println("Enter the Stud_Id whose fields you want to delete:");
						s_id = sc.nextInt();
						String deleteQuery = String.format("Delete from student where s_id='%d'", s_id);

						int rowCount = statement.executeUpdate(deleteQuery);

						if (rowCount != 0) {
							System.out.println("No of rows affected :: " + rowCount);
							JdbcUtilps.closeConnections(resultSet, statement, connection);
						} else
							System.out.println("No recrods affected ");

					}
					break;
				}

				}

			}

		} catch (SQLException se) {
			
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

}
