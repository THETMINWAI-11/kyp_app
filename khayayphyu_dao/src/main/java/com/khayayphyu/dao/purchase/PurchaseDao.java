package com.khayayphyu.dao.purchase;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.khayayphyu.entity.purchase.Purchase;

@Repository
public interface PurchaseDao extends CrudRepository<Purchase, Long> {
	@Query("select p from Purchase p where p.id = :id")
	Purchase getByPurchaseId(Long id);

	@Query("select p from Purchase p where Date(p.purchaseDate) between Date(:startDate) and Date(:endDate)")
	List<PurchaseDao> findByPeriod(Date startDate, Date endDate);
}
