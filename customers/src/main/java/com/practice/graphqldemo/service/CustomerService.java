package com.practice.graphqldemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.graphqldemo.model.Customer;
import com.practice.graphqldemo.repository.ICustomerRepo;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepo customerRepo;

	@Override
	public Customer createCustomer(String id,String firstName, String lastName, int age) {
		return customerRepo.save(new Customer(id,firstName, lastName, age));
	}

	@Override
	public Customer getByFirstName(String firstName) {
//		return customerRepo.findByFirstName(firstName);
		return null;
	}

	@Override
	public List<Customer> getAll() {
		return customerRepo.findAll();
	}

	@Override
	public Customer updateCustomer(String firstName, String lastName, int age) {
//		Customer c = customerRepo.findByFirstName(firstName);
//		c.setLastName(lastName);
//		c.setAge(age);
//		return customerRepo.save(c);
		return null;
	}

	@Override
	public String deleteAll() {
		customerRepo.deleteAll();
		return "Deleted All";
	}

	@Override
	public String deleteByfname(String firstName) {
//		Customer c = customerRepo.findByFirstName(firstName);
//		customerRepo.delete(c);
//		return "Deleted Customer :: " + c.getFirstName();
		return null;

	}

}
