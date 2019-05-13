package com.practice.orders.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.orders.model.Customer;
import com.practice.orders.model.Order;
import com.practice.orders.model.OrderDetails;
import com.practice.orders.model.Product;
import com.practice.orders.repository.IOrderRepo;
import com.practice.orders.service.query.CustomerQuery;
import com.practice.orders.service.query.ProductQuery;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi // impose this service for GraphQL
public class OrderGraphQlService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

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
				HashMap<String, String> prodhm = new HashMap<>();
				prodhm.put("id", o.getProductId());
				ProductQuery p = (ProductQuery) client.sendRequest(new ProductQuery(), "http://localhost:9090/graphql/",
						prodhm, "product");
				Product prod = p.getProduct();
				logger.info("Response---------> {}", prod.toString());

				HashMap<String, String> custhm = new HashMap<>();
				custhm.put("id", o.getCustomerId());
				CustomerQuery c = (CustomerQuery) client.sendRequest(new CustomerQuery(),
						"http://localhost:8080/graphql/", custhm, "customer");
				Customer cust = c.getCustomer();
				logger.info("Response---------> {}", cust.toString());

				OrderDetails od = new OrderDetails(o.getId(), o.getProductId(), o.getCustomerId(), prod, cust);
				orderDtlList.add(od);
			} catch (Exception e) {
				logger.error(e.getMessage());
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
		if (!o.isPresent())
			throw new RuntimeException("Order doesn't exist for given orderId:: " + id);

		HashMap<String, String> prodhm = new HashMap<String, String>();
		prodhm.put("id", o.get().getProductId());
		ProductQuery p = (ProductQuery) client.sendRequest(new ProductQuery(), "http://localhost:9090/graphql/", prodhm,
				"product");
		Product prod = p.getProduct();
		logger.info("Response---------> {}", prod.toString());

		HashMap<String, String> custhm = new HashMap<>();
		custhm.put("id", o.get().getCustomerId());
		CustomerQuery c = (CustomerQuery) client.sendRequest(new CustomerQuery(), "http://localhost:8080/graphql/",
				custhm, "customer");
		Customer cust = c.getCustomer();
		logger.info("Response---------> {}", cust.toString());
		return new OrderDetails(o.get().getId(), o.get().getProductId(), o.get().getCustomerId(), prod, cust);
	}

	@GraphQLMutation(name = "createOrder")
	public Order saveProduct(String id, String productId, String customerId) {
		return orderRepo.save(Order.builder().id(id).productId(productId).customerId(customerId).build());
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
