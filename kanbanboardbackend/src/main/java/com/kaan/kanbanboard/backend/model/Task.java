package com.kaan.kanbanboard.backend.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaan.kanbanboard.backend.model.type.StatusType;

/**
 * @author Kaan Kocabas
 *
 */
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String content;

	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	private Timestamp endDate;

	@Enumerated(EnumType.STRING)
	private StatusType status = StatusType.TODO;

	private boolean isCompleted = false;

	public Task() {
	}

	/**
	 * @param title
	 * @param content
	 * @param creationTime
	 * @param endDate
	 * @param isCompleted
	 */
	public Task(String title, String content, Timestamp creationTime, Timestamp endDate, boolean isCompleted) {
		super();
		this.title = title;
		this.content = content;
		this.creationTime = creationTime;
		this.endDate = endDate;
		this.isCompleted = isCompleted;
	}
	
	/**
	 * @param title
	 * @param content
	 * @param endDate
	 */
	public Task(String title, String content, Timestamp endDate) {
		this.title = title;
		this.content = content;
		this.endDate = endDate;
	}

	/**
	 * @param title
	 * @param content
	 */
	public Task(String title, String content) {
		this.title = title;
		this.content = content;
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
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	public Task taskDTOtoTask(TaskDTO taskDto) {
		if (taskDto != null) {
			if(taskDto.getContent() != null) this.content = taskDto.getContent();
			this.endDate = taskDto.getEndDate();
			this.isCompleted = taskDto.isCompleted();
			if(taskDto.getStatus() != null) this.status = taskDto.getStatus();
			if(taskDto.getTitle() != null) this.title = taskDto.getTitle();
		}
		return this;
	}
}
