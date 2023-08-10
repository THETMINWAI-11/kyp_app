package com.khayayphyu.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.service.search.SearchRequest;


public interface AbstractService<T extends AbstractEntity, D extends AbstractDto<T>> {
	public void save(D d);

	public void update(D d);

	public void save(D d, Consumer<D> consumer);

	public D get(long id);

	public List<D> getAll();

	public List<D> getAll(Comparator<D> sorter);

	public List<D> getAll(Function<T, D> convertor);

	public List<D> search(SearchRequest<T, D> searchRequest);

	public List<D> search(SearchRequest<T, D> searchRequest, Predicate<T> filter);

	// public List<D> searchWithFieldFilter(SearchRequest<T, D> searchRequest,
	// LoginUserDto loginUser, BiPredicate<T, LoginUserDto> filter);

	public void delete(Long id);

	public T saveWithReturn(D d);

	public Optional<String> saveWithValidation(D d);
}
