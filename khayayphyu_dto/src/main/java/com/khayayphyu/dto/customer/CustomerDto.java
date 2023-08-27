package com.khayayphyu.dto.customer;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.utils.type.CustomerStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends AbstractDto<Customer> {

	private String customerName;
	private String adress;
	private CustomerStatus status;
	
	@Override
	public Customer toEntity() {
		Customer customer = new Customer();
		customer.setId(getId());
		customer.setCustomerName(customerName);
		customer.setAdress(adress);
		customer.setStatus(status);
		return customer;
	}
	
	public static CustomerDto create(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.customerName = customer.getCustomerName();
		customerDto.adress = customer.getAdress();
		customerDto.status = customer.getStatus();
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
