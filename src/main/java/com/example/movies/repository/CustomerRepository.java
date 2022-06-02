package com.example.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movies.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	Optional<Customer> findById(Integer Id);


}
