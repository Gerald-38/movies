package com.example.movies.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.metrics.StartupStep.Tags;

import com.example.movies.model.Actor;
import com.example.movies.model.Tag;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Entity (name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;  
    
    @NotNull
    @Column(name = "restriction_age")
    private Integer restrictionAge;       

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "movie_actors",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;
    
    
//  @JsonManagedReference 
	@ManyToMany
	@JoinTable(
	  name = "tag_movies",
	  joinColumns = @JoinColumn(name = "movie_id"),
	inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	
	
	// CONSTRUCTORS    

	public Movie() {

	}
	
	public Movie(Long id, String title, String description, Integer restrictionAge, List<Actor> actors,
			List<Tag> tags) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.restrictionAge = restrictionAge;
		this.actors = actors;
		this.tags = tags;
	}
	
	
	// GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRestrictionAge() {
		return restrictionAge;
	}

	public void setRestrictionAge(Integer restrictionAge) {
		this.restrictionAge = restrictionAge;
	}
	
	public List<Actor> getActors() {
		return actors;
	}
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
