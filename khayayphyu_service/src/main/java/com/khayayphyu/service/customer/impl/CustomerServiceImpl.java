package com.khayayphyu.service.customer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.customer.CustomerDao;
import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.service.customer.CustomerService;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.search.CustomerSearchRequest;

@Service
public class CustomerServiceImpl extends AbstractServiceImpl<Customer> implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private GenericDao genericDao;

	@Override
	public List<Customer> search(CustomerSearchRequest searchRequest) {
		return genericDao.search(searchRequest::generateQuery, Customer.class);
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
	public Customer getDetail(Long id) {
		return customerDao.getByCustomerId(id);
	}

}
