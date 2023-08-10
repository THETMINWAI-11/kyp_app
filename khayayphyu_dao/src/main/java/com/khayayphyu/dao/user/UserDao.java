package com.khayayphyu.dao.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.khayayphyu.entity.user.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	
	@Query("select u from User u where u.id = :id")
	User getByUserId(Long id);
	
	@Query("select u from User u where u.userName = :name")
	String getByUserName(String name);

}
