package com.khayayphyu.service.sale;

import java.util.List;

import com.khayayphyu.entity.sale.Sale;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.SaleSearchRequest;

public interface SaleService extends AbstractService<Sale> {
	public List<Sale> search(SaleSearchRequest searchRequest);
}
