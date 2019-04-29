package com.htm.Htmlgenerator.controllers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="employee")
	public class Employee {

	@Id
	@Column(name="Id")
	private String id;

	@Column(name="name")
	private String name;

	@Column(name="answerd")
	private boolean answerd;


	
	
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
	public boolean isAnswerd() {
		return answerd;
	}
	public void setAnswerd(boolean answerd) {
		this.answerd = answerd;
	}

}
