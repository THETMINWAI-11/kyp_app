package com.khayayphyu.service.user.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.khayayphyu.dao.GenericDao;
import com.khayayphyu.dao.user.UserDao;
import com.khayayphyu.dto.user.UserDto;
import com.khayayphyu.entity.user.User;
import com.khayayphyu.service.impl.AbstractServiceImpl;
import com.khayayphyu.service.search.UserSearchRequest;
import com.khayayphyu.service.user.UserService;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDto> implements UserService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private GenericDao genericDao;

	@Override
	protected CrudRepository<User, Long> getDao() {
		return userDao;
	}

	@Override
	public Class<User> getTargetClass() {
		return User.class;
	}

	@Override
	public Function<User, UserDto> getDtoConvertor() {
		
		return UserDto::create;
	}

	@Override
	public List<UserDto> search(UserSearchRequest searchRequest) {	
		return toDtos(genericDao.search(searchRequest::generateQuery, User.class));
	}

}
