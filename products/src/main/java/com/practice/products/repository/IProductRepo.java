package com.practice.products.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.practice.products.model.Product;

@Repository
public interface IProductRepo extends MongoRepository<Product, String> {

	public Optional<Product> findById(String id);

	public Optional<Product> findByName(String name);

}
