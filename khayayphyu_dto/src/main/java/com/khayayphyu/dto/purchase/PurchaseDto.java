package com.khayayphyu.dto.purchase;

import java.util.Date;
import java.util.List;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.dto.user.UserDto;
import com.khayayphyu.entity.purchase.Purchase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseDto extends AbstractDto<Purchase> {
	
	private List<PurchaseOrderDto> purchaseOrder;
	private CustomerDto customer;
	private int total;
	private int payAmount;
	private Date purchaseDate;
	private UserDto user;
	
	@Override
	public Purchase toEntity() {
		Purchase purchase = new Purchase();
		purchase.setId(getId());
		purchase.setCustomer(customer == null ? null : CustomerDto.createFromId(customer.getId()).toEntity());
		purchase.setPayAmount(payAmount);
		purchase.setTotal(total);
		purchase.setUser(user == null ? null : UserDto.createFromId(user.getId()).toEntity());
		return purchase;
	}
	
	public static PurchaseDto create(Purchase purchase) {
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setId(purchase.getId());
		purchaseDto.customer = CustomerDto.create(purchase.getCustomer());
		purchaseDto.payAmount = purchase.getPayAmount();
		purchaseDto.total = purchase.getTotal();
		purchaseDto.user = UserDto.create(purchase.getUser());
		return purchaseDto;
	}
	
	public static PurchaseDto createFromId(Long id) {
		if(id == null) {
			return null;
		}
		
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setId(id);
		return purchaseDto;
	}

}
