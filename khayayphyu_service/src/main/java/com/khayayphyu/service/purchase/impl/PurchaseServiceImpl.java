package com.khayayphyu.service.purchase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.purchase.PurchaseDao;
import com.khayayphyu.entity.purchase.Purchase;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.purchase.PurchaseService;
import com.khayayphyu.service.search.PurchaseSearchRequest;

@Service
public class PurchaseServiceImpl extends AbstractServiceImpl<Purchase> implements PurchaseService {

	@Autowired
	private PurchaseDao purchaseDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Override
	protected CrudRepository<Purchase, Long> getDao() {
		return purchaseDao;
	}

	@Override
	public Class<Purchase> getTargetClass() {
		return Purchase.class;
	}

	@Override
	public List<Purchase> search(PurchaseSearchRequest searchRequest) {
		return genericDao.search(searchRequest::generateQuery, Purchase.class);
	}

}
