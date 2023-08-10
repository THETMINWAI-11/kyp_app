package com.khayayphyu.entity.sale;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.entity.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "SaleOrder")
@EqualsAndHashCode(callSuper = true)
public class SaleOrder extends AbstractEntity{

	@JsonIgnore
	@OneToOne
	private Sale sale;

	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@Column(name = "amount")
	private int amount;

	@Column(name = "weight")
	private double weight;

	@Column(name = "quantity")
	private double quantity;
	
	@Column(name = "order_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
}
