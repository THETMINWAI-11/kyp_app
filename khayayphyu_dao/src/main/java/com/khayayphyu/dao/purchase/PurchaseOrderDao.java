package com.khayayphyu.dao.purchase;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.khayayphyu.entity.purchase.PurchaseOrder;

@Repository
public interface PurchaseOrderDao extends CrudRepository<PurchaseOrder, Long>{
	@Query("select po from PurchaseOrder po where po.id = :id")
	PurchaseOrder getByPurchaseOrderId(Long id);
	
	@Query("select po from PurchaseOrder po where Date(po.date) = Date(:orderDate)")
	List<PurchaseOrderDao> getByOrderDate(Date orderDate);

}
