package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Category;

public class CategoryService {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.categoryDAO = new CategoryDAO();
	}

	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category categoryByEmail = categoryDAO.findByName(name);
		if (categoryByEmail != null) {
			String failCreationCategory = "Could not create caterogry. A category" + "with email " + name
					+ " already exists";
			request.setAttribute("someUserMessage", failCreationCategory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Category newCategory = new Category(name);
			categoryDAO.create(newCategory);
			String mess = "Category added succesufuly";
			listCategory(mess);
		}

	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("lisCategory", listCategory);
		if (message != null) {
			request.setAttribute("someUserMessage", message);
		}
		request.getRequestDispatcher("category_list.jsp").forward(request, response);
	}

	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		String destPage = "category_form.jsp";
		if (category != null) {
			request.setAttribute("category", category);
		} else {
			String message = "Could not find category with  ID " + categoryId;
			request.setAttribute("message", message);
			destPage = "message.jsp";
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);

	}

	public void updateCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("name");
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(name);

		if (categoryByName != null && categoryByName.getCategoryId() != categoryById.getCategoryId()) {
			String mess = " Could not update category due to duplicates names";
			request.setAttribute("someUserMessage", mess);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			categoryById.setName(name);
			categoryDAO.update(categoryById);
			listCategory("Category Updated Succesefuly");

		}

	}

	public void deleteCategory() throws ServletException, IOException {
		int catId = Integer.parseInt(request.getParameter("id"));
		BookDAO bookDAO = new BookDAO();
		long noOfBooks = bookDAO.countByCategory(catId);

		String message = null;
		if (noOfBooks > 0) {
			message = "Could not delete category ( " + categoryDAO.get(catId).getName()
					+ ") because it currentluy  has books associted  with ";
			message = String.format(message, catId);

		} else {
			categoryDAO.delete(catId);
			message = "Category with Id " + catId + " has been deleted successfully";

		}
		listCategory(message);

	}

}
