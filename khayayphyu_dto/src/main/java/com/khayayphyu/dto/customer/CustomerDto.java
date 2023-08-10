package com.khayayphyu.dto.customer;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.entity.customer.Customer;

public class CustomerDto extends AbstractDto<Customer> {

	private String name;
	private String adress;
	
	@Override
	public Customer toEntity() {
		Customer customer = new Customer();
		customer.setId(getId());
		customer.setCustomerName(name);
		customer.setAdress(adress);
		return customer;
	}
	
	public static CustomerDto create(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.name = customer.getCustomerName();
		customerDto.adress = customer.getAdress();
		return customerDto;
	}
	
	public static CustomerDto createFromId(Long id) {
		if(id == null) {
			return null;
		}
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(id);
		return customerDto;
	}

}
