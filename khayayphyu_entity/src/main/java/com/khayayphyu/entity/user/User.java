package com.khayayphyu.entity.user;

import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.utils.type.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "User")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity{
	@Column(name = "name")
	private String userName;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;
}
