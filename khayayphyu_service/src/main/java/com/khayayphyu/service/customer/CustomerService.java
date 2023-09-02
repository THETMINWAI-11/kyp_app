package com.khayayphyu.service.customer;

import java.util.List;

import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.CustomerSearchRequest;

public interface CustomerService extends AbstractService<Customer> {
	
	public Customer getDetail(Long id);

	public List<Customer> search(CustomerSearchRequest searchRequest);
}
