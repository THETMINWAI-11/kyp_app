package com.khayayphyu.service.product;

import java.util.List;

import com.khayayphyu.entity.product.Product;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.ProductSearchRequest;

public interface ProductService extends AbstractService<Product> {
	public Product getDetail(Long id);

	public List<Product> search(ProductSearchRequest searchRequest);
}
