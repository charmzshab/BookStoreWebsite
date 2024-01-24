package com.bookstore.dao;

import javax.persistence.EntityManager;

public class JpaDAO<T> {
	protected EntityManager entityManager; // injected by the client code such as the DAO classes

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public T create(T t) {
		entityManager.getTransaction().begin();

		entityManager.persist(t);
		entityManager.flush();
		entityManager.refresh(t);

		entityManager.getTransaction().commit();

		return t;
	}

}
