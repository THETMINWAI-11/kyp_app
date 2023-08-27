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
import com.khayayphyu.dto.purchase.PurchasePriceDto;
import com.khayayphyu.service.purchase.PurchasePriceService;
import com.khayayphyu.service.search.PurchasePriceSearchRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("purchase-price")
public class PurchasePriceController extends AbstractController {

	@Autowired
	private PurchasePriceService purchasePriceService;

	@GetMapping("/setup")
	@RequiredPermission("purchase-price-setup")
	public String getPurchasePriceSetup(@RequestParam(value = "id", defaultValue = "0", required = false) Long id,
			Model model) {
		if (id.longValue() > 0) {
			PurchasePriceDto purchasePriceDto = purchasePriceService.get(id);
			model.addAttribute("purchasePriceDto", purchasePriceDto);
			createPage("Purchase Price Edit", model);
		} else {
			createPage("Purchase Price Setup", model);
			PurchasePriceDto purchasePriceDto = new PurchasePriceDto();
			model.addAttribute("purchasePriceDto", purchasePriceDto);
		}
		return "purchase_price_setup";
	}

	@PostMapping("/setup")
	public String postPurchasePriceSetup(@ModelAttribute("purchasePriceDto") PurchasePriceDto purchasePriceDto,
			Model model) {
		purchasePriceService.save(purchasePriceDto);
		return getPurchasePriceSetup(purchasePriceDto.getId(), model);
	}

	@GetMapping("/search")
	@RequiredPermission("purchase-price-search")
	public String getPurchasePriceSearch(Model model, HttpServletRequest searchRequest) {
		createPage("Purchase Price Search", model);
		model.addAttribute("searchRequest", new PurchasePriceSearchRequest());
		model.addAttribute("priceList", purchasePriceService.getAll());
		return "purchase_price_search";
	}

	@PostMapping("/search")
	public String postPurchasePriceSearch(Model model,
			@ModelAttribute("purchasePriceDto") PurchasePriceSearchRequest purchasePriceSearchRequest) {
		
		model.addAttribute("searchRequest", purchasePriceSearchRequest);
		if(!purchasePriceSearchRequest.isValid()) {
			return "purchase_price_search";
		}
		model.addAttribute("priceList", purchasePriceService.search(purchasePriceSearchRequest));
		return "purchase_price_search";

	}

}
