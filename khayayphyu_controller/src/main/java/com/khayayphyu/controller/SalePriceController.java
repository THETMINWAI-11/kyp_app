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
import com.khayayphyu.entity.sale.SalePrice;
import com.khayayphyu.service.sale.SalePriceService;
import com.khayayphyu.service.search.PurchasePriceSearchRequest;
import com.khayayphyu.service.search.SalePriceSearchRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("sale-price")
public class SalePriceController extends AbstractController {
	@Autowired
	private SalePriceService salePriceService;

	@GetMapping("/setup")
	@RequiredPermission("sale-price-setup")
	public String getSalePriceSetup(@RequestParam(value = "id", defaultValue = "0", required = false) Long id,
			Model model) {
		if (id.longValue() > 0) {
			SalePrice salePriceDto = salePriceService.get(id);
			model.addAttribute("salePriceDto", salePriceDto);
			createPage("Sale Price Edit", model);
		} else {
			createPage("Sale Price Setup", model);
			SalePrice salePriceDto = new SalePrice();
			model.addAttribute("salePriceDto", salePriceDto);
		}
		return "sale_price_setup";
	}

	@PostMapping("/setup")
	public String postSalePriceSetup(@ModelAttribute("salePriceDto") SalePrice salePriceDto,
			Model model) {
		salePriceService.save(salePriceDto);
		return getSalePriceSetup(salePriceDto.getId(), model);
	}

	@GetMapping("/search")
	@RequiredPermission("sale-price-search")
	public String getPurchasePriceSearch(Model model, HttpServletRequest searchRequest) {
		createPage("Sale Price Search", model);
		model.addAttribute("searchRequest", new PurchasePriceSearchRequest());
		model.addAttribute("priceList", salePriceService.getAll());
		return "sale_price_search";
	}

	@PostMapping("/search")
	public String postSalePriceSearch(Model model,
			@ModelAttribute("salePriceDto") SalePriceSearchRequest salePriceSearchRequest) {
		
		model.addAttribute("searchRequest", salePriceSearchRequest);
		if(!salePriceSearchRequest.isValid()) {
			return "sale_price_search";
		}
		model.addAttribute("priceList", salePriceService.search(salePriceSearchRequest));
		return "sale_price_search";

	}
}
