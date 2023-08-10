package com.khayayphyu.dao.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.customer.Customer;


@Repository
@Transactional
@EnableJpaRepositories
public interface CustomerDao extends CrudRepository<Customer, Long> {
	
	@Query("select c from Customer c where c.id = :id")
	Customer getByCustomerId(Long id);
	
	@Query("select c from Customer c where c.name = :name")
	String findByCustomerName(String name);

}
