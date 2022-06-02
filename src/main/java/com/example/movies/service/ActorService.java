package com.example.movies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.movies.model.Actor;
import com.example.movies.repository.ActorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActorService {
	
	@Autowired
    private ActorRepository actorRepository;

    public List<Actor> getActors() {
        return actorRepository.findAll();
    }    

    public Boolean addActor(Actor actor) {    
    	actorRepository.save(actor);
    	return true; 
    }
    
    public Boolean deleteActor(Long id) {
        boolean isActorInDB;

        try {
          isActorInDB = actorRepository.existsById(id);

          if (!isActorInDB) {
            return false;
          }
          
          actorRepository.deleteActorById(id);
          actorRepository.deleteById(id);
          return true;
        } catch (RuntimeException e) {
        	System.out.println(e.getLocalizedMessage());
          return false;
        }
    }
}
