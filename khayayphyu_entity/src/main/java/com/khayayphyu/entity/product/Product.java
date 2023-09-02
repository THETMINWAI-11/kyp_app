package com.khayayphyu.entity.product;

import java.util.List;

import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.entity.purchase.PurchasePrice;
import com.khayayphyu.entity.sale.SalePrice;
import com.khayayphyu.utils.type.ProductType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends AbstractEntity {

	@Column(name = "name")
	private String productName;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	@OneToOne
	@JoinColumn(name = "sale_price")
	private SalePrice salePrice;
	
	@Column(name = "quantity")
	private int quantity;
	
	@OneToOne
	@JoinColumn(name = "purchase_price")
	private PurchasePrice purchasePrice;
	
	@Column(name = "packaging_type")
	@Enumerated(EnumType.STRING)
	private ProductType productType;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<SalePrice> salePriceHistory;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<PurchasePrice> purchasePriceHistory;
}
