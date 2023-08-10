package com.khayayphyu.entity.customer;

import com.khayayphyu.entity.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "Customer")
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractEntity {
	
	@Column(name = "name")
	private String customerName;
	
	@Column(name = "adress")
	private String adress;
}
