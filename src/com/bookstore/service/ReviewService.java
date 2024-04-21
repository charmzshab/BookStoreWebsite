package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewService {
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ReviewService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.reviewDAO = new ReviewDAO();
	}

	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}

	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();
		request.setAttribute("listReviews", listReviews);
		if (message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "review_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);

	}

	public void editReview() throws ServletException, IOException {
		Integer reviewID = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewID);
		request.setAttribute("review", review);

		String editPage = "review_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);

	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewID = Integer.parseInt(request.getParameter("reviewId"));

		String hedline = request.getParameter("headline");
		String commnent = request.getParameter("comment");

		Review review = reviewDAO.get(reviewID);
		review.setHeadline(hedline);
		review.setComment(commnent);
		reviewDAO.update(review);
		String message = "The review has been updated succesefully";
		listAllReview(message);

	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewID = Integer.parseInt(request.getParameter("id"));
		reviewDAO.delete(reviewID);
		String message = "Review deleted succesefully";
		listAllReview(message);

	}

	public void showReviewForm() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);

		HttpSession session = request.getSession();
		session.setAttribute("book", book);

		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		Review existReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
		String targetPage = "frontend/review_form.jsp";
		if (existReview != null) {
			request.setAttribute("review", existReview);
			targetPage = "frontend/review_info.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);

	}

	public void submitReview() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String headLine = request.getParameter("headline");
		String comment = request.getParameter("comment");
		Review newReview = new Review();
		newReview.setRating(rating);
		newReview.setHeadline(headLine);
		newReview.setComment(comment);

		Book book = new Book();
		book.setBookId(bookId);

		newReview.setBook(book);

		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");

		newReview.setCustomer(customer);

		reviewDAO.create(newReview);

		String mesagePage = "frontend/review_done.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(mesagePage);
		dispatcher.forward(request, response);

	}
}
