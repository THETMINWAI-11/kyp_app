package com.khayayphyu.service.search;

import com.khayayphyu.entity.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserSearchRequest implements SearchRequest<User> {
	
	private String name;
	private StringBuffer buffer;
	
	@Override
	public String generateQuery() {
		buffer.append(generateBaseQuery());
		addName();
		return buffer.toString();
	}

	@Override
	public String generateBaseQuery() {
		return "select u from User u where 1 = 1";
	}
	
	private void addName() {
		buffer.append(" and upper(u.name) like upper('%" + name + "')");
	}

}
