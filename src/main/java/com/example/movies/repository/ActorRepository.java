package com.example.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movies.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>{
	Optional<Actor> findByName(String name);


}
