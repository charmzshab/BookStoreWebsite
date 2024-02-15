package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseDAOTest {
	protected static EntityManagerFactory entityManagerFactory;
	protected static EntityManager entityManager;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// Close EntityManager
		entityManager.close();
		entityManagerFactory.close();
	}

}
