package com.khayayphyu.service.product;

import java.util.List;

import com.khayayphyu.dto.product.ProductDto;
import com.khayayphyu.entity.product.Product;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.ProductSearchRequest;

public interface ProductService extends AbstractService<Product, ProductDto> {
	public ProductDto getDetail(Long id);
	public List<ProductDto> search(ProductSearchRequest searchRequest);
}
