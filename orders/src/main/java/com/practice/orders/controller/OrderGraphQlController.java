package com.practice.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.orders.model.Order;
import com.practice.orders.service.OrderGraphQlService;

@RestController
public class OrderGraphQlController {

	@Autowired
	private OrderGraphQlService service;

	@PostMapping("/graphql/orders")
	public List<Order> getOrderList() {
		return service.findAllOrders();
	}

}
