package com.khayayphyu.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khayayphyu.controller.AbstractController;
import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.service.customer.CustomerService;
import com.khayayphyu.service.search.CustomerSearchRequest;

@RestController
@RequestMapping(value = "customer")
public class CustomerController extends AbstractController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(value = "setup")
	public String customerSetup(@RequestBody CustomerDto customerDto) {
		customerService.save(customerDto);
		customerService.getAll();
		return "customer_setup";
	}
	
	@GetMapping(value = "search/")
	public List<CustomerDto> searchByCustomerName(@RequestParam(name = "customerName") CustomerSearchRequest searchRequest) {
		return customerService.search(searchRequest);
	}
	
}
