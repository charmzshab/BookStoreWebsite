package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

class CategoryDAOTest{
	private static CategoryDAO categoryDAO;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		categoryDAO = new CategoryDAO();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// Close EntityManagerFacto
		categoryDAO.close();
	}

	@Test
	void testCreateCategory() {
		Category newCat = new Category("Javascript");
		Category category = categoryDAO.create(newCat);

		assertTrue(category.getCategoryId() > 0);
	}

	@Test
	void testUpdateCategory() {
		Category newCat = new Category("Core Java");
		newCat.setCategoryId(11);

		Category category = categoryDAO.update(newCat);

		assertEquals(newCat.getName(), category.getName());

	}

	@Test
	void testGet() {
		Integer catId = 13;
		Category category = categoryDAO.get(catId);
		
		assertNotNull(category);
	}

	@Test
	void testDeleteObject() {
		Integer catId = 13;
		
		categoryDAO.delete(catId);

		Category category = categoryDAO.get(catId);

		assertNull(category);

		
	}

	@Test
	void testListAll() {
		List<Category> categories = categoryDAO.listAll();
		
		categories.forEach(cat -> System.out.println(cat.getName()));
		
		assertTrue(categories.size() > 0);
	}

	@Test
	void testCount() {
		long totalCategories = categoryDAO.count();
		long expected = 2;
		
		assertEquals(expected,totalCategories);
		
	}
	

	@Test
	void testFindByName() {
		String name = "Javascript";
		Category category = categoryDAO.findByCategoryName(name);
		
		assertNotNull(category);
		
	}

}
