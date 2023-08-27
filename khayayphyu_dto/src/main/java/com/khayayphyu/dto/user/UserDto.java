package com.khayayphyu.dto.user;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.entity.user.User;
import com.khayayphyu.utils.type.UserRole;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractDto<User> {

	private String userName;
	private UserRole role;

	@Override
	public User toEntity() {
		User user = new User();
		user.setId(getId());
		user.setUserName(userName);
		user.setRole(role);
		return user;
	}

	public static UserDto create(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.userName = user.getUserName();
		userDto.role = user.getRole();
		return userDto;
	}

	public static UserDto createFromId(Long id) {
		if (id == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		userDto.setId(id);
		return userDto;
	}

}
