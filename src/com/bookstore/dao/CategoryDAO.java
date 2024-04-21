package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {

	}

	@Override
	public Category create(Category category) {
		return super.create(category);
	}

	@Override
	public Category update(Category c) {
		return super.update(c);
	}

	@Override
	public Category get(Object id) {
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);

	}
	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}

	public Category findByName(String nameCategory) {
		List<Category> list = super.findWithNamedQuery("Category.findByName", "name", nameCategory);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
}
