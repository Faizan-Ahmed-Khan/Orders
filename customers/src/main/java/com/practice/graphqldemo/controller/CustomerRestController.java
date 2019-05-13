package com.practice.graphqldemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.graphqldemo.model.Customer;
import com.practice.graphqldemo.service.ICustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	private ICustomerService service;

	// Create
	@PostMapping("/customer/{id}/{firstName}/{lastName}/{age}")
	public String insertCustomer(@PathVariable("id") String id, @PathVariable("firstName") String firstName,
			@PathVariable String lastName, @PathVariable int age) {
		Customer c = service.createCustomer(id, firstName, lastName, age);
		return c.toString();
	}

	// Retrieve
	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		return service.getAll();
	}

	@GetMapping("/customer/{firstName}")
	public String getCustomerByfirstName(@PathVariable("firstName") String firstName) {
		Customer c = service.getByFirstName(firstName);
		return c.toString();
	}

	// Update
	@PutMapping("/customer/{firstName}/{lastName}/{age}")
	public String updateCustomer(@PathVariable("firstName") String firstName, @PathVariable String lastName,
			@PathVariable int age) {
		Customer c = service.updateCustomer(firstName, lastName, age);
		return c.toString();
	}

	// Delete
	@DeleteMapping("/customer")
	public void deleteAll() {
		service.deleteAll();
	}

	@DeleteMapping("/customer/{firstName}")
	public void deleteByFname(@PathVariable("firstName") String firstName) {
		service.deleteByfname(firstName);
	}
}
