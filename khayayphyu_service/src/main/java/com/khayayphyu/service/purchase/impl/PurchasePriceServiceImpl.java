package com.khayayphyu.service.purchase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.purchase.PurchasePriceDao;
import com.khayayphyu.entity.purchase.PurchasePrice;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.purchase.PurchasePriceService;
import com.khayayphyu.service.search.PurchasePriceSearchRequest;

@Service
public class PurchasePriceServiceImpl extends AbstractServiceImpl<PurchasePrice> implements PurchasePriceService {

	@Autowired
	private PurchasePriceDao purchasePriceDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Override
	protected CrudRepository<PurchasePrice, Long> getDao() {
		
		return purchasePriceDao;
	}

	@Override
	public Class<PurchasePrice> getTargetClass() {
		return PurchasePrice.class;
	}

	@Override
	public List<PurchasePrice> search(PurchasePriceSearchRequest searchRequest) {
		return genericDao.search(searchRequest::generateQuery, PurchasePrice.class);
	}

}
