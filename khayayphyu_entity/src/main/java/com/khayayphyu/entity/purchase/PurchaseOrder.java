package com.khayayphyu.entity.purchase;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.entity.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "PurchaseOrder")
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrder extends AbstractEntity {
	
	@Column(name = "amount")
	private int amount;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Purchase purchase;

	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
}
