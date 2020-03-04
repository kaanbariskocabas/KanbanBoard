package com.kaan.kanbanboard.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaan.kanbanboard.app.security.SecurityUtils;
import com.kaan.kanbanboard.backend.model.Role;
import com.kaan.kanbanboard.backend.model.Task;
import com.kaan.kanbanboard.backend.model.User;
import com.kaan.kanbanboard.backend.model.UserDTO;
import com.kaan.kanbanboard.backend.service.user.UserService;

/**
 * @author Kaan Kocabas
 *
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/me")
	public @ResponseBody UserDTO getUser() {
		return new UserDTO(SecurityUtils.getCurrentUser(userService));
	}

	@GetMapping("/try")
	public @ResponseBody String tryMe() {
		return "try me authenticated!";
	}

	@GetMapping("/secured/try")
	public @ResponseBody String tryMeAuth() {
		return "try me secured authenticated!";
	}

	@GetMapping("/findall")
	public @ResponseBody ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

//	@GetMapping("/task/{taskId}/{userId}")
//	public @ResponseBody ResponseEntity<Task> findTaskById(@PathVariable("taskId") Long taskId, @PathVariable("userId") Long userId) {
//		return new ResponseEntity<>(userService.findTaskById(taskId, userId), HttpStatus.OK);
//	}

	@PostMapping("/role/{roleId}/{userId}")
	public @ResponseBody ResponseEntity<Set<Role>> addRole(@PathVariable("roleId") Long roleId,
			@PathVariable("userId") Long userId) {
		return new ResponseEntity<>(userService.addRole(roleId, userId), HttpStatus.OK);
	}

	@PostMapping("/user")
	public @ResponseBody ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
	}

	@PostMapping("/task/{userId}")
	public @ResponseBody ResponseEntity<List<Task>> addTask(@RequestBody Task task,
			@PathVariable("userId") Long userId) {
		return new ResponseEntity<>(userService.addTask(task, userId), HttpStatus.OK);
	}

	@DeleteMapping("/user/{userId}")
	public @ResponseBody ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/role/{roleId}/{userId}")
	public @ResponseBody ResponseEntity<Void> deleteRole(@PathVariable("roleId") Long roleId,
			@PathVariable("userId") Long userId) {
		userService.deleteRole(roleId, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@DeleteMapping("/task/{taskId}/{userId}")
//	public @ResponseBody ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId, @PathVariable("userId") Long userId) {
//		userService.deleteTask(taskId, userId);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@PutMapping("/task/{userId}")
//	public @ResponseBody ResponseEntity<Task> editTask(@RequestBody Task task, @PathVariable("userId") Long userId) {
//		return new ResponseEntity<>(userService.editTask(task, userId), HttpStatus.OK);
//	}
}
