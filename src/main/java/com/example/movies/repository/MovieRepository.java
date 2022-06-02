package com.example.movies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movies.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	Optional<Movie> findById(Integer id);
	Optional<Movie> findByTitle(String title);		
	Optional<List<Movie>> findByRestrictionAge(Integer restrictionAge);
}
