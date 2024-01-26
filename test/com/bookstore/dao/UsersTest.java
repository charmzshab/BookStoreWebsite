package com.bookstore.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction et = null;

		try {
			// Get transaction and start
			et = entityManager.getTransaction();
			et.begin();

			Users user1 = new Users();
			user1.setEmail("charmz@gmail.com");
			user1.setFullName("Harouna Gabz");
			user1.setPassword("shabix20024");

			entityManager.persist(user1);
			et.commit();
			System.out.println("A Users object was persisted.");
			
		} catch (Exception e) {
			// If there is an exception rollback changes
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			// Close EntityManager
			entityManager.close();
			entityManagerFactory.close();
		}

		

	}

}
