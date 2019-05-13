package com.practice.graphqldemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.graphqldemo.model.Customer;
import com.practice.graphqldemo.repository.ICustomerRepo;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

//@GraphQLApi is to impose this service for GraphQL
@Service
@GraphQLApi
public class CustomerGraphQlService {

	@Autowired
	private ICustomerRepo customerRepo;

	@GraphQLQuery(name = "customers")
	public List<Customer> findAllCustomer() {
		return customerRepo.findAll();
	}

	@GraphQLMutation(name = "createCustomer")
	public Customer saveCustomer(String id, String firstName, String lastName, int age) {
		return customerRepo.save(new Customer(id, firstName, lastName, age));
	}

	@GraphQLMutation(name = "updateCustomer")
	public Customer updateCustomer(String firstName, String lastName, int age) {
		Customer customer = findCustomerByName(firstName);
		customer.setLastName(lastName);
		customer.setAge(age);
		return customerRepo.save(customer);
	}

	@GraphQLQuery(name = "customer")
	public Customer findCustomerByName(String name) {
		Optional<Customer> c = customerRepo.findByFirstName(name);
		if (!c.isPresent())
			throw new RuntimeException("Customer doesn't exist with given name " + name);
		return c.get();
	}

	@GraphQLQuery(name = "customer")
	public Customer findCustomerById(String id) {
		Optional<Customer> c = customerRepo.findById(id);
		if (!c.isPresent())
			throw new RuntimeException("Customer doesn't exist with given id " + id);
		return c.get();
	}

	@GraphQLMutation(name = "deleteCustomer")
	public String deleteCustomer(String name) {
		Optional<Customer> c = customerRepo.findByFirstName(name);
		if (!c.isPresent())
			throw new RuntimeException("Customer doesn't exist for name " + name);
		else {
			customerRepo.delete(c.get());
			return "Customer deleted Successfully";
		}

	}

	@GraphQLMutation(name = "deleteCustomers")
	public String deleteAllCustomers() {
		customerRepo.deleteAll();
		return "Deleted Successfully";
	}

}
