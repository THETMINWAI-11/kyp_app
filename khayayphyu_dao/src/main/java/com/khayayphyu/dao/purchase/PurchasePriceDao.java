package com.khayayphyu.dao.purchase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.purchase.PurchasePrice;

@Repository
@Transactional
@EnableJpaRepositories
public interface PurchasePriceDao extends CrudRepository<PurchasePrice, Long> {

	@Query("select pp from PurchasePrice pp where pp.id = :id")
	PurchasePrice getByPurchasePriceId(Long id);

}
