package com.khayayphyu.service.purchase;


import java.util.List;

import com.khayayphyu.dto.purchase.PurchasePriceDto;
import com.khayayphyu.entity.purchase.PurchasePrice;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.PurchasePriceSearchRequest;

public interface PurchasePriceService extends AbstractService<PurchasePrice, PurchasePriceDto> {

	public List<PurchasePriceDto> search(PurchasePriceSearchRequest searchRequest);
	
}
