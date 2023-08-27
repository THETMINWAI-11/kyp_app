package com.khayayphyu.dto.purchase;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.dto.product.ProductDto;
import com.khayayphyu.entity.purchase.Purchase;
import com.khayayphyu.entity.purchase.PurchaseOrder;
import com.khayayphyu.utils.date.DateUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderDto extends AbstractDto<PurchaseOrder> {
	
	private int amount;
	private ProductDto product;
	private Purchase purchase;
	private int quantity;
	@DateTimeFormat(pattern = DateUtil.uiTimeFormatStr)
	private Date date;
	
	@Override
	public PurchaseOrder toEntity() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(getId());
		purchaseOrder.setAmount(amount);
		purchaseOrder.setProduct(product == null ? null : ProductDto.createFromId(product.getId()).toEntity());
		purchaseOrder.setDate(date);
		return purchaseOrder;
	}
	
	public static PurchaseOrderDto create(PurchaseOrder purchaseOrder) {
		PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
		purchaseOrderDto.setId(purchaseOrder.getId());
		purchaseOrderDto.amount = purchaseOrder.getAmount();
		purchaseOrderDto.product = ProductDto.create(purchaseOrder.getProduct());
		purchaseOrderDto.date = purchaseOrder.getDate();
		return purchaseOrderDto;
	}
	
	public static PurchaseOrderDto createFromId(Long id) {
		if(id == null) {
			return null;
		}
		
		PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
		purchaseOrderDto.setId(id);
		return purchaseOrderDto;
	}

}
