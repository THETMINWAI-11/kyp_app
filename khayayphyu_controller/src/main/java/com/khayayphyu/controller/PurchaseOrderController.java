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
import com.khayayphyu.service.purchase.PurchaseOrderService;
import com.khayayphyu.service.search.PurchaseOrderSearchRequest;

import jakarta.servlet.http.HttpServletRequest;

import com.khayayphyu.dto.purchase.PurchaseOrderDto;

@Controller
@RequestMapping("purchase-order")
public class PurchaseOrderController extends AbstractController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@GetMapping("/setup")
	@RequiredPermission("purchase-order-setup")
	public String getPurchaseOrderSetup(@RequestParam(value = "id", defaultValue = "0", required = false)Long id, Model model) {
		if(id.longValue() > 0) {
			PurchaseOrderDto purchaseOrderDto = purchaseOrderService.get(id);
			model.addAttribute("purchaseOrderDto", purchaseOrderDto);
			createPage("PurchaseOrder Edit", model);
		}else {
			createPage("PurchaseOrder Setup", model);
			PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
			model.addAttribute("purchaseOrderDto", purchaseOrderDto);
		}
		model.addAttribute("purchaseOrderList", purchaseOrderService.getAll());
		return "purchase_order_setup";
	}
	
	@PostMapping("/setup")
	public String postPurchaseOrderSetup(@ModelAttribute("purchaseOrderDto")PurchaseOrderDto purchaseOrderDto, Model model) {
		purchaseOrderService.save(purchaseOrderDto);
		return getPurchaseOrderSetup(purchaseOrderDto.getId(), model);
	}
	
	@GetMapping("/search")
	@RequiredPermission("purchase-order-search")
	public String getPurchaseOrderSearch(Model model, HttpServletRequest searchRequest) {
		createPage("Purchase Order Search", model);
		model.addAttribute("searchRequest", new PurchaseOrderSearchRequest());
		model.addAttribute("purchaseOrderList", purchaseOrderService.getAll());
		return "purchase_order_search";
	}
	
	@PostMapping("/search")
	public String postPurchaseOrderSearch(Model model, @ModelAttribute("purchaseOrderDto") PurchaseOrderSearchRequest purchaseOrderSearchRequest) {
		model.addAttribute("searchRequest", purchaseOrderSearchRequest);
		if(!purchaseOrderSearchRequest.isValid()) {
			return "purchase_order_search";
		}
		model.addAttribute("purchaseOrderList", purchaseOrderService.search(purchaseOrderSearchRequest));
		return "purchase_order_search";
	}
	
}
