package com.stackcodie.studentmanagenet.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stackcodie.studentmanagenet.dao.StudentDao;
import com.stackcodie.studentmanagenet.model.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private StudentDao studentDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        this.studentDao = new StudentDao();
    }

    public void init() {
    	studentDao = new StudentDao();
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
		switch (action) {
		case "/new":
			showNewForm(request, response);
		break;
		case "/insert":
			insertStudent(request, response);
		break;
		case "/delete":
			deleteStudent(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			updateStudent(request, response);
			break;
			default:
				listStudent(request, response);
				break;

		}
		}catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listStudent(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException{
		List<Student> listStudent = studentDao.selectAllStudent();
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		Student existingStudent = studentDao.selectStudent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-edit.jsp");
		request.setAttribute("student", existingStudent);
		dispatcher.forward(request, response);
	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response)
	throws SQLException,IOException{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String standard = request.getParameter("standard");
		String gender = request.getParameter("gender");
		Student newStudent = new Student(name, email, standard, gender);
		studentDao.insertStudent(newStudent);
		response.sendRedirect("list");
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	   
	    int id = Integer.parseInt(request.getParameter("id"));
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String standard = request.getParameter("standard");
	    String gender = request.getParameter("gender");

		Student student = new Student(id, name, email, standard, gender);
	    studentDao.updateStudent(student);
	    response.sendRedirect("list");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		studentDao.deleteStudent(id);
		response.sendRedirect("list");
	}
}


