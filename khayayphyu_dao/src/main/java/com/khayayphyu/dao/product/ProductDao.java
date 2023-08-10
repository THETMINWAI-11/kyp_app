package com.khayayphyu.dao.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.product.Product;

@Repository
@Transactional
@EnableJpaRepositories
public interface ProductDao extends CrudRepository<Product, Long> {
	
	@Query("select p from Product p where p.id = :id")
	Product getByProductId(Long id);
	
	@Query("select p from Product p where p.name = :name")
	String getByProductName(String name);

}
