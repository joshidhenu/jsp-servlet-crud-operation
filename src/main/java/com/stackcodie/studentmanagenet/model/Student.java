package com.stackcodie.studentmanagenet.model;

public class Student {
	private int id;
	private String name;
	private String email;
	private String standard;
	private String gender;
	
	public Student(String name, String email, String standard, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.standard = standard;
		this.gender = gender;
	}
	
	public Student() {
	}
	
	public Student(int id, String name, String email, String standard, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.standard = standard;
		this.gender = gender;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
