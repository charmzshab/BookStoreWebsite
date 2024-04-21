package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {
	private EntityManager entityManager;
	private BookDAO bookDAO;
	private HttpServletRequest request;
	private CategoryDAO categoryDAO;
	private HttpServletResponse response;

	public BookService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.bookDAO = new BookDAO();
		this.categoryDAO = new CategoryDAO();
	}

	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}

	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);
		if (message != null) {
			request.setAttribute("message", message);
		}
		request.getRequestDispatcher("book_list.jsp").forward(request, response);

	}

	public void showBookNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		request.getRequestDispatcher("book_form.jsp").forward(request, response);

	}

	public void readBookFields(Book book) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
		}

		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPublishDate(publishDate);
		book.setPrice(price);

		Part part = request.getPart("bookImage");

		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			book.setImage(imageBytes);
		}

	}

	public void createBook() throws ServletException, IOException {
		String title = request.getParameter("title");
		Book existBook = bookDAO.findByTitle(title);
		if (existBook != null) {
			String message = "Could not create book because the title  " + title + "is already in database";
			listBooks(message);
			return;

		}
		Book newBook = new Book();
		readBookFields(newBook);

		Book createdBook = bookDAO.create(newBook);
		if (createdBook.getBookId() > 0) {
			String message = "A new book has been created succesufly";
			listBooks(message);
		}

	}

	public void editBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		String destPage = "book_form.jsp";
		if (book != null) {
			List<Category> listCategory = categoryDAO.listAll();
			request.setAttribute("listCategory", listCategory);
			request.setAttribute("book", book);
		} else {
			destPage = "message.jsp";
			String message = "Could not find book with ID " + book.getTitle();
			request.setAttribute("message", message);

		}
		request.getRequestDispatcher(destPage).forward(request, response);

	}

	public void updateBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		Book existBook = bookDAO.get(bookId);
		Book bookByTitle = bookDAO.findByTitle(title);
		if (bookByTitle != null && !existBook.equals(bookByTitle)) {
			String mes = "Could not update book because there's  another book having the same title ";
			listBooks(mes);
			return;
		}
		readBookFields(existBook);
		bookDAO.update(existBook);
		String message = "The book has been updated successfully.";
		listBooks(message);

	}

	public void deleteBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);

		if (book == null) {
			String message = "Could not find book with ID " + bookId + ", or it might have been deleted";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			bookDAO.delete(bookId);
			String message = "The book has been deleted successfully.";
			listBooks(message);
		}

	}

	public void listBookByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		if (category == null) {
			String message = "Sorry, the category ID " + categoryId + " is not available.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("frontend/message.jsp").forward(request, response);
			return;
		}
	
		request.setAttribute("category", category);
		request.setAttribute("listBooks", listBooks);
		
		String listPage = "frontend/books_list_by_category.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void viewBookDetail() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		String destPage = "frontend/book_detail.jsp";
		if (book != null) {
			request.setAttribute("book", book);

		} else {
			destPage = "frontend/message.jsp";
			String message = "Sorry, the book with ID " + bookId + " is not available.";
			request.setAttribute("message", message);
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
	}

	public void search() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Book> result = null;
		if (keyword.equals("")) {
			result = bookDAO.listAll();
		} else {
			result = bookDAO.search(keyword);
		}
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", result);
		String resultsearchPage = "frontend/search_result.jsp";
		System.out.println(resultsearchPage);
		request.getRequestDispatcher(resultsearchPage).forward(request, response);

	}

}
