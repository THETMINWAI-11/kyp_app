package com.khayayphyu.entity.purchase;

import java.util.Date;
import java.util.List;

import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.entity.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "Purchase")
@EqualsAndHashCode(callSuper = true)
public class Purchase extends AbstractEntity {
	
	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<PurchaseOrder> purchaseOrderList;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "customerName")
	private Customer customer;

	@Column(name = "total")
	private int total;

	@Column(name = "payAmount")
	private int payAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Temporal(TemporalType.DATE)
	@Column(name = "purchaseDate")
	private Date purchaseDate;
}
