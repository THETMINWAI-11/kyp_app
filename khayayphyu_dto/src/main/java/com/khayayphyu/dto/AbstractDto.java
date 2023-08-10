package com.khayayphyu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.utils.EntityUtil;

import lombok.Data;

@Data
public abstract class AbstractDto<T extends AbstractEntity> implements EntityUtil{
	private Long id;

	public abstract T toEntity();

	public AbstractDto() {

	}

	public AbstractDto(Long id) {
		this.id = id;
	}

	public T createEmptyEntity() {
		return null;
	}

	@JsonIgnore
	public boolean isNew() {
		return id == null || id == 0;
	}

	// use for nested dependency
	public T toEntityOnlyId() {
		T entnity = createEmptyEntity();
		entnity.setId(getId());
		return entnity;
	}
	
	public static <I extends AbstractDto<G>, G extends AbstractEntity> G convertToEntity(I i) {
		if(i == null)
			return null;
		return i.toEntity();
	}
	
	public static <T extends AbstractEntity>  boolean isNewEntity(T t) {
		return t == null || t.getId() == null;
	}
	
	public boolean isSameId(AbstractDto<?> t) {
		return id.equals(t.getId());
	}

}
