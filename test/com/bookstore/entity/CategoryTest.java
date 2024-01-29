package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CategoryTest {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction et = null;

		try {
			// Get transaction and start
			et = entityManager.getTransaction();
			et.begin();

			Category newCat = new Category("Core JAva");
		

			entityManager.persist(newCat);
			et.commit();
			System.out.println("A Category object was persisted.");
			
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
