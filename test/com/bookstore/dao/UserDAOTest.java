package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {

	private static UserDAO userDAO;

	@BeforeClass
	public static void setupClass() throws Exception {
		userDAO = new UserDAO();

	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("john@gmail.com");
		user1.setFullName("John Smith");
		user1.setPassword("asdk!88)99");
		user1 = userDAO.create(user1);
		assertTrue(user1.getUserId() > 0);

	}

	@Test
	public void checkAdminLogin() {
		String email = "vite@mail.ru";
		String password = "asda";
		boolean loginResult = userDAO.checkLogin(email, password);
		assertTrue(loginResult);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);

	}

	@Test
	public void testUpdateUser() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("my@email.net");
		user.setFullName("Vitalie");
		user.setPassword("abracadabra");
		user = userDAO.update(user);
		String exepected = "abracadabra";
		// String actual = "helloworld";
		assertEquals(exepected, user.getPassword());

	}

	@Test
	public void testGetUsersFound() {
		Integer userID = 1;
		Users user = userDAO.get(userID);
		if (user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}

	@Test
	public void testGetUserNotFound() {
		Integer userID = 100;
		Users user = userDAO.get(userID);
		assertNull(user);
	}

	@Test
	public void testDeleteUser() {
		Integer userID = 5;
		userDAO.delete(userID);
		Users user = userDAO.get(userID);
		assertNull(user);

	}

	@Test(expected = Exception.class)
	public void testeDeleteNonExistUser() {
		Integer userID = 57;
		userDAO.delete(userID);

	}

	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		for (Users users : listUsers) {
			System.out.println(users.getEmail());
		}
		assertTrue(listUsers.size() > 0);

	}

	@Test
	public void testCount() {
		long count = userDAO.count();
		assertEquals(7, count);

	}

	@Test
	public void testFindByEmail() {
		String email = "maria@mail.ru";
		Users user = userDAO.findByEmail(email);
		assertNotNull(user);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {

	}

}
