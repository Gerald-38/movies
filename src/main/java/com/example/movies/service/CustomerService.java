package com.example.movies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movies.model.Customer;
import com.example.movies.repository.CustomerRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;
	
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }    
   
    public Boolean addCustomer(Customer customer) {    
    	customerRepository.save(customer);
    	return true;
    }
    
    public Boolean deleteById(Long id) {
        boolean isCustomerInDB;

        try {
          isCustomerInDB = customerRepository.existsById(id);

          if (!isCustomerInDB) {
            return false;
          }

          customerRepository.deleteById(id);
          return true;
        } catch (RuntimeException e) {
        	System.out.println(e.getLocalizedMessage());
          return false;
        }
    }
}
