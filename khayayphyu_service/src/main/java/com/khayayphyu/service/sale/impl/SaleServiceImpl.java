package com.khayayphyu.service.sale.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.sale.SaleDao;
import com.khayayphyu.entity.sale.Sale;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.sale.SaleService;
import com.khayayphyu.service.search.SaleSearchRequest;

@Service
public class SaleServiceImpl extends AbstractServiceImpl<Sale> implements SaleService {

	@Autowired
	private SaleDao saleDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Override
	protected CrudRepository<Sale, Long> getDao() {
		return saleDao;
	}

	@Override
	public Class<Sale> getTargetClass() {
		return Sale.class;
	}

	@Override
	public List<Sale> search(SaleSearchRequest searchRequest) {
		return genericDao.search(searchRequest::generateQuery, Sale.class);
	}

}
