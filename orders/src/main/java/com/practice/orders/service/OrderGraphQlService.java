package com.practice.orders.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.orders.model.Customer;
import com.practice.orders.model.Order;
import com.practice.orders.model.OrderDetails;
import com.practice.orders.model.Product;
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
	public List<OrderDetails> findAllOrders() {
		List<Order> orderList = orderRepo.findAll();
		List<OrderDetails> orderDtlList = new ArrayList<OrderDetails>();
		orderList.forEach(o -> {
			try {
				OrderDetails od = new OrderDetails(o.getId(), o.getProductId(), o.getCustomerId(),
						client.getProduct(o.getProductId()), client.getCustomer(o.getCustomerId()));
				orderDtlList.add(od);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return orderDtlList;
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
	public OrderDetails findByOrderId(String id) throws Exception {
		Optional<Order> o = orderRepo.findById(id);
		Product p = client.getProduct(o.get().getProductId());
		Customer c = client.getCustomer(o.get().getCustomerId());
		if (!o.isPresent())
			throw new RuntimeException("Order doesn't exist for given orderId:: " + id);

		OrderDetails od = new OrderDetails(o.get().getId(), o.get().getProductId(), o.get().getCustomerId(), p, c);
		return od;
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
		return "All ProductQuery Deleted Successfully";
	}

}
