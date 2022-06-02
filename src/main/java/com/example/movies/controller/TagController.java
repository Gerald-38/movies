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

import com.example.movies.model.Movie;
import com.example.movies.model.Tag;
import com.example.movies.repository.TagRepository;
import com.example.movies.service.TagService;

@CrossOrigin
@RestController
@RequestMapping("/tags")
public class TagController {
	
	@Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private TagService tagService;      
    
    
    @GetMapping("/get")
    public List<Tag> getTags(Model model) {
        List<Tag> tags = tagService.getTags();
        model.addAttribute("tags", tags);
        return tags;
    }
    
    @GetMapping("getMovieByTag")
    public ResponseEntity<Object> getMovieByTag(@RequestParam String tagName) {
    	
    	final Optional<Tag> optionalTag = tagRepository.findByName(tagName);
    	List<Movie> movies;
    	
    	if (optionalTag.isPresent()) {
    		
    		movies = optionalTag.get().getMovies();
    		
    		if (movies.isEmpty()) {
    			return ResponseEntity.ok().body("Aucun film trouvé");
    		}
    		
    		return ResponseEntity.ok().body(movies);
    	}
    	
    	return ResponseEntity.notFound().build();    
    }
    
    @PostMapping("/post")
    public String createTag(@RequestBody Tag tag)  {
    	tagService.addTag(tag);    	
        return ("Tag is valid");
    }   
    
    @PutMapping("/update/{id}")
    public ResponseEntity updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        Tag currentTag = tagRepository.findById(id).orElseThrow(RuntimeException::new);
        currentTag.setName(tag.getName());
        currentTag = tagRepository.save(tag);
        return ResponseEntity.ok(currentTag);
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
      Boolean isTagDeleted = tagService.deleteById(id);
      if (!isTagDeleted) {
        return "Tag does not exist!";
      }
      return "Tag deleted";
    } 

}
