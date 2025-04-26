//all imports are changes because the versions are been changed
package com.practice.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@javax.persistence.Entity
public class Student {

	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="name",nullable=false,unique=true) //for exceptions to check
	private String name;
	
	private String email;
	@Size(min = 4, message = " name too short") ////validation remeber these terms valid ,binding result
	// if (result.hasErrors()) { return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);}
	private String course;
	//for sorting we should remember name ,default value and required
	private int mobile;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	
	
}
