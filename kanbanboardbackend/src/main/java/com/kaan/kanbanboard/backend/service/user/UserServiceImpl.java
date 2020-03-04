package com.kaan.kanbanboard.backend.service.user;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaan.kanbanboard.app.security.SecurityUtils;
import com.kaan.kanbanboard.backend.model.Role;
import com.kaan.kanbanboard.backend.model.Task;
import com.kaan.kanbanboard.backend.model.User;
import com.kaan.kanbanboard.backend.repository.RoleRepository;
import com.kaan.kanbanboard.backend.repository.TaskRepository;
import com.kaan.kanbanboard.backend.repository.UserRepository;

/**
 * @author Kaan Kocabas
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private TaskRepository taskRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.taskRepository = taskRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public User findLoggedInUser() {
		if(SecurityUtils.isAuthenticated())
		{
			return userRepository.findOneByEmailIgnoreCase(SecurityUtils.getUsername());
		}
		return null;
	}
	
	@Override
	public User addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public List<Task> findAllTasks(Long userId) {
		return userRepository.findById(userId).get().getTasks();
	}

	@Override
	public User findById(Long userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(Long userId, User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public void deleteRole(Long roleId, Long userId) {
		User user = findById(userId);
		user.deleteRole(roleRepository.findById(roleId).get());
		userRepository.save(user);
	}

	@Override
	public Set<Role> addRole(Long roleId, Long userId) {
		User user = findById(userId);
		user.addRole(roleRepository.findById(roleId).get());
		return userRepository.save(user).getRoles();
	}

	@Override
	public void deleteTask(Long taskId, Long userId) {
		User user = findById(userId);
		user.deleteTodo(user.findTaskById(taskId));
		userRepository.save(user);
	}

	@Override
	public Task editTask(Task task, Long userId) {
		User user = findById(userId);
		user.editTask(task);
		return userRepository.save(user).findTaskById(task.getId());
	}

	@Override
	public List<Task> addTask(Task task, Long userId) {
		User user = findById(userId);
		user.addTask(task);
		return userRepository.save(user).getTasks();
	}

	@Override
	public Task findTaskById(Long taskId, Long userId) {
		return findById(userId).findTaskById(taskId);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findOneByEmailIgnoreCase(email);
	}

}
