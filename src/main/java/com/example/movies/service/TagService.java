package com.example.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.movies.model.Tag;
import com.example.movies.repository.TagRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TagService {
	
	@Autowired
    private TagRepository tagRepository;

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }    
   
    public Boolean addTag(Tag tag) {    
    	tagRepository.save(tag);
    	return true;
    } 
    
    public Boolean deleteById(Long id) {
        boolean isTagInDB;

        try {
          isTagInDB = tagRepository.existsById(id);

          if (!isTagInDB) {
            return false;
          }

          tagRepository.deleteById(id);
          return true;
        } catch (RuntimeException e) {
        	System.out.println(e.getLocalizedMessage());
          return false;
        }
    }
}
