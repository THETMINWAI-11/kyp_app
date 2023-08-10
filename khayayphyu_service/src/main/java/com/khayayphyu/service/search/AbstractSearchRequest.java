package com.khayayphyu.service.search;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.entity.AbstractEntity;

public abstract class AbstractSearchRequest<T extends AbstractEntity, D extends AbstractDto<T>>
		implements SearchRequest<T, D> {
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

	public List<D> defaultOnInValid(Function<SearchRequest<T, D>, List<D>> main, Supplier<List<D>> supplier) {
		if (isValid())
			return main.apply(this);

		return supplier.get();
	}

}
