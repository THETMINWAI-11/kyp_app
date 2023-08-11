package com.khayayphyu.service.customer.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.customer.CustomerDao;
import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.service.customer.CustomerService;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.search.CustomerSearchRequest;

@Service
public class CustomerServiceImpl extends AbstractServiceImpl<Customer, CustomerDto> implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private GenericDao genericDao;

	@Override
	public List<CustomerDto> search(CustomerSearchRequest searchRequest) {
		return toDtos(genericDao.search(searchRequest::generateQuery, Customer.class));
	}

	@Override
	protected CrudRepository<Customer, Long> getDao() {
		return customerDao;
	}

	@Override
	public Class<Customer> getTargetClass() {
		return Customer.class;
	}

	@Override
	public Function<Customer, CustomerDto> getDtoConvertor() {
		return CustomerDto::create;
	}
	
	public CustomerDto getDetail(Long id) {
		Customer entity = customerDao.getByCustomerId(id);
		return entity != null ? CustomerDto.create(entity) : null;
	}
	

}
