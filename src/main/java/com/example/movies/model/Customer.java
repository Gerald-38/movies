package com.example.movies.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity(name = "customer")
public class Customer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @NotNull(message = "Le nom ne peut pas être nul")
    @Column(name = "name")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull
    @Column(name = "age")
    private Integer age;
    
    
    // CONSTRUCTORS 
    
	public Customer() {

	}
    
	public Customer(Long id, String name, String email, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	
	// GETTERS AND SETTERS

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	
	
	
    
    
    


}
