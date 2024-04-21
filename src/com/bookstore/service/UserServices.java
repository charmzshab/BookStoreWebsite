package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.lang.model.element.Element;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.UDecoder;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserDAO userDAO;

	public UserServices( HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.userDAO = new UserDAO();
	}

	public void listUsers() throws ServletException, IOException {
		listUsers(null);
	}

	public void listUsers(String mess) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
		if (mess != null) {
			request.setAttribute("someUserMessage", mess);
		}
		request.setAttribute("listUsers", listUsers);
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);

	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		Users existedUserFromDb = userDAO.findByEmail(email);
		if (existedUserFromDb != null) {
			String failCreationUserMess = "Could not create user." + " A user" + "with email " + email
					+ " already exists";
			request.setAttribute("someUserMessage", failCreationUserMess);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Users user = new Users(email, fullName, password);
			userDAO.create(user);
			listUsers("New user created succesufuly");
		}
	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);
		request.setAttribute("user", user);
		String editPage = "user_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);

	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");

		Users userByEmail = userDAO.findByEmail(email);
		Users userById = userDAO.get(userId);

		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String mess = " Could not update user , user's email already exists in database";
			request.setAttribute("someUserMessage", mess);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);

		} else {
			Users user = new Users(userId, email, fullName, password);
			userDAO.update(user);
			String mes = "User has been updated succesufully";
			listUsers(mes);
		}

	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);
		String message = "User has been deleted successfuly";
		if (user == null) {
			message = "Could not find user with user Id " + userId;
			request.setAttribute("someUserMessage", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);

		} else {
			userDAO.delete(userId);
			listUsers(message);
		}

	}

	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean loginResult = userDAO.checkLogin(email, password);
		if (loginResult) {
			request.getSession().setAttribute("useremail", email);
			request.getRequestDispatcher("/admin/").forward(request, response);
		} else {
			String message = "Login failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
