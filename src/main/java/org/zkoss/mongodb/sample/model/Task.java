package org.zkoss.mongodb.sample.model;

import java.util.Date;

/**
 * 
 * @author ashish
 *
 */

public class Task {
	private String id;
	private String name;
	private Integer priority;
	private Date executionDate;
	
	public Task(){ }
	
	public Task(String id,String name,int priority,Date date){
		this.id = id;
		this.name = name;
		this.priority = priority;
		this.setExecutionDate(date);
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	public Date getExecutionDate() {
		return executionDate;
	}
}
