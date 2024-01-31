package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
		// user1.setPassword("shabix20024"); error is thrown because password is
		// required

		Assertions.assertThrows(PersistenceException.class, () -> {
			userDAO.create(user1);
		});
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("shabix@amigos.net");
		user.setFullName("Shabix Lampard");
		user.setPassword("mySecret");

		user = userDAO.update(user);
		String expected = "mySecret";
		String result = user.getPassword();

		assertEquals(expected, result);

	}

	@Test
	public void testGetUsersFound() {
		Integer userId = 19;
		Users user = userDAO.get(userId);
		assertNotNull(user);

	}
	
	@Test
	public void testUsersNotFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	

	@Test
	public void testDeleteUsers() {
		Integer userId = 19;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		
		assertNull(user);

	}
	
	@Test
	public void testDeleteNonExistUser() {
		Integer userId = 19;
		
		assertThrows(Exception.class, () -> {
			userDAO.delete(userId);
		});
	}
	
	
	@Test
	public void testListAllUsers() {
		
		List<Users> users = userDAO.listAll();
		
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		long expected = 4;
		
		assertEquals(expected, totalUsers);
	}

	@AfterAll
	public static void tearDownAll() {// Close EntityManager
		entityManager.close();
		entityManagerFactory.close();
	}

}