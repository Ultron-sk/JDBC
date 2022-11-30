package com.assignment4;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CRUD {

	public static void main(String[] args) {
	
		Connection connection = null;
		ResultSet resultSet = null;
	PreparedStatement ps=null;
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
				switch (userChoiceMain) {
				case 1: {
					insertQuery = "Insert into student values(?,?,?,?);";
					ps = connection.prepareStatement(insertQuery);
					if (ps != null) {
						sc = new Scanner(System.in);
						System.out.println("Please provide the required details to create the query");
						System.out.println("Enter the Stud_Id :");
						s_id = sc.nextInt();
						ps.setInt(1, s_id);
						System.out.println("Enter the Stud_Name :");
						Name = sc.next();
						ps.setString(2, Name);
						System.out.println("Enter the Course_Id :");
						c_id = sc.nextInt();
						ps.setInt(3, c_id);
						System.out.println("Enter the Stud_Age :");
						Age = sc.nextInt();
						ps.setInt(4, Age);

						int rowCount = ps.executeUpdate();
						if (rowCount != 0) {
					
							System.out.println("No of rows affected :: " + rowCount);
							JdbcUtilps.closeConnections(resultSet, ps, connection);
						} else
							System.out.println("No rows affected");

					}
					break;
				}
		
				case 2: {
					Scanner sc1=new Scanner(System.in);
					String selectQuery = "Select s_id,Name,Age from student where c_id=?;";
					ps = connection.prepareStatement(selectQuery);
					if (ps != null) {
						System.out.println("Enter the course id whose details you want to view: ");
						c_id=sc1.nextInt();
						ps.setInt(1, c_id);
						
						resultSet = ps.executeQuery();
						if (resultSet != null) {
							System.out.println("S_Id\t\tName\t\tAge");
							System.out.println("-----------------------------------------------------");
							while (resultSet.next()) {
								Integer stud_id = resultSet.getInt(1);
								String stud_Name = resultSet.getString(2);
								Integer stud_Age = resultSet.getInt(3); 
								System.out.println(stud_id + "\t\t" + stud_Name + "\t\t" + stud_Age);
							}
						}
					}
					break;
				}
				case 3: {
					
					if (ps == null) {
						Scanner sc2 = new Scanner(System.in);
						System.out.println("Please provide the required details to create the query");
						System.out.println(
								"Which field do you want to update?\r\n 1.Name\t2.c_id\t3.Age\t4.All the 3 fileds");
						int updateOption = sc2.nextInt();
						if (updateOption == 1) {
							updateQuery = "Update student set Name =? where s_id=?;";
							ps = connection.prepareStatement(updateQuery);
							System.out.println("Enter the new Stud_Name :");
							Name = sc2.next();
							ps.setString(1,Name);
							System.out.println("Enter the Stud_id whose name you want to  change : ");
							s_id = sc2.nextInt();
							ps.setInt(2,s_id);
								
						}
						if (updateOption == 2) {
							Scanner sc3 = new Scanner(System.in);
							updateQuery = "Update student set c_id=? where s_id=?";
							ps = connection.prepareStatement(updateQuery);
							System.out.println("Enter the new Course_Id :");
							c_id = sc3.nextInt();
							ps.setInt(1, c_id);
							System.out.println("Enter the Stud_id whose CourseId you want to  change : ");
							s_id = sc3.nextInt();
							ps.setInt(2, s_id);
						}
						if (updateOption == 3) {
							Scanner sc4 = new Scanner(System.in);
							updateQuery = "Update student set Age=? where s_id=?";
							ps = connection.prepareStatement(updateQuery);
							System.out.println("Enter the new Stud_Age :");
							Age = sc4.nextInt();
							ps.setInt(1, Age);
							System.out.println("Enter the Stud_id whose age you want to  change : ");
							s_id = sc4.nextInt();
							ps.setInt(2, s_id);
							
						}
						if (updateOption == 4) {
							Scanner sc5 = new Scanner(System.in);
							updateQuery ="Update student set Name=?, c_id=? ,Age=? where s_id=?";
							ps = connection.prepareStatement(updateQuery);
							System.out.println("Enter the new Stud_Name :");
							Name = sc5.next();
							ps.setString(1, Name);
							System.out.println("Enter the new Course_Id :");
							c_id = sc5.nextInt();
							ps.setInt(2, c_id);
							System.out.println("Enter the new Stud_Age :");
							Age = sc5.nextInt();
							ps.setInt(3, Age);
							System.out.println("Enter the Stud_id whose details you want to  change : ");
							s_id = sc5.nextInt();
							ps.setInt(4, s_id);
							
						}

						int rowCount = ps.executeUpdate();
						if (rowCount != 0) {
							System.out.println("No of rows affected :: " + rowCount);
							JdbcUtilps.closeConnections(resultSet, ps, connection);
						} else
							System.out.println("No recrods affected ");

					}
					break;
				}
				case 4: {
					String deleteQuery = "Delete from student where s_id=?";
					ps = connection.prepareStatement(deleteQuery);
					if (ps != null) {
						Scanner sc6 = new Scanner(System.in);
						System.out.println("Please provide the required details to create the query");
						System.out.println("Enter the Stud_Id whose fields you want to delete:");
						s_id = sc6.nextInt();
						ps.setInt(1, s_id);
						int rowCount = ps.executeUpdate();

						if (rowCount != 0) {
							// Getting the output for insert operation
							System.out.println("No of rows affected :: " + rowCount);
							JdbcUtilps.closeConnections(resultSet, ps, connection);
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