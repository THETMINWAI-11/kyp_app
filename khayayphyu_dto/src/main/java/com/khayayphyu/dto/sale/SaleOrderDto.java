package com.khayayphyu.dto.sale;

import java.util.Date;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.dto.product.ProductDto;
import com.khayayphyu.entity.sale.SaleOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderDto extends AbstractDto<SaleOrder> {

	private ProductDto product;
	private double weight;
	private double quantity;
	private int amount;
	private Date orderDate;

	@Override
	public SaleOrder toEntity() {
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setId(getId());
		saleOrder.setAmount(amount);
		saleOrder.setWeight(weight);
		saleOrder.setQuantity(quantity);
		saleOrder.setProduct(product == null ? null : ProductDto.createFromId(product.getId()).toEntity());
		saleOrder.setOrderDate(orderDate);
		return saleOrder;
	}

	public static SaleOrderDto create(SaleOrder saleOrder) {
		SaleOrderDto saleOrderDto = new SaleOrderDto();
		saleOrderDto.setId(saleOrder.getId());
		saleOrderDto.weight = saleOrder.getWeight();
		saleOrderDto.amount = saleOrder.getAmount();
		saleOrderDto.quantity = saleOrder.getQuantity();
		saleOrderDto.product = ProductDto.create(saleOrder.getProduct());
		saleOrderDto.orderDate = saleOrder.getOrderDate();
		return saleOrderDto;
	}
	
	public static SaleOrderDto createFromId(Long id) {
		if(id == null) {
			return null;
		}
		
		SaleOrderDto saleOrderDto = new SaleOrderDto();
		saleOrderDto.setId(id);
		return saleOrderDto;
	}

}
