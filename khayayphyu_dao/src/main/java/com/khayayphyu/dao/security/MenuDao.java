package com.khayayphyu.dao.security;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.khayayphyu.entity.security.Menu;

@Repository
public interface MenuDao extends CrudRepository<Menu, Long> {

	@Query(value="SELECT m.permission FROM Menu m JOIN UserProfile_Menu upm ON upm.Menu_id = m.id WHERE upm.UserProfile_Id=:userProfileId and m.permission in (:requestPermission)", nativeQuery=true)
	public List<String> getPermission(Long userProfileId, String[] requestPermission);
}
