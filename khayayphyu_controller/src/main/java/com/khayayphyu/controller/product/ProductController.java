package com.khayayphyu.controller.product;

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
import com.khayayphyu.entity.product.Product;
import com.khayayphyu.service.product.ProductService;
import com.khayayphyu.service.search.ProductSearchRequest;
import com.khayayphyu.utils.type.ProductType;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("product")
public class ProductController extends AbstractController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/setup")
	@RequiredPermission("product-setup")
	public String getProductSetup(@RequestParam(value = "id", defaultValue = "0", required = false) Long id, Model model) {
		if(id.longValue() > 0) {
			Product productDto = productService.getDetail(id); 
			model.addAttribute("productDto", productDto);
			model.addAttribute("productType", ProductType.values());
			createPage("Product Edit", model);
		}else {
			createPage("Product Setup", model);
			Product productDto = new Product();
			model.addAttribute("productDto", productDto);
			model.addAttribute("productType", ProductType.values());
		}
		model.addAttribute("productList", productService.getAll());
		return "product_setup";
	}
	
	@PostMapping("/setup")
	public String postProductSetup(@ModelAttribute("productDto") Product productDto, Model model) {
		productService.save(productDto);
		return getProductSetup(productDto.getId(), model);
	}
	
	@GetMapping("/search")
	@RequiredPermission("product-search")
	public String getProductSearch(Model model, HttpServletRequest searchRequest) {
		createPage("Product Search", model);
		model.addAttribute("searchRequest", new ProductSearchRequest());
		model.addAttribute("productList", productService.getAll());
		return "product_search";
	}
	
	@PostMapping("/search")
	public String postProductSearch(@ModelAttribute("productDto") ProductSearchRequest productSearchRequest, Model model) {
		model.addAttribute("searchRequest", productSearchRequest);
		if(!productSearchRequest.isValid()) {
			return "product_search";
		}
		model.addAttribute("productList", productService.search(productSearchRequest));
		return "product_search";
	}

}
