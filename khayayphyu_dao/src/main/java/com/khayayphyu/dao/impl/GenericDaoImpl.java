package com.khayayphyu.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.entity.AbstractEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class GenericDaoImpl implements GenericDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public <T> List<T> search(Supplier<String> querySupplier, Class<T> targetClass) {
		TypedQuery<T> query = entityManager.createQuery(querySupplier.get(), targetClass);
		List<T> list =  query.getResultList();
		return CollectionUtils.isNotEmpty(list) ? list : Collections.emptyList();
	}

	@Override
	public <T> List<T> searchWithNativeQuery(Supplier<String> querySupplier, Class<T> target) {
		Session session = entityManager.unwrap(Session.class);
		Query<T> query = session.createNativeQuery(querySupplier.get(), target); //entityManager.createNativeQuery(querySupplier.get());
		List<T> list =   query.list();
		return CollectionUtils.isNotEmpty(list) ? list : Collections.emptyList();
	}

	@Override
	public <T extends AbstractEntity> void update(T t) {
		entityManager.merge(t);
		
	}

}
