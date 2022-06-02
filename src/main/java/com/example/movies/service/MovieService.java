package com.example.movies.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieService {

	@Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }    

    public Boolean addMovie(Movie movie) {    
    	movieRepository.save(movie);
    	return true; 
    }
    
    public Movie getByTitle(String title) {
    	return movieRepository.findByTitle(title).orElseThrow();    	
    }
    
    public List<Movie> findMoviesByRestrictionAge(Integer restrictionAge) {
        List<Movie> allMovies = getMovies();
        return allMovies.stream().filter(movie -> movie.getRestrictionAge()<=restrictionAge).collect(Collectors.toList());
    }
    
    public Movie getById(Long id) {
    	return movieRepository.findById(id).orElseThrow();    	
    } 
    
    public Boolean deleteById(Long id) {
        boolean isMovieInDB;

        try {
          isMovieInDB = movieRepository.existsById(id);

          if (!isMovieInDB) {
            return false;
          }

          movieRepository.deleteById(id);
          return true;
        } catch (RuntimeException e) {
        	System.out.println(e.getLocalizedMessage());
          return false;
        }
    }
}
