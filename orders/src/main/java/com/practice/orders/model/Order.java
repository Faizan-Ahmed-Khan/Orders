package com.practice.orders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

	@Id
	private String id;
	private String productId;
	private String customerId;

	@Override
	public String toString() {
		return "Order -> id :: " + id + " productId:: " + productId + " customerId:: " + customerId;
	}

	public Order(String id, String productId, String customerId) {
		this.id = id;
		this.customerId = customerId;
		this.productId = productId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
