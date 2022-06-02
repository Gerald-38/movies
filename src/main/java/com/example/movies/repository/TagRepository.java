package com.example.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movies.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>  {
	Optional<Tag> findByName(String name);

}
