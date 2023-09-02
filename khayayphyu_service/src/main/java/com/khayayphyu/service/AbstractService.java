package com.khayayphyu.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.service.search.SearchRequest;

public interface AbstractService<T extends AbstractEntity> {

	public void save(T d);

	public void update(T d);

	public T get(long id);

	public List<T> getAll();

	public List<T> getAll(Comparator<T> sorter);

	public <D> List<D> getAll(Function<T, D> convertor);

	public List<T> search(SearchRequest<T> searchRequest);

	public List<T> search(SearchRequest<T> searchRequest, Predicate<T> filter);

	public void delete(Long id);

	public T saveWithReturn(T d);

	public Optional<String> saveWithValidation(T d);
}
