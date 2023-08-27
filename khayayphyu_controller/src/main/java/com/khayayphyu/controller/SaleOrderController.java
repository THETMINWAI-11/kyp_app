package com.khayayphyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khayayphyu.controller.util.RequiredPermission;
import com.khayayphyu.dto.sale.SaleOrderDto;
import com.khayayphyu.service.sale.SaleOrderService;
import com.khayayphyu.service.search.SaleOrderSearchRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("sale-order")
public class SaleOrderController extends AbstractController {
	
	@Autowired
	private SaleOrderService saleOrderService;

	@GetMapping("/setup")
	@RequiredPermission("sale-order-setup")
	public String getSaleOrderSetup(@RequestParam(value = "id", defaultValue = "0", required = false)Long id, Model model) {
		if(id.longValue() > 0) {
			SaleOrderDto saleOrderDto = saleOrderService.get(id);
			model.addAttribute("saleOrderDto", saleOrderDto);
			createPage("SaleOrder Edit", model);
		}else {
			createPage("SaleOrder Setup", model);
			SaleOrderDto saleOrderDto = new SaleOrderDto();
			model.addAttribute("saleOrderDto", saleOrderDto);
		}
		model.addAttribute("saleOrderList", saleOrderService.getAll());
		return "sale_order_setup";
	}
	
	@PostMapping("/setup")
	public String postSaleOrderSetup(@ModelAttribute("saleOrderDto")SaleOrderDto saleOrderDto, Model model) {
		saleOrderService.save(saleOrderDto);
		return getSaleOrderSetup(saleOrderDto.getId(), model);
	}
	
	@GetMapping("/search")
	@RequiredPermission("sale-order-search")
	public String getSaleOrderSearch(Model model, HttpServletRequest searchRequest) {
		createPage("Sale Order Search", model);
		model.addAttribute("searchRequest", new SaleOrderSearchRequest());
		model.addAttribute("purchaseOrderList", saleOrderService.getAll());
		return "sale_order_search";
	}
	
	@PostMapping("/search")
	public String postSaleOrderSearch(Model model, @ModelAttribute("saleOrderDto") SaleOrderSearchRequest saleOrderSearchRequest) {
		model.addAttribute("searchRequest", saleOrderSearchRequest);
		if(!saleOrderSearchRequest.isValid()) {
			return "sale_order_search";
		}
		model.addAttribute("saleOrderList", saleOrderService.search(saleOrderSearchRequest));
		return "sale_order_search";
	}
}
