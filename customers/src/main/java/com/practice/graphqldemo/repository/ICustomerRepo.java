package com.practice.graphqldemo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.practice.graphqldemo.model.Customer;

@Repository
public interface ICustomerRepo extends MongoRepository<Customer, String> {

	public Optional<Customer> findByFirstName(String firstName);

//	public Customer findByFirstName(String firstName);

}
