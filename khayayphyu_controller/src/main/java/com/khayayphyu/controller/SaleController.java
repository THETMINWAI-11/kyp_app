package com.khayayphyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khayayphyu.controller.util.RequiredPermission;
import com.khayayphyu.entity.sale.Sale;
import com.khayayphyu.service.sale.SaleOrderService;
import com.khayayphyu.service.sale.SalePriceService;
import com.khayayphyu.service.sale.SaleService;

@Controller
@RequestMapping("sale")
public class SaleController extends AbstractController {

	@Autowired
	private SaleService saleService;

	@Autowired
	private SaleOrderService saleOrderService;

	@Autowired
	private SalePriceService salePriceService;
	

	@GetMapping("/setup")
	@RequiredPermission("sale-setup")
	public String getSaleSetup(@RequestParam(value = "id", defaultValue = "0", required = false)Long id, Model model) {
		if(id.longValue() > 0) {
			Sale saleDto = saleService.get(id);
			model.addAttribute("saleDto", saleDto);
			model.addAttribute("saleBy", saleDto.getSaleBy());
		}
		return "sale_setup";
	}

//	private void updatePriceToSaleOrder(SaleOrderDto saleOrderDto) {
//
//		try {
//			SalePriceDto salePrice = salePriceService.getByProduct(saleOrderDto.getProduct());
//			saleOrderDto.setAmount(salePrice.getAmount());
//			saleOrderDto.setAmount(saleOrderDto.getPrice() * saleOrderDto.getQuantity());
//		} catch (ServiceUnavailableException e) {
//			e.printStackTrace();
//		}
//
//	}
}
