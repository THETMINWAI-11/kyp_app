package com.khayayphyu.service.user;

import java.util.List;

import com.khayayphyu.entity.user.User;
import com.khayayphyu.service.AbstractService;
import com.khayayphyu.service.search.UserSearchRequest;

public interface UserService extends AbstractService<User>{
	public List<User> search(UserSearchRequest searchRequest);
}
