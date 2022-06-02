package com.example.movies.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.model.Actor;
import com.example.movies.model.Movie;
import com.example.movies.model.Tag;
import com.example.movies.repository.ActorRepository;
import com.example.movies.service.ActorService;

@CrossOrigin
@RestController
@RequestMapping("/actors")
public class ActorController {
	
    @Autowired
    private ActorRepository actorRepository;
    
    @Autowired
    private ActorService actorService;      
    
    
    @GetMapping("/get")
    public List<Actor> getActors(Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);
        return actors;
    }
    
    @GetMapping("getMovieByActor")
    public ResponseEntity<Object> getMovieByActor(@RequestParam String actorName) {
    	
    	final Optional<Actor> optionalActor = actorRepository.findByName(actorName);
    	List<Movie> movies;
    	
    	if (optionalActor.isPresent()) {
    		
    		movies = optionalActor.get().getMovies();
    		
    		if (movies.isEmpty()) {
    			return ResponseEntity.ok().body("Aucun film trouvé");
    		}
    		
    		return ResponseEntity.ok().body(movies);
    	}
    	
    	return ResponseEntity.notFound().build();    
    }
    
    @PostMapping("/post")
    public String createActor(@RequestBody Actor actor)  {
    	actorService.addActor(actor);    	
        return ("Actor is valid");
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity updateActor(@PathVariable Long id, @RequestBody Actor actor) {
        Actor currentActor = actorRepository.findById(id).orElseThrow(RuntimeException::new);
        currentActor.setName(actor.getName());
        currentActor.setAge(actor.getAge());
        currentActor = actorRepository.save(actor);
        return ResponseEntity.ok(currentActor);
    }  
        
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
      Boolean isActorDeleted = actorService.deleteById(id);

      if (!isActorDeleted) {
        // ...
      }

      return "Actor deleted";
    } 

}
