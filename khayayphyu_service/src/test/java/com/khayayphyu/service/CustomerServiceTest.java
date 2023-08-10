package com.khayayphyu.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.service.customer.CustomerService;

@SpringBootApplication
@PropertySource(value = { "classpath:application.properties"})
@ComponentScan(basePackages = { "com.khayayphyu" })
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;
	
	private static Logger logger = LogManager.getLogger(CustomerServiceTest.class);
	
	@Test
	public void saveCustomer() {
		//CustomerDto customerDto = new CustomerDto();
		Customer customer = new Customer();
		customer.setCustomerName("Thet Min Wai");
		customer.setAdress("Kyun Ywar");
		customerService.save(CustomerDto.create(customer));
	}

}
