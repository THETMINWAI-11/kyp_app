package com.khayayphyu.dao.sale;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.sale.SaleOrder;

@Repository
@Transactional
@EnableJpaRepositories
public interface SaleOrderDao extends CrudRepository<SaleOrder, Long> {
	
	@Query("select so from SaleOrder so where so.id = :id")
	SaleOrder getBySaleOrderId(Long id);
	
	@Query("select so from SaleOrder so where Date(so.orderDate) = Date(:orderDate")
	List<SaleOrderDao> getByOrderDate(Date orderDate);

}
