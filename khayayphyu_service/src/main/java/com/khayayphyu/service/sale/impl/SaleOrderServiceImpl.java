package com.khayayphyu.service.sale.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.sale.SaleOrderDao;
import com.khayayphyu.dto.sale.SaleOrderDto;
import com.khayayphyu.entity.sale.SaleOrder;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.sale.SaleOrderService;
import com.khayayphyu.service.search.SaleOrderSearchRequest;

@Service
public class SaleOrderServiceImpl extends AbstractServiceImpl<SaleOrder, SaleOrderDto> implements SaleOrderService {

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
	public Function<SaleOrder, SaleOrderDto> getDtoConvertor() {
		return SaleOrderDto::create;
	}

	@Override
	public List<SaleOrderDto> search(SaleOrderSearchRequest searchRequest) {
		return toDtos(genericDao.search(searchRequest::generateQuery, SaleOrder.class));
	}

}
