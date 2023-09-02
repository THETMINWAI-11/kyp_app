package com.khayayphyu.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.security.MenuDao;
import com.khayayphyu.entity.security.Menu;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.security.MenuService;

@Service
public class MenuServiceImpl extends AbstractServiceImpl<Menu> implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	protected CrudRepository<Menu, Long> getDao() {
		return menuDao;
	}

	@Override
	public Class<Menu> getTargetClass() {
		return Menu.class;
	}

}
