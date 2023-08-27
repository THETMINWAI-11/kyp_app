package com.khayayphyu.service.purchase.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.purchase.PurchaseDao;
import com.khayayphyu.dto.purchase.PurchaseDto;
import com.khayayphyu.entity.purchase.Purchase;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.purchase.PurchaseService;
import com.khayayphyu.service.search.PurchaseSearchRequest;

@Service
public class PurchaseServiceImpl extends AbstractServiceImpl<Purchase, PurchaseDto> implements PurchaseService {

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
	public Function<Purchase, PurchaseDto> getDtoConvertor() {
		return PurchaseDto::create;
	}

	@Override
	public List<PurchaseDto> search(PurchaseSearchRequest searchRequest) {
		return toDtos(genericDao.search(searchRequest::generateQuery, Purchase.class));
	}

}
