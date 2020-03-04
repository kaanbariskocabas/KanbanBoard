package com.kaan.kanbanboard.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaan.kanbanboard.backend.model.Task;

/**
 * @author Kaan Kocabas
 *
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}
