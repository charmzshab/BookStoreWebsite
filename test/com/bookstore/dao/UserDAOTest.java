package com.bookstore.dao;

import static org.junit.Assert.assertTrue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Users;

class UserDAOTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;

//	@BeforeClass
//	public static void setupClass() {
//		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
//		entityManager = entityManagerFactory.createEntityManager();
//		userDAO = new UserDAO(entityManager);
//	}

	@BeforeAll
	public static void setupAll() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO = new UserDAO(entityManager);
	}

	@Test
	void testCreateUsers() {

		Users user1 = new Users();
		user1.setEmail("trump@gmail.com");
		user1.setFullName("Michelle Obama");
		user1.setPassword("shabix20024");

		user1 = userDAO.create(user1);
	
		assertTrue(user1.getUserId() > 0);
	}

	@Test
	public void testCreateUserFieldsNotSet() {
		Users user1 = new Users();
		user1.setEmail("jackie@gmail.com");
		user1.setFullName("JAckie Chan");
		// user1.setPassword("shabix20024");

		Assertions.assertThrows(PersistenceException.class, () -> {
			userDAO.create(user1);
		});

	}
	
    @AfterAll
    public static void tearDownAll() {// Close EntityManager
		entityManager.close();
		entityManagerFactory.close();
    }

}