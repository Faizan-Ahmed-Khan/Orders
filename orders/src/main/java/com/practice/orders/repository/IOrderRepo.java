package com.practice.orders.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.practice.orders.model.Order;

@Repository
public interface IOrderRepo extends MongoRepository<Order, String> {

	public Optional<Order> findByProductId(String productId);

	public Optional<Order> findByCustomerId(String customerId);

	public Optional<Order> findById(String id);

}
