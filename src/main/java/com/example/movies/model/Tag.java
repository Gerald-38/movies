package com.example.movies.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.context.annotation.Lazy;

import lombok.Data;

import com.example.movies.model.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity (name = "tag")
public class Tag {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(name = "name")
    private String name;	

    @JsonBackReference
	@ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
	private List<Movie> movies;
    
    
    // CONSTRUCTORS
	
	public Tag() {	}

	public Tag(Long id, String name, List<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
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

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}	

}
