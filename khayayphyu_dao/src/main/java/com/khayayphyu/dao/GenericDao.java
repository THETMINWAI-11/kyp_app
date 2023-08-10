package com.khayayphyu.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.AbstractEntity;


@Transactional(readOnly = true)
@Repository
public interface GenericDao {

	public <T> List<T> search(Supplier<String> querySupplier, Class<T> target);
	
	public <T> List<T> searchWithNativeQuery(Supplier<String> querySupplier, Class<T> target);
	
	public <T extends AbstractEntity> void update(T t);
	
}