package com.kaan.kanbanboard.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String firstname;

	private String lastname;

	@NotEmpty(message = "*Please provide your password")
	private String password;

	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	public boolean isActive = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "user_id")
	private List<Task> tasks = new ArrayList<>();

	public User() {
	}

	public User(String password, String email) {
		this.password = password;
		this.email = email;
		roles.add(new Role());
	}

	public User(String firstname, String lastname, String password, String email, Set<Role> roles) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public User(String firstname, String lastname, String password, String email, Set<Role> roles, List<Task> todos) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.roles = roles;
		todos.forEach(e -> addTask(e));
	}

	public User(User user) {
		this.isActive = user.isActive();
		this.userId = user.getUserId();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.roles = user.getRoles();
		user.getTasks().forEach(e -> addTask(e));
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
		tasks.forEach(e -> addTask(e));
	}

	public void addTask(Task task) {
		task.setUser(this);
		this.tasks.add(task);
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void deleteTodo(Task task) {
		if (tasks.contains(task)) {
			this.tasks.remove(task);
		}
	}

	public void deleteRole(Role role) {
		if (roles.contains(role)) {
			this.roles.remove(role);
		}
	}

	public Task findTaskById(Long todoId) {
		return tasks.stream().filter(x -> todoId == x.getId()).findAny().orElse(null);
	}

	public Role findRoleById(Long roleId) {
		return roles.stream().filter(x -> roleId == x.getRoleId()).findAny().orElse(null);
	}

	public void editTask(Task task) {
		tasks.remove(findTaskById(task.getId()));
		addTask(task);
	}

}
