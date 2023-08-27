package com.khayayphyu.dto.purchase;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.entity.product.Product;
import com.khayayphyu.entity.purchase.PurchasePrice;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchasePriceDto extends AbstractDto<PurchasePrice> {
	
	private Product product;
	private int amount;
	private Double discount;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date date;
	
	@Override
	public PurchasePrice toEntity() {
		PurchasePrice purchasePrice = new PurchasePrice();
		purchasePrice.setId(getId());
		purchasePrice.setProduct(purchasePrice.getProduct());
		purchasePrice.setAmount(amount);
		purchasePrice.setDiscount(discount);
		purchasePrice.setDate(date);
		return purchasePrice;
	}
	
	public static PurchasePriceDto create(PurchasePrice purchasePrice) {
		PurchasePriceDto purchasePriceDto = new PurchasePriceDto();
		purchasePriceDto.setId(purchasePrice.getId());
		purchasePriceDto.product = purchasePrice.getProduct();
		purchasePriceDto.amount = purchasePrice.getAmount();
		purchasePriceDto.discount = purchasePrice.getDiscount();
		purchasePriceDto.date = purchasePrice.getDate();
		return purchasePriceDto;
	}
	
	public static PurchasePriceDto createFromId(Long id) {
		if(id == null) {
			return null;
		}
		
		PurchasePriceDto priceDto = new PurchasePriceDto();
		priceDto.setId(id);
		return priceDto;
	}

}
