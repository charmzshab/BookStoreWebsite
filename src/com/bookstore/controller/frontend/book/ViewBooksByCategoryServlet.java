package com.bookstore.controller.frontend.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookService;


@WebServlet("/view_category") 
public class ViewBooksByCategoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
   
    public ViewBooksByCategoryServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService = new BookService( request, response);
		bookService.listBookByCategory();
			
	}

}
