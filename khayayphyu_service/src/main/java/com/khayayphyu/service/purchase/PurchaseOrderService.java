package com.khayayphyu.service.purchase;

import java.util.List;

import com.khayayphyu.dto.purchase.PurchaseOrderDto;
import com.khayayphyu.entity.purchase.PurchaseOrder;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.PurchaseOrderSearchRequest;

public interface PurchaseOrderService extends AbstractService<PurchaseOrder, PurchaseOrderDto> {

	public List<PurchaseOrderDto> search(PurchaseOrderSearchRequest searchRequest);
	
}
