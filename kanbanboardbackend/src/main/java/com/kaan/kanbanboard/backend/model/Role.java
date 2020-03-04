package com.kaan.kanbanboard.backend.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kaan.kanbanboard.backend.model.type.RoleType;

/**
 * @author Kaan Kocabas
 *
 */
@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long roleId;

	@Enumerated(EnumType.STRING)
	private RoleType role = RoleType.USER;

	public Role() {
	}

	/**
	 * @param roleType
	 */
	public Role(RoleType roleType) {
		this.role = roleType;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the role
	 */
	public RoleType getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleType role) {
		this.role = role;
	}

}
