package com.khayayphyu.service.purchase;


import java.util.List;

import com.khayayphyu.entity.purchase.PurchasePrice;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.PurchasePriceSearchRequest;

public interface PurchasePriceService extends AbstractService<PurchasePrice> {

	public List<PurchasePrice> search(PurchasePriceSearchRequest searchRequest);
	
}
