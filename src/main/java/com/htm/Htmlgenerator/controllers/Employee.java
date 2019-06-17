package com.htm.Htmlgenerator.controllers;

public class Employee {

	private String id;

	private String name;
	
	private int count;

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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isAnswerd() {
		return answerd;
	}
	public void setAnswerd(boolean answerd) {
		this.answerd = answerd;
	}

}
