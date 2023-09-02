package com.khayayphyu.service.sale.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.sale.SaleOrderDao;
import com.khayayphyu.entity.sale.SaleOrder;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.sale.SaleOrderService;
import com.khayayphyu.service.search.SaleOrderSearchRequest;

@Service
public class SaleOrderServiceImpl extends AbstractServiceImpl<SaleOrder> implements SaleOrderService {

	@Autowired
	private SaleOrderDao saleOrderDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Override
	protected CrudRepository<SaleOrder, Long> getDao() {
		return saleOrderDao;
	}

	@Override
	public Class<SaleOrder> getTargetClass() {
		return SaleOrder.class;
	}

	@Override
	public List<SaleOrder> search(SaleOrderSearchRequest searchRequest) {
		return genericDao.search(searchRequest::generateQuery, SaleOrder.class);
	}

}
