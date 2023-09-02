package com.khayayphyu.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.product.ProductDao;
import com.khayayphyu.entity.product.Product;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.product.ProductService;
import com.khayayphyu.service.search.ProductSearchRequest;

@Service
public class ProductServiceImpl extends AbstractServiceImpl<Product> implements ProductService {

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
	public List<Product> search(ProductSearchRequest searchRequest) {
		return genericDao.search(searchRequest::generateQuery, Product.class);
	}
	
	@Override
	public Product getDetail(Long id) {
		return productDao.getByProductId(id);
	}

}
