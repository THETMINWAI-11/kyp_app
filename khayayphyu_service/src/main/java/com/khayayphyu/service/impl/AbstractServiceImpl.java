package com.khayayphyu.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.SearchRequest;
import com.khayayphyu.utils.CommonUtils;

@Component
@Transactional(readOnly = true)
public abstract class AbstractServiceImpl<T extends AbstractEntity>	implements AbstractService<T> {
	
	@Autowired
	private GenericDao genericDao;
	
	public Stream<T> toStream(Iterable<T> list) {
		return StreamSupport.stream(list.spliterator(), false);
	}

	@Transactional(readOnly = false)
	public void save(T d) {
		d = saveWithReturn(d);
	}

	@Transactional(readOnly = false)
	public T saveWithReturn(T d) {
		return getDao().save(d);
	}

	@Override
	@Transactional(readOnly = false)
	public Optional<String> saveWithValidation(T entity) {
		save(entity);
		return Optional.empty();
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		getDao().deleteById(id);
	}

	@Transactional(readOnly = false)
	public void save(T d, Consumer<T> consumer) {
		consumer.accept(d);
		save(d);
	}

	@Transactional(readOnly = false)
	public void update(T entity) {
		if (entity == null || entity.getId() == null)
			return;
		genericDao.update(entity);
	}

	public T get(long id) {
		return getDao().findById(id).get();
	}

	public List<T> getAll() {
		return CommonUtils.toList(getDao().findAll());
	}

	public <D> List<D> getAll(Function<T, D> convertor) {
		if (convertor == null)
			return Collections.emptyList();
		
		Iterable<T> entityList = getDao().findAll();
		return toStream(entityList).map(convertor).collect(Collectors.toList());
	}

	public List<T> getAll(Comparator<T> sorter) {
		List<T> entityList = getAll();
		entityList.sort(sorter);
		return entityList;
	}

	protected abstract CrudRepository<T, Long> getDao();

	public abstract Class<T> getTargetClass();

	public List<T> search(SearchRequest<T> searchRequest) {
		List<T> entityList = genericDao.search(searchRequest::generateQuery, getTargetClass());

		return entityList.stream().filter(searchRequest::filter).collect(Collectors.toList());
	}

	public List<T> search(SearchRequest<T> searchRequest, Predicate<T> filter) {
		List<T> entityList = genericDao.search(searchRequest::generateQuery, getTargetClass());
		return entityList.stream().filter(filter).collect(Collectors.toList());
	}
}
