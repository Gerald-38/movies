package com.example.movies.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;

import com.example.movies.model.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity (name = "actor")
public class Actor {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @NotNull(message = "Le nom ne peut pas être nul")
    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private Integer age;  
    
    @JsonBackReference
    @ManyToMany(mappedBy="actors", cascade = CascadeType.MERGE)
    private List<Movie> movies;     
    
    
    // CONSTRUCTORS
    
	public Actor() {

	}

	public Actor(Long id, String name, Integer age, List<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.movies = movies;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
    

}
