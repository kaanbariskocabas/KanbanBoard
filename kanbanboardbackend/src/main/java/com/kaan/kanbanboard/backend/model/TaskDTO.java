package com.kaan.kanbanboard.backend.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kaan.kanbanboard.backend.model.type.StatusType;

/**
 * @author Kaan Kocabas
 *
 */
public class TaskDTO {

	private Long id;

	private Long userId;

	private String title;

	private String content;

	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	private Timestamp endDate;

	private StatusType status = StatusType.TODO;

	private boolean isCompleted = false;

	public TaskDTO() {
	}

	/**
	 * Convert Todo to TodoDTO
	 * 
	 * @param id
	 * @param title
	 * @param content
	 * @param creationTime
	 * @param endDate
	 * @param isCompleted
	 */
	public TaskDTO(Long id, String title, String content, Timestamp creationTime, Timestamp endDate,
			boolean isCompleted) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.creationTime = creationTime;
		this.endDate = endDate;
		this.isCompleted = isCompleted;
	}

	public TaskDTO(Long id, Long userId, String title, String content, Timestamp creationTime, Timestamp endDate,
			StatusType status, boolean isCompleted) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.creationTime = creationTime;
		this.endDate = endDate;
		this.status = status;
		this.isCompleted = isCompleted;
	}

	/**
	 * @param id
	 * @param title
	 * @param content
	 * @param creationTime
	 * @param endDate
	 * @param isCompleted
	 */
	public TaskDTO(Task todo) {
		super();
		this.id = todo.getId();
		this.userId = todo.getUser().getUserId();
		this.title = todo.getTitle();
		this.content = todo.getContent();
		this.creationTime = todo.getCreationTime();
		this.endDate = todo.getEndDate();
		this.status = todo.getStatus();
		this.isCompleted = todo.isCompleted();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the creationTime
	 */
	public Timestamp getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the endDate
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
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

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

}
