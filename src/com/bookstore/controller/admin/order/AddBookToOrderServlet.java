package com.bookstore.controller.admin.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBookToOrderServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));

		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);

		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");

		float subTotal = quantity * book.getPrice();

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(book);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubtotal(subTotal);

		float newTotal = order.getTotal() + subTotal;
		order.setTotal(newTotal);
		order.getOrderDetails().add(orderDetail);
		request.setAttribute("book", book);
		session.setAttribute("NewBookPendingToAddToOrder", true);

		String resultPage = "add_book_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);

	}

}
