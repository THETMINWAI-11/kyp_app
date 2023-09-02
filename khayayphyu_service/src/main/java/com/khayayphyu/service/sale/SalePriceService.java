package com.khayayphyu.service.sale;

import java.util.List;

import com.khayayphyu.entity.sale.SalePrice;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.SalePriceSearchRequest;

public interface SalePriceService extends AbstractService<SalePrice> {
	public List<SalePrice> search(SalePriceSearchRequest searchRequest);
}
