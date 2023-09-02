package com.khayayphyu.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.purchase.ItemDao;
import com.khayayphyu.entity.product.Item;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.product.ItemService;

@Service
public class ItemServiceImpl extends AbstractServiceImpl<Item> implements ItemService {

	@Autowired
	private ItemDao itemDao;
	
	@Override
	protected CrudRepository<Item, Long> getDao() {
		return itemDao;
	}

	@Override
	public Class<Item> getTargetClass() {
		return Item.class;
	}

}
