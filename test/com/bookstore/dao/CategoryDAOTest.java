package com.bookstore.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Category;

class CategoryDAOTest extends BaseDAOTest {
	private static CategoryDAO categoryDAO;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO(entityManager);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// Close EntityManager
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	void testCreateCategory() {
		Category newCat = new Category("Javascript");
		Category category = categoryDAO.create(newCat);

		assertTrue(category.getCategoryId() > 0);
	}

	@Test
	void testUpdateCategory() {
		fail("Not yet implemented");
	}

	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	void testCount() {
		fail("Not yet implemented");
	}

}
