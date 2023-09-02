package com.khayayphyu.service.search;

import com.khayayphyu.entity.product.Product;

public class ProductSearchRequest implements SearchRequest<Product> {

	private String productName;
	private StringBuffer buffer;

	@Override
	public String generateQuery() {
		buffer = new StringBuffer(generateBaseQuery());
		addName();
		return buffer.toString();
	}

	@Override
	public String generateBaseQuery() {
		return "select p from Product p where 1 = 1";
	}

	private void addName() {
		buffer.append(" and upper(p.name) like upper('%" + productName + "')");
	}

}
