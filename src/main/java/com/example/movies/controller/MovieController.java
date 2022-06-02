package com.example.movies.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.MovieService;


@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {
	
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private MovieService movieService;      
    
    
    @GetMapping("/get")
    public List<Movie> getMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return movies;
    }
    
    @PostMapping("/post")
    public String createMovie(@RequestBody Movie movie)  {
    	movieService.addMovie(movie);    	
        return ("Movie is valid");
    }
    
    @GetMapping("/movie")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.getByTitle(title));
    }  

    
    @GetMapping("/age/{restrictionAge}")
    public ResponseEntity<List<Movie>> getMoviesByRestrictionAge(@PathVariable Integer restrictionAge) {
        List<Movie> movies = movieService.findMoviesByRestrictionAge(restrictionAge);
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/id")
    public ResponseEntity<Movie> getMovieById(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie currentMovie = movieRepository.findById(id).orElseThrow(RuntimeException::new);
        currentMovie.setTitle(movie.getTitle());
        currentMovie.setDescription(movie.getDescription());
        currentMovie.setRestrictionAge(movie.getRestrictionAge());
        currentMovie.setActors(movie.getActors());
        currentMovie.setTags(movie.getTags());
        currentMovie = movieRepository.save(movie);
        return ResponseEntity.ok(currentMovie);
    }    
  
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
      Boolean isMovieDeleted = movieService.deleteById(id);

      if (!isMovieDeleted) {
        // ...
      }

      return "Movie deleted";
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
