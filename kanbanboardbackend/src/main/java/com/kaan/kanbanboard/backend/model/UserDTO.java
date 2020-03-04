package com.kaan.kanbanboard.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Kaan Kocabas
 *
 */
public class UserDTO {

	private Long userId;
	private String firstname;
	private String lastname;
	private String email;
	private Set<Role> roles;
	private List<Task> tasks = new ArrayList<Task>();
	private boolean isActive;

	public UserDTO() {
	}

	/**
	 * Converts User to UserDTO
	 * 
	 * @param user
	 */
	public UserDTO(User user) {
		super();
		if (user != null) {
			this.userId = user.getUserId();
			this.firstname = user.getFirstname();
			this.lastname = user.getLastname();
			this.email = user.getEmail();
			this.roles = user.getRoles();
			this.isActive = user.isActive();
			this.tasks = user.getTasks();
		}
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
