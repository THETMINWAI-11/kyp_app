package com.khayayphyu.service.customer;

import java.util.List;

import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.CustomerSearchRequest;

public interface CustomerService extends AbstractService<Customer, CustomerDto> {
	
	public CustomerDto getDetail(Long id);

	public List<CustomerDto> search(CustomerSearchRequest searchRequest);
}
