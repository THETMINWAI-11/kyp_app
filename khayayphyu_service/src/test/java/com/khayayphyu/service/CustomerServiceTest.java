package com.khayayphyu.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.khayayphyu.dao.customer.CustomerDao;
import com.khayayphyu.dto.customer.CustomerDto;
import com.khayayphyu.entity.customer.Customer;
import com.khayayphyu.service.customer.CustomerService;

@EnableAutoConfiguration
@SpringBootTest(classes = CustomerServiceTest.class)
@ComponentScan(basePackages = { "com.khayayphyu.service", "com.kyayayphyu.dao" })
@EntityScan("com.khayayphyu.entity")
@EnableJpaRepositories(basePackages = "com.khayayphyu.dao")
@PropertySource(value = { "classpath:application.properties" })
public class CustomerServiceTest {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerService customerService;
	
	private static Logger logger = LogManager.getLogger(CustomerServiceTest.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceTest.class, args);
	}

	@Test
	public void saveCustomer() {
		// CustomerDto customerDto = new CustomerDto();
		Customer customer = new Customer();
		customer.setCustomerName("Thet Min Wai");
		customer.setAdress("Kyun Ywar");
		customerService.save(CustomerDto.create(customer));
	}

}
