package com.khayayphyu.entity.product;

import com.khayayphyu.entity.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="item")
@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends AbstractEntity {
	
	@Column(name="name")
	private String name;
	
}
