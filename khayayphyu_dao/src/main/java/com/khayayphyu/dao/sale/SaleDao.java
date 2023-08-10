package com.khayayphyu.dao.sale;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.sale.Sale;

@Repository
@Transactional
@EnableJpaRepositories
public interface SaleDao extends CrudRepository<Sale, Long> {

	@Query("select s from Sale s where s.id = :id")
	Sale getBySaleId(Long id);

	@Query("select s from Sale s where Date(s.saleDate) between Date(:startDate) and Date(:endDate")
	List<SaleDao> findByPeriod(Date startDate, Date endDate);

}
