package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.MovieService;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@SpringBootTest
public class MovieServiceTest {
	
	@Mock
	MovieRepository movieRepository;
	
	@Autowired
	MovieService movieService;
	
	@Test
	void Create() {
		Movie movie1 = new Movie();
		movie1.setTitle("Title");
		movie1.setDescription("this is a description");
		movie1.setRestrictionAge(10);
		
		Boolean movie = movieService.addMovie(movie1);
		Assertions.assertTrue(movie);
	}

	

}
