package com.stackcodie.studentmanagenet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stackcodie.studentmanagenet.model.Student;

public class StudentDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/studentmanagement_db?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_STUDENT_SQL = "INSERT INTO student " + "  (name, email, standard, gender) VALUES " + " (?, ?, ?, ?);";
	private static final String SELECT_STUDENT_BY_ID = "select id,name,email,standard,gender from student where id =?";
	private static final String SELECT_ALL_STUDENT = "select * from student";
	private static final String DELETE_STUDENT_SQL = "delete from student where id =?;";
	private static final String UPDATE_STUDENT_SQL = "update student set name =?,email = ?, standard =?, gender =? where id = ?;";

	public StudentDao() {
	}

	protected Connection getConnection() {

		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

	public void insertStudent(Student student) throws SQLException {
		System.out.println(INSERT_STUDENT_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getStandard());
			preparedStatement.setString(4, student.getGender());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Student selectStudent(int id) {
		Student student = null;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String standard = rs.getString("standard");
				String gender = rs.getString("gender");

				student = new Student(id,name, email, standard, gender);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	public List<Student> selectAllStudent() {
		List<Student> students = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT)) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String standard = rs.getString("standard");
				String gender = rs.getString("gender");

				students.add(new Student(id, name, email, standard, gender));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
	}

	public boolean updateStudent(Student student) throws SQLException {
	   boolean rowUpdated;
	    try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
	        statement.setString(1, student.getName());
	        statement.setString(2, student.getEmail());
	        statement.setString(3, student.getStandard());
	        statement.setString(4, student.getGender());
	        statement.setInt(5, student.getId());
	        
	        rowUpdated = statement.executeUpdate() > 0;
	        
	    }
	    return rowUpdated;
	}

	public boolean deleteStudent(int id) throws SQLException{
		boolean rowDeleted;
		try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		
		return rowDeleted;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException)e).getSQLState()); 
				System.err.println("Error code: " +((SQLException)e).getSQLState());
				System.err.println("Message: " + e.getMessage());
				
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: " +t);
					t = t.getCause();
				}
			}
		}
	}
}
