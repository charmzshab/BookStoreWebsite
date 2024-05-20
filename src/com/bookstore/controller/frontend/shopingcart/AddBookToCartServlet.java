package com.bookstore.controller.frontend.shopingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Object ShopingCart = null;

	public AddBookToCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer bookID = Integer.parseInt(request.getParameter("book_id"));
		Object cartObject = request.getSession().getAttribute("cart");
		ShoppingCart shopiingCarty = null;
		if (cartObject != null && cartObject instanceof ShoppingCart) {
			shopiingCarty = (ShoppingCart) cartObject;
		} else {
			shopiingCarty = new ShoppingCart();
			request.getSession().setAttribute("cart", shopiingCarty);
		}
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookID);
		shopiingCarty.addItem(book);

		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
