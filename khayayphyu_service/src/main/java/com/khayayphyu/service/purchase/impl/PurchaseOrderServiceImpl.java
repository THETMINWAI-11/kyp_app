package com.khayayphyu.service.purchase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.purchase.PurchaseOrderDao;
import com.khayayphyu.entity.purchase.PurchaseOrder;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.purchase.PurchaseOrderService;
import com.khayayphyu.service.search.PurchaseOrderSearchRequest;

@Service
public class PurchaseOrderServiceImpl extends AbstractServiceImpl<PurchaseOrder> implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	
	@Autowired
	private GenericDao genericDao;

	@Override
	protected CrudRepository<PurchaseOrder, Long> getDao() {
		return purchaseOrderDao;
	}

	@Override
	public Class<PurchaseOrder> getTargetClass() {
		return PurchaseOrder.class;
	}
	
	@Override
	public List<PurchaseOrder> search(PurchaseOrderSearchRequest searchRequest) {
		return genericDao.search(searchRequest::generateQuery, PurchaseOrder.class);
	}

}
