package com.kaan.kanbanboard.backend.service.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.stereotype.Service;

import com.kaan.kanbanboard.backend.model.Task;
import com.kaan.kanbanboard.backend.model.TaskDTO;
import com.kaan.kanbanboard.backend.model.User;
import com.kaan.kanbanboard.backend.repository.TaskRepository;
import com.kaan.kanbanboard.backend.service.user.UserService;

/**
 * @author Kaan Kocabas
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserService userService;

	@Override
	public List<TaskDTO> findAll() {
		List<TaskDTO> tasks = new ArrayList<TaskDTO>();
		taskRepository.findAll().forEach(e -> tasks.add(new TaskDTO(e)));
		return tasks;
	}

	@Override
	public List<TaskDTO> findAllTasksOfUser() {
		List<TaskDTO> tasks = new ArrayList<TaskDTO>();
		User user = userService.findLoggedInUser();
		if (user != null)
			user.getTasks().forEach(e -> tasks.add(new TaskDTO(e)));
		return tasks;
	}

	@Override
	public Task findById(Long id) {
		return taskRepository.findById(id).get();
	}

	@Override
	public Task editTask(TaskDTO taskDto, Long taskId) {
		Task task = taskRepository.findById(taskId).get();
		return taskRepository.save(task.taskDTOtoTask(taskDto));
	}

	@Override
	public void deleteTask(Long taskId) {
		taskRepository.deleteById(taskId);
	}

	@Override
	public Task changeUser(Long userId, Long taskId) {
		User user = userId == null ? null : userService.findById(userId);
		Task task = findById(taskId);
		if (task != null) {
			task.setUser(user);
			return taskRepository.save(task);
		}
		return task;
	}

}
