package com.khayayphyu.service.search;

import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.entity.customer.Customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerSearchRequest extends AbstractSearchRequest<Customer, CustomerDto> {
	
	private String customerName;
	private StringBuffer buffer;
	
	@Override
	public String generateQuery() {
		buffer = new StringBuffer(generateBaseQuery());
		addName();
		return buffer.toString();
	}

	@Override
	public String generateBaseQuery() {
		
		return "select c from Customer c where 1 = 1";
	}
	
	private void addName() {
		buffer.append(" and upper(c.name) like upper('%" + customerName + "')");
	}

}
