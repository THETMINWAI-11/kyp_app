package com.khayayphyu.dao.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khayayphyu.entity.user.User;

@Repository
@Transactional
@EnableJpaRepositories
public interface UserDao extends CrudRepository<User, Long> {
	
	@Query("select u from User u where u.id = :id")
	User getByUserId(Long id);
	
	@Query("select u from User u where u.name = :name")
	String getByUserName(String name);

}
