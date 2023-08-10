package com.khayayphyu.dao.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.khayayphyu.entity.product.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Long> {
	
	@Query("select p from Product p where p.id = :id")
	Product getByProductId(Long id);
	
	@Query("select p from Product p where p.productName = :name")
	String getByProductName(String name);

}
