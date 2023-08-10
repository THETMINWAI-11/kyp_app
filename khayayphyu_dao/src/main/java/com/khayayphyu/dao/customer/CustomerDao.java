package com.khayayphyu.dao.customer;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.khayayphyu.entity.customer.Customer;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Long> {
	
	@Query("select c from Customer c where c.id = :id")
	Customer getByCustomerId(Long id);
	
	@Query("select c from Customer c where c.customerName = :name")
	String findByCustomerName(String name);

}
