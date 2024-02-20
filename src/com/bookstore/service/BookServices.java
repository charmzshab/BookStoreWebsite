package com.bookstore.service;


import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {

	private EntityManager entityManager;
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.entityManager = entityManager;
		bookDAO = new BookDAO(entityManager);
		categoryDAO = new CategoryDAO(entityManager);
		this.request = request;
		this.response = response;
	}

	public void listBook() throws ServletException, IOException {
		listBook(null);
	}

	public void listBook(String message) throws ServletException, IOException {
		List<Book> listBook = bookDAO.listAll();
		request.setAttribute("listBook", listBook);
		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	
	public void showBookNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();

		request.setAttribute("listCategory", listCategory);
		String newPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
		
	}

	public void editBook() {
		
		
	}

	
//	public void editCategory() throws ServletException, IOException {
//	int categoryId = Integer.parseInt(request.getParameter("id"));
//	
//	Category category = bookDAO.get(categoryId);
//	String editPage = "category_form.jsp";
//	String categoryName = category.getName();
//	categoryName = categoryName.replace(" ", "&nbsp;");
//	request.setAttribute("categoryName", categoryName);
//	request.setAttribute("category", category);
//	RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
//	
//	requestDispatcher.forward(request, response);
//	
//}	
//	public void createCategory() throws ServletException, IOException {
//		
//		String categoryName = request.getParameter("categoryName");
//		Category existCategory = bookDAO.findByCategoryName(categoryName);
//
//		if (existCategory != null) {
//			String message = "Could not create category. A category with name: " + categoryName + " already exists";
//			request.setAttribute("message", message);
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
//			requestDispatcher.forward(request, response);
//
//		} else {
//			Category category = new Category(categoryName);
//			bookDAO.create(category);
//			listCategory("A new category created successfully");
//		}
//	}
//	

//
//	public void updateCategory() throws ServletException, IOException {
//		
//		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
//		String categoryName = request.getParameter("categoryName");
//		
//		Category categoryById = bookDAO.get(categoryId);
//		Category categoryByName = bookDAO.findByCategoryName(categoryName);
//		
//		if(categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
//			String message = "Could not update category. Category with name "+ categoryByName.getName() + " already exists.";
//			request.setAttribute("message", message);
//			
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
//			requestDispatcher.forward(request,response);
//		}
//		else {
//			categoryById.setName(categoryName);
//			bookDAO.update(categoryById);
//			String message = "Category has been updated successfully";
//			listCategory(message);
//		}
//		
//	}
//
//	public void deleteCategory() throws ServletException, IOException {
//		int categoryId = Integer.parseInt(request.getParameter("id"));
//		bookDAO.delete(categoryId);
//		
//		String message = "Category has been deleted successfully";
//		listCategory(message);
//	}

}