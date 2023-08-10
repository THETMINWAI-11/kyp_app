package com.khayayphyu.dao.sale;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.sale.SalePrice;

@Repository
@Transactional
@EnableJpaRepositories
public interface SalePriceDao extends CrudRepository<SalePrice, Long>{

	@Query("select sp from SalePrice sp where sp.id = :id")
	SalePrice getBySalePriceId(Long id);
	
}
