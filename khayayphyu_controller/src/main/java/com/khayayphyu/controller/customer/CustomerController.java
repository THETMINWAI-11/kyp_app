package com.khayayphyu.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khayayphyu.controller.AbstractController;
import com.khayayphyu.controller.util.RequiredPermission;
import com.khayayphyu.service.customer.CustomerService;
import com.khayayphyu.service.search.CustomerSearchRequest;
import com.khayayphyu.utils.type.CustomerStatus;

import jakarta.servlet.http.HttpServletRequest;

import com.khayayphyu.dto.customer.CustomerDto;

@Controller
@RequestMapping("customer")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/setup")
	@RequiredPermission("customer-setup")
	public String getCustomerSetup(@RequestParam(value = "id", defaultValue = "0", required = false) Long id,
			Model model) {
		if (id.longValue() > 0) {
			CustomerDto customerDto = customerService.getDetail(id);
			model.addAttribute("customerDto", customerDto);
			model.addAttribute("status", CustomerStatus.values());
			createPage("Customer Edit", model);
		} else {
			createPage("Customer Setup", model);
			CustomerDto customerDto = new CustomerDto();
			model.addAttribute("customerDto", customerDto);
			model.addAttribute("status", CustomerStatus.values());
		}
		model.addAttribute("customerList", customerService.getAll());
		return "customer_setup";
	}

	@PostMapping("/setup")
	public String postCustomerSetup(@ModelAttribute("customerDto") CustomerDto customerDto, Model model) {
		customerService.save(customerDto);
		return getCustomerSetup(customerDto.getId(), model);
	}

	@GetMapping("/search")
	@RequiredPermission("customer-search")
	public String getCustomerSearch(Model model, HttpServletRequest searchRequest) {
		model.addAttribute("searchRequest", new CustomerSearchRequest());
		model.addAttribute("customerList", customerService.getAll());
		return "customer_search";
	}

	@PostMapping("/search")
	public String postCustomerSearch(@ModelAttribute("customerDto") CustomerSearchRequest customerSearchRequest, Model model) {
		model.addAttribute("searchRequest", customerSearchRequest);
		if(!customerSearchRequest.isValid()) {
			return "customer_search";
		}
		model.addAttribute("customerList", customerService.search(customerSearchRequest));
		return "customer_search";
	}

}
