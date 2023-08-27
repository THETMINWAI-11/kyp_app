package com.khayayphyu.service.sale;

import java.util.List;

import com.khayayphyu.dto.sale.SalePriceDto;
import com.khayayphyu.entity.sale.SalePrice;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.SalePriceSearchRequest;

public interface SalePriceService extends AbstractService<SalePrice, SalePriceDto> {
	public List<SalePriceDto> search(SalePriceSearchRequest searchRequest);
}
