package com.khayayphyu.service.product.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.product.ProductDao;
import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.dto.product.ProductDto;
import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.entity.product.Product;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.product.ProductService;
import com.khayayphyu.service.search.ProductSearchRequest;

@Service
public class ProductServiceImpl extends AbstractServiceImpl<Product, ProductDto> implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Override
	protected CrudRepository<Product, Long> getDao() {
		return productDao;
	}

	@Override
	public Class<Product> getTargetClass() {
		return Product.class;
	}

	@Override
	public Function<Product, ProductDto> getDtoConvertor() {
		return ProductDto::create;
	}
	
	@Override
	public List<ProductDto> search(ProductSearchRequest searchRequest) {
		return toDtos(genericDao.search(searchRequest::generateQuery, Product.class));
	}
	
	@Override
	public ProductDto getDetail(Long id) {
		Product entity = productDao.getByProductId(id);
		return entity != null ? ProductDto.create(entity) : null;
	}

}
