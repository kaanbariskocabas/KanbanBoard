package com.kaan.kanbanboard.backend.service.user;

import java.util.List;
import java.util.Set;

import com.kaan.kanbanboard.backend.model.Role;
import com.kaan.kanbanboard.backend.model.Task;
import com.kaan.kanbanboard.backend.model.User;

/**
 * @author Kaan Kocabas
 *
 */
public interface UserService {

public User addUser(User user);
	
	public List<Task> findAllTasks(Long userId);
	
	public User findById(Long userId);
	
	public User findLoggedInUser();
	
	public List<User> findAll();
	
	public User updateUser(Long userId, User user);
	
	public void deleteUser(Long userId);

	/**
	 * Delete the role
	 * 
	 * @param roleId
	 * @param userId
	 *
	 */
	void deleteRole(Long roleId, Long userId);

	/**
	 * @param role
	 * @param userId
	 * @return
	 *
	 */
	Set<Role> addRole(Long roleId, Long userId);

	/**
	 * @param TaskId
	 * @param userId
	 *
	 */
	void deleteTask(Long taskId, Long userId);

	/**
	 * @param Task
	 * @param userId
	 * @return Task if edited
	 *
	 */
	Task editTask(Task task, Long userId);

	/**
	 * @param Task
	 * @param userId
	 * @return Task if added
	 *
	 */
	List<Task> addTask(Task task, Long userId);

	/**
	 * @param TaskId
	 * @return
	 *
	 */
	public Task findTaskById(Long taskId, Long userId);

	/**
	 * @param email
	 * @return
	 *
	 */
	User findByEmail(String email);
	
}
