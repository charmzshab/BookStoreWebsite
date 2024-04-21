package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public class CategoryDAOTest {

	private static CategoryDAO categoryDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDao = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		categoryDao.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Serevb Ten");
		Category newCategory = categoryDao.create(newCat);
		assertTrue(newCategory != null && newCategory.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category c = new Category("---------vlad");
		c.setCategoryId(24);
		Category updateCategory = categoryDao.update(c);
		assertEquals(c.getName(), updateCategory.getName());
	}

	@Test
	public void testGetCategory() {
		Integer userID = 16;
		Category category = categoryDao.get(userID);
		assertNotNull(category);

	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll();
		listCategory.forEach(c -> System.out.println(c.getName()));
		assertTrue(listCategory.size() > 0);

	}

	@Test
	public void testCountCategory() {
		long count = categoryDao.count();
		assertEquals(18, count);

	}

	@Test
	public void testDeleteCategory() {
		Integer categoryID = 9;
		categoryDao.delete(categoryID);
		Category category = categoryDao.get(categoryID);
		assertFalse(category != null);

	}

}
