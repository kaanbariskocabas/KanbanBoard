package com.kaan.kanbanboard.backend.controller;

 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaan.kanbanboard.backend.model.Task;
import com.kaan.kanbanboard.backend.model.TaskDTO;
import com.kaan.kanbanboard.backend.service.task.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Kaan Kocabas
 *
 */
@RestController
@Api("Task")
@RequestMapping("/api")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@GetMapping("/task")
	@ApiOperation(value = "Finds All The Tasks", notes = "Provides all the tasks which are available", response = TaskDTO.class)
	public @ResponseBody List<TaskDTO> findAll() {
//		return taskService.findAll();
		return taskService.findAllTasksOfUser();
	}
	
	@GetMapping("/task/{taskId}")
	public @ResponseBody Task findById(@PathVariable("taskId") Long taskId) {
		return taskService.findById(taskId);
	}
	
	@PutMapping("/task/{taskId}")
	public @ResponseBody Task editTask(@PathVariable("taskId") Long taskId,@RequestBody TaskDTO task) {
		return taskService.editTask(task, taskId);
	}
	
	@DeleteMapping("/task/{taskId}")
	public @ResponseBody void deleteTask(@PathVariable("taskId") Long taskId) {
		taskService.deleteTask(taskId);
	}
	
	@PutMapping("/task/{taskId}/{userId}")
	public @ResponseBody void deleteTask(@PathVariable("taskId") Long taskId, @PathVariable("userId") Long userId) {
		taskService.changeUser(userId, taskId);
	}
}