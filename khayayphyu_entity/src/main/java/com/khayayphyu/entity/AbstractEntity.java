package com.khayayphyu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	public <T extends AbstractEntity> boolean isSame(T route) {
		if(route == null)
			return false;
		return route.getId().equals(getId());
	}
	
	public boolean isNew() {
		return id == null;
	}
}
