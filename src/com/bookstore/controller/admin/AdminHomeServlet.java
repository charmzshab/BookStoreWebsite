package com.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.ReverbType;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Review;

@WebServlet("/admin")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminHomeServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String homePage = "index.jsp";
		UserDAO userDAO = new UserDAO();
		BookDAO bookDAO = new BookDAO();
		CustomerDAO customerDAO = new CustomerDAO();
		OrderDAO orderDao = new OrderDAO();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		List<Review> listMostRecentReviews = reviewDAO.listMostRecent();
		List<BookOrder> listMostRecentSales = orderDao.listMostRecentSales();
		
		long totalUsers = userDAO.count();
		long totalBooks = bookDAO.count();
		long totalCustomers = customerDAO.count();
		long totalReviews = reviewDAO.count();
		long totalOrders = orderDao.count();
		request.setAttribute("listMostRecentSales", listMostRecentSales );
		request.setAttribute("listMostRecentReviews", listMostRecentReviews);
		
		request.setAttribute("totalUsers", totalUsers );
		request.setAttribute("totalBooks", totalBooks );
		request.setAttribute("totalReviews", totalReviews );
		request.setAttribute("totalCustomers", totalCustomers );
		request.setAttribute("totalOrders", totalOrders );
		RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
		dispatcher.forward(request, response);

	}

}
