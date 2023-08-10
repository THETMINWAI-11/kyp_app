package com.khayayphyu.entity.purchase;

import java.util.Date;

import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.entity.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "PurchasePrice")
@EqualsAndHashCode(callSuper = true)
public class PurchasePrice extends AbstractEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	@Column(name = "amount")
	private int amount;

	@Column(name = "discount")
	private Double discount;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

}
