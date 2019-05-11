package com.practice.orders.service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.orders.model.Order;
import com.practice.orders.repository.IOrderRepo;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi // impose this service for GraphQL
public class OrderGraphQlService {

	@Autowired
	private IOrderRepo orderRepo;
	
	@Autowired
	private GraphQLClient client;

	@GraphQLQuery(name = "orders")
	public List<Order> findAllOrders() {
		return orderRepo.findAll();
	}

	@GraphQLQuery(name = "OrdersByCustomerId")
	public Order findByCustomerId(String customerId) {
		Optional<Order> o = orderRepo.findByCustomerId(customerId);
		if (!o.isPresent())
			throw new RuntimeException("Order doesn't exist for given customerId:: " + customerId);
		return o.get();
	}

	@GraphQLQuery(name = "OrdersByProductId")
	public Order findByProductId(String productId) {
		Optional<Order> o = orderRepo.findByProductId(productId);
		if (!o.isPresent())
			throw new RuntimeException("Order doesn't exist for given productId:: " + productId);
		return o.get();
	}

	@GraphQLQuery(name = "OrdersByOrderId")
	public Order findByOrderId(String id) throws IllegalStateException, MalformedURLException {
		Optional<Order> o = orderRepo.findById(id);
		client.sendRequest();
		if (!o.isPresent())
			throw new RuntimeException("Order doesn't exist for given orderId:: " + id);
		return o.get();
	}

	@GraphQLMutation(name = "createOrder")
	public Order saveProduct(String id, String productId, String customerId) {
		return orderRepo.save(new Order(id, productId, customerId));
	}

	@GraphQLMutation(name = "updateOrder")
	public Order updateProduct(String id, String productId, String customerId) {
		Optional<Order> o = orderRepo.findById(id);
		if (!o.isPresent())
			throw new RuntimeException("Order doesn't exist for given orderId:: " + id);

		Order od = o.get();
		od.setCustomerId(customerId);
		od.setProductId(productId);
		return orderRepo.save(od);
	}

	@GraphQLMutation(name = "deleteOrder")
	public String deleteOrder(String id) {
		Optional<Order> o = orderRepo.findById(id);
		if (!o.isPresent())
			throw new RuntimeException("Order doesn't exist for given orderId:: " + id);

		orderRepo.delete(o.get());
		return "Deleted Order Successfully";
	}

	@GraphQLMutation(name = "deleteOrders")
	public String deleteAll() {
		orderRepo.deleteAll();
		return "All Products Deleted Successfully";
	}

}
