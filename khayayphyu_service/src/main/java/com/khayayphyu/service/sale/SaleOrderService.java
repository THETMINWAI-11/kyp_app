package com.khayayphyu.service.sale;

import java.util.List;

import com.khayayphyu.dto.sale.SaleOrderDto;
import com.khayayphyu.entity.sale.SaleOrder;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.SaleOrderSearchRequest;

public interface SaleOrderService extends AbstractService<SaleOrder, SaleOrderDto> {
	public List<SaleOrderDto> search(SaleOrderSearchRequest searchRequest);
}
