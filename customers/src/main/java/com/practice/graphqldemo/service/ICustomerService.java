package com.practice.graphqldemo.service;

import java.util.List;

import com.practice.graphqldemo.model.Customer;

public interface ICustomerService {

	// Create
	public Customer createCustomer(String id, String firstName, String lastName, int age);

	// Retrieve
	public Customer getByFirstName(String firstName);

	public List<Customer> getAll();

	// Update
	public Customer updateCustomer(String firstName, String lastName, int age);

	// Delete
	public String deleteAll();

	public String deleteByfname(String firstName);

}
