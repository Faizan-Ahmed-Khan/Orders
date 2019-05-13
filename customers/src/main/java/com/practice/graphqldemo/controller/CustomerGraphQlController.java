package com.practice.graphqldemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.graphqldemo.model.Customer;
import com.practice.graphqldemo.service.CustomerGraphQlService;

@RestController
public class CustomerGraphQlController {

	@Autowired
	private CustomerGraphQlService service;
	
	@PostMapping("/graphql/customer")
	public List<Customer> getCustomerList(){
		return service.findAllCustomer();
	}

}
