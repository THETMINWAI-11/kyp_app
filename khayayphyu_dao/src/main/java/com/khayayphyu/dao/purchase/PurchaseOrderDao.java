package com.khayayphyu.dao.purchase;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.purchase.PurchaseOrder;

@Repository
@Transactional
@EnableJpaRepositories
public interface PurchaseOrderDao extends CrudRepository<PurchaseOrder, Long>{
	@Query("select po from PurchaseOrder po where po.id = :id")
	PurchaseOrder getByPurchaseOrderId(Long id);
	
	@Query("select po from PurchaseOrder po where Date(po.orderDate) = Date(:orderDate")
	List<PurchaseOrderDao> getByOrderDate(Date orderDate);

}
