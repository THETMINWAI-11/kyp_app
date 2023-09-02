package com.khayayphyu.service.search;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.khayayphyu.entity.AbstractEntity;

public abstract class AbstractSearchRequest<T extends AbstractEntity> implements SearchRequest<T> {
	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public boolean isRequireReport() {
		return false;
	}

	public T getTargetClass() {
		return null;
	}

	public List<T> defaultOnInValid(Function<SearchRequest<T>, List<T>> main, Supplier<List<T>> supplier) {
		if (isValid())
			return main.apply(this);

		return supplier.get();
	}

}
