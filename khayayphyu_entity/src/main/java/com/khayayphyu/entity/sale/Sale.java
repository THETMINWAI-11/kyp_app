package com.khayayphyu.entity.sale;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "Sale")
@EqualsAndHashCode(callSuper = true)
public class Sale extends AbstractEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	private Customer customer;
	
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SaleOrder> saleOrderList;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User saleBy;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "payAmount")
	private int payAmount;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "saleDate")
	private Date saleDate;
	
}
