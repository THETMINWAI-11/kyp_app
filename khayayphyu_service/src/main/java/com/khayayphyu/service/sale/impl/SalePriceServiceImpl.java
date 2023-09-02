package com.khayayphyu.service.sale.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.sale.SalePriceDao;
import com.khayayphyu.entity.sale.SalePrice;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.sale.SalePriceService;
import com.khayayphyu.service.search.SalePriceSearchRequest;

@Service
public class SalePriceServiceImpl extends AbstractServiceImpl<SalePrice> implements SalePriceService {

	@Autowired
	private SalePriceDao salePriceDao;

	@Autowired
	private GenericDao genericDao;

	@Override
	protected CrudRepository<SalePrice, Long> getDao() {
		return salePriceDao;
	}

	@Override
	public Class<SalePrice> getTargetClass() {
		return SalePrice.class;
	}

	@Override
	public List<SalePrice> search(SalePriceSearchRequest searchRequest) {
		return genericDao.search(searchRequest::generateQuery, SalePrice.class);
	}

}
