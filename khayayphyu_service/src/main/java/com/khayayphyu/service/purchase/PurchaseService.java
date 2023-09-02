package com.khayayphyu.service.purchase;

import java.util.List;

import com.khayayphyu.entity.purchase.Purchase;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.PurchaseSearchRequest;

public interface PurchaseService extends AbstractService<Purchase> {
	public List<Purchase> search(PurchaseSearchRequest searchRequest);
}
