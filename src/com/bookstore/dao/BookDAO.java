package com.bookstore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Users;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
		super();

	}

	public List<Book> listByCategory(int categryId) {
		return super.findWithNamedQuery("Book.findByCategory", "catId", categryId);
	}

	public List<Book> listNewBook() {
		return super.findWithNamedQuery("Book.listNew", 0, 4);
	}

	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);

	}

	public List<Book> listBestSellingBook() {
		List<Book> findWithNamedQuery = super.findWithNamedQuery("OrderDetail.bestSelling", 0, 4);
		return null;
	}
	
	public List<Book> listMostFavoredBooks() {
		List<Book> mostFavoredBooks = new ArrayList<>();
		
		List<Object[]> result = super.findWithNamedQueryObjects("Review.mostFavoredBooks", 0, 4);
		
		if (!result.isEmpty()) {
			for (Object[] elements : result) {
				Book book = (Book) elements[0];
				mostFavoredBooks.add(book);
			}
		} 
		
		return mostFavoredBooks;
	}

	public Book update(Book book) {
		Date lastUpdateTime = new Date();
		book.setLastUpdateTime(lastUpdateTime);
		return super.update(book);

	}

	@Override
	public Book get(Object bookId) {
		return super.find(Book.class, bookId);
	}

	@Override
	public void delete(Object bookid) {
		super.delete(Book.class, bookid);

	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");

	}

	public Book findByTitle(String title) {
		List<Book> result = findWithNamedQuery("Book.findByTitle", "title", title);
		if (!result.isEmpty()) {
			Book book = result.get(0);
			return book;
		} else {
			return null;
		}

	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}

	public List<Book> search(String keyword) {
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
	}

	public long countByCategory(int categoryID) {
		return super.countWithNamedQuery("Book.countByCategory", "catId", categoryID);
	}

}