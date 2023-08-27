package com.khayayphyu.service.sale;

import java.util.List;

import com.khayayphyu.dto.sale.SaleDto;
import com.khayayphyu.entity.sale.Sale;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.SaleSearchRequest;

public interface SaleService extends AbstractService<Sale, SaleDto> {
	public List<SaleDto> search(SaleSearchRequest searchRequest);
}
