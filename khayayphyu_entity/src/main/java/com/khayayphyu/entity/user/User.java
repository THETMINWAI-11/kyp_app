package com.khayayphyu.entity.user;

import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.utils.type.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "User")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {
	@Column(name = "name")
	private String userName;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private Status status;

	public boolean isAdministrator() {
		return role == UserRole.ADMIN;
	}

	public enum Status {
		ACTIVE, INACTIVE;

		public String getName() {
			return this.name();
		}
	}

	@PrePersist
	public void prepersist() {
		if (status == null)
			status = Status.ACTIVE;
	}

	@PreUpdate
	public void preupdate() {
		if (status == null)
			status = Status.ACTIVE;
	}
}
