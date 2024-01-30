package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class JpaDAO<E> {
	protected EntityManager entityManager; // injected by the client code such as the DAO classes

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public E create(E entity) {
		entityManager.getTransaction().begin();

		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);

		entityManager.getTransaction().commit();

		return entity;
	}

	public E update(E entity) {
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();

		return entity;
	}

	public E find(Class<E> type, Object id) {
		E entity = entityManager.find(type, id);
		if (entity != null) {
			entityManager.refresh(entity);
		}

		return entity;
	}
	
	public void delete(Class<E> type, Object id) {
		entityManager.getTransaction().begin();
		
		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);
		
		entityManager.getTransaction().commit();
		
		
	}
}
