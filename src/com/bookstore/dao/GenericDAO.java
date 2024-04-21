package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public interface GenericDAO<T> {

	public T create(T t);

	public T update(T t);

	public T get(Object id);

	public void delete(Object id);

	

	public long count();

	List<T> listAll();

}
