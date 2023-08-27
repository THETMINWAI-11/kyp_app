package com.khayayphyu.service.impl;

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
import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.SearchRequest;
import com.khayayphyu.utils.CommonUtils;

@Component
@Transactional(readOnly = true)
public abstract class AbstractServiceImpl<T extends AbstractEntity, D extends AbstractDto<T>>
		implements AbstractService<T, D> {
	
	@Autowired
	private GenericDao genericDao;

	public Stream<T> toStream(Iterable<T> list) {
		return StreamSupport.stream(list.spliterator(), false);
	}

	@Transactional(readOnly = false)
	public void save(D d) {
		saveWithReturn(d);
	}

	@Transactional(readOnly = false)
	public T saveWithReturn(D d) {
		T entity = d.toEntity();
		entity = getDao().save(entity);
		d.setId(entity.getId());
		return entity;
	}

	@Transactional(readOnly = false)
	public T save(T t) {
		return getDao().save(t);
	}

	@Override
	@Transactional(readOnly = false)
	public Optional<String> saveWithValidation(D dto) {
		save(dto);
		return Optional.empty();
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		getDao().deleteById(id);
	}

	@Transactional(readOnly = false)
	public void save(D d, Consumer<D> consumer) {
		consumer.accept(d);
		save(d);
	}

	@Transactional(readOnly = false)
	public void update(D d) {
		if (d == null || d.getId() == null)
			return;
		genericDao.update(d.toEntity());
	}

	public D get(long id) {
		return getDtoConvertor().apply(getDao().findById(id).get());
	}

	public List<D> getAll() {
		return toStream(getDao().findAll()).map(getDtoConvertor()).collect(Collectors.toList());
	}

	public List<D> getAll(Function<T, D> convertor) {
		if (convertor == null)
			convertor = getDtoConvertor();
		Iterable<T> entityList = getDao().findAll();
		return toStream(entityList).map(convertor).collect(Collectors.toList());
	}

	public List<D> getAll(Comparator<D> sorter) {
		List<D> entityList = getAll();
		entityList.sort(sorter);
		return entityList;
	}

	protected D toDto(T t) {
		return getDtoConvertor().apply(t);
	}

	protected D toDto(T t, Consumer<D> additionFunction) {
		D d = toDto(t);
		additionFunction.accept(d);
		return d;
	}

	protected List<D> toDtos(List<T> t) {
		return CommonUtils.mapToList(t, getDtoConvertor());
	}

	protected abstract CrudRepository<T, Long> getDao();

	public abstract Class<T> getTargetClass();

	public abstract Function<T, D> getDtoConvertor();

	public List<D> search(SearchRequest<T, D> searchRequest) {
		List<T> entityList = genericDao.search(searchRequest::generateQuery, getTargetClass());

		return toDtos(entityList.stream().filter(searchRequest::filter).collect(Collectors.toList()));
	}

	public List<D> search(SearchRequest<T, D> searchRequest, Predicate<T> filter) {
		List<T> entityList = genericDao.search(searchRequest::generateQuery, getTargetClass());

		return toDtos(entityList.stream().filter(filter).collect(Collectors.toList()));
	}
}
