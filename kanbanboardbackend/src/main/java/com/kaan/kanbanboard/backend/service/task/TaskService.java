package com.kaan.kanbanboard.backend.service.task;

import java.util.List;

import com.kaan.kanbanboard.backend.model.Task;
import com.kaan.kanbanboard.backend.model.TaskDTO;
import com.kaan.kanbanboard.backend.model.UserDTO;

/**
 * @author Kaan Kocabas
 *
 */
public interface TaskService {

	public List<TaskDTO> findAll();
	
	public List<TaskDTO> findAllTasksOfUser();
	
	public Task findById(Long id);
	
	public void deleteTask(Long taskId);
	
	public Task changeUser(Long userId, Long taskId);

	Task editTask(TaskDTO taskDto, Long taskId);
	
}
