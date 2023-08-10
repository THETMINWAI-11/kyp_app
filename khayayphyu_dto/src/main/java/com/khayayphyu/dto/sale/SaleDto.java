package com.khayayphyu.dto.sale;

import java.util.Date;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.dto.user.UserDto;
import com.khayayphyu.entity.sale.Sale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDto extends AbstractDto<Sale> {

	private CustomerDto customer;
	private UserDto user;
	private Date saleDate;
	private int total;
	private int payAmount;

	@Override
	public Sale toEntity() {
		Sale sale = new Sale();
		sale.setId(getId());
		sale.setCustomer(customer == null ? null : CustomerDto.createFromId(customer.getId()).toEntity());
		sale.setPayAmount(payAmount);
		sale.setTotal(total);
		sale.setSaleBy(user == null ? null : UserDto.createFromId(user.getId()).toEntity());
		sale.setSaleDate(saleDate);
		return sale;
	}

	public static SaleDto create(Sale sale) {
		SaleDto saleDto = new SaleDto();
		saleDto.setId(sale.getId());
		saleDto.customer = CustomerDto.create(sale.getCustomer());
		saleDto.payAmount = sale.getPayAmount();
		saleDto.total = sale.getTotal();
		saleDto.user = UserDto.create(sale.getSaleBy());
		saleDto.saleDate = sale.getSaleDate();
		return saleDto;
	}
	
	public static SaleDto createFromId(Long id) {
		if(id == null) {
			return null;
		}
		SaleDto saleDto = new SaleDto();
		saleDto.setId(id);
		return saleDto;
	}

}
