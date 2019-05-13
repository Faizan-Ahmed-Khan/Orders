package com.practice.orders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@AllArgsConstructor
@Data
public class OrderDetails {

	@Id
	private String id;
	private String productId;
	private String customerId;
	private Product product;
	private Customer customer;
	

}
