package com.example.movies.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.movies.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>{
	Optional<Actor> findByName(String name);
	
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value ="DELETE FROM movie_actors WHERE actor_id = :id")
    public void deleteActorById(@Param("id") Long  id);
    
    public void deleteById(Long id);


}
