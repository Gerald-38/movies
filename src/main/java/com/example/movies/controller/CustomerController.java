package com.example.movies.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.model.Customer;
import com.example.movies.model.Tag;
import com.example.movies.repository.CustomerRepository;
import com.example.movies.service.CustomerService;



@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/get")
    public List<Customer> getCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("tags", customers);
        return customers;
    }
    
    @PostMapping("/post")
    public String createCustomer(@RequestBody Customer customer)  {
    	customerService.addCustomer(customer);    	
        return ("Customer is valid");
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer currentCustomer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCustomer.setName(customer.getName());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setAge(customer.getAge());
        currentCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(currentCustomer);
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
      Boolean isCustomerDeleted = customerService.deleteById(id);
      if (!isCustomerDeleted) {
        return "Customer does not exist!";
      }
      return "Customer deleted";
    } 

}
