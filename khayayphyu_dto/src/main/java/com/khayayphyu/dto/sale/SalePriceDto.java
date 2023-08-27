package com.khayayphyu.dto.sale;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;
import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.dto.product.ProductDto;
import com.khayayphyu.entity.product.Product;
import com.khayayphyu.entity.sale.SalePrice;
import com.khayayphyu.utils.date.DateUtil;
import com.khayayphyu.utils.views.SummaryView;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonView(SummaryView.class)
@EqualsAndHashCode(callSuper = true)
public class SalePriceDto extends AbstractDto<SalePrice> {

	private Product product;
	private int amount;
	private Double discount;

	@DateTimeFormat(pattern = DateUtil.uiDateFormatStr)
	private Date date;

	@Override
	public SalePrice toEntity() {
		SalePrice salePrice = new SalePrice();
		salePrice.setId(getId());
		salePrice.setProduct(product == null ? null : ProductDto.createFromId(product.getId()).toEntity());
		salePrice.setAmount(amount);
		salePrice.setDate(date);
		salePrice.setDiscount(discount);
		return salePrice;
	}

	public static SalePriceDto create(SalePrice salePrice) {
		SalePriceDto salePriceDto = new SalePriceDto();
		salePriceDto.setId(salePrice.getId());
		salePriceDto.product = salePrice.getProduct();
		salePriceDto.amount = salePrice.getAmount();
		salePriceDto.discount = salePrice.getDiscount();
		salePriceDto.date = salePrice.getDate();
		return salePriceDto;
	}
	
	public static SalePriceDto createFromId(Long id) {
		if(id == null) {
			return null;
		}
		
		SalePriceDto priceDto = new SalePriceDto();
		priceDto.setId(id);
		return priceDto;
	}

}
