package com.khayayphyu.dto.product;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.dto.purchase.PurchasePriceDto;
import com.khayayphyu.dto.sale.SalePriceDto;
import com.khayayphyu.entity.product.Product;
import com.khayayphyu.utils.type.ProductType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends AbstractDto<Product> {

	private String productName;
	private SalePriceDto salePrice;
	private int quantity;
	private PurchasePriceDto purchasePrice;
	private ProductType type;
	
	@Override
	public Product toEntity() {
		Product product = new Product();
		product.setId(getId());
		product.setProductName(productName);
		product.setProductType(type);
		product.setPurchasePrice(purchasePrice == null ? null : PurchasePriceDto.createFromId(purchasePrice.getId()).toEntity());
		product.setSalePrice(salePrice == null ? null : SalePriceDto.createFromId(salePrice.getId()).toEntity());
		return product;
	}
	
	public static ProductDto create(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.productName = product.getProductName();
		productDto.purchasePrice = PurchasePriceDto.create(product.getPurchasePrice());
		productDto.salePrice = SalePriceDto.create(product.getSalePrice());
		productDto.type = product.getProductType();
		return productDto;
	}
	
	public static ProductDto createFromId(Long id) {
		if(id == null) {
			return null;
		}
		ProductDto productDto = new ProductDto();
		productDto.setId(id);
		return productDto;
	}

}
